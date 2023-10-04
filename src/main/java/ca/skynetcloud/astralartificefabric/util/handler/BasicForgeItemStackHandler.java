package ca.skynetcloud.astralartificefabric.util.handler;

import ca.skynetcloud.astralartificefabric.lib.forge.ForgeBasicItemHandlerHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public abstract class BasicForgeItemStackHandler implements WorldlyContainer {
    protected NonNullList<ItemStack> stacks;
    protected boolean simulate;

    public BasicForgeItemStackHandler() {
        this(1);
    }

    public BasicForgeItemStackHandler(int size) {
        this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);
        this.simulate = false;
    }

    public BasicForgeItemStackHandler(NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    public void setSize(int size) {
        this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        this.validateSlotIndex(slot);
        this.stacks.set(slot, stack);
        this.setChanged();
    }

    public void setSimulate(boolean simulate) {
        this.simulate = simulate;
    }

    public int getContainerSize() {
        return this.stacks.size();
    }

    public boolean isEmpty() {
        Iterator var1 = this.stacks.iterator();

        ItemStack itemStack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemStack = (ItemStack)var1.next();
        } while(itemStack.isEmpty());

        return false;
    }

    public ItemStack getItem(int slot) {
        this.validateSlotIndex(slot);
        return (ItemStack)this.stacks.get(slot);
    }

    public ItemStack removeItem(int slot, int amount) {
        if (amount == 0) {
            return ItemStack.EMPTY;
        } else {
            this.validateSlotIndex(slot);
            ItemStack existing = (ItemStack)this.stacks.get(slot);
            if (existing.isEmpty()) {
                return ItemStack.EMPTY;
            } else {
                int toExtract = Math.min(amount, existing.getMaxStackSize());
                if (existing.getCount() <= toExtract) {
                    if (!this.simulate) {
                        this.stacks.set(slot, ItemStack.EMPTY);
                        this.setChanged();
                        return existing;
                    } else {
                        this.simulate = false;
                        return existing.copy();
                    }
                } else {
                    if (!this.simulate) {
                        this.stacks.set(slot, ForgeBasicItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                        this.setChanged();
                    }

                    this.simulate = false;
                    return ForgeBasicItemHandlerHelper.copyStackWithSize(existing, toExtract);
                }
            }
        }
    }

    public ItemStack removeItemNoUpdate(int slot) {
        return null;
    }

    public void setItem(int slot, ItemStack stack) {
        this.validateSlotIndex(slot);
        this.stacks.set(slot, stack);
        this.setChanged();
    }

    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack) {
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        } else if (!this.canPlaceItem(slot, stack)) {
            return stack;
        } else {
            this.validateSlotIndex(slot);
            ItemStack existing = (ItemStack)this.stacks.get(slot);
            int limit = this.getStackLimit(slot, stack);
            if (!existing.isEmpty()) {
                if (!ItemStack.isSameItemSameTags(stack, existing)) {
                    return stack;
                }

                limit -= existing.getCount();
            }

            if (limit <= 0) {
                return stack;
            } else {
                boolean reachedLimit = stack.getCount() > limit;
                if (!this.simulate) {
                    if (existing.isEmpty()) {
                        this.setItem(slot, reachedLimit ? ForgeBasicItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
                    } else {
                        existing.grow(reachedLimit ? limit : stack.getCount());
                    }

                    this.setChanged();
                }

                this.simulate = false;
                return reachedLimit ? ForgeBasicItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
            }
        }
    }

    public int getSlotLimit(int slot) {
        return 64;
    }

    protected int getStackLimit(int slot, @NotNull ItemStack stack) {
        return Math.min(this.getSlotLimit(slot), stack.getMaxStackSize());
    }

    public void setChanged() {
    }

    public boolean stillValid(Player player) {
        return false;
    }

    public void clearContent() {
    }

    public CompoundTag serializeNBT() {
        ListTag nbtTagList = new ListTag();

        for(int i = 0; i < this.stacks.size(); ++i) {
            if (!((ItemStack)this.stacks.get(i)).isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                ((ItemStack)this.stacks.get(i)).save(itemTag);
                nbtTagList.add(itemTag);
            }
        }

        CompoundTag nbt = new CompoundTag();
        nbt.put("Items", nbtTagList);
        nbt.putInt("Size", this.stacks.size());
        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.setSize(nbt.contains("Size", 3) ? nbt.getInt("Size") : this.stacks.size());
        ListTag tagList = nbt.getList("Items", 10);

        for(int i = 0; i < tagList.size(); ++i) {
            CompoundTag itemTags = tagList.getCompound(i);
            int slot = itemTags.getInt("Slot");
            if (slot >= 0 && slot < this.stacks.size()) {
                this.stacks.set(slot, ItemStack.of(itemTags));
            }
        }

    }

    protected void validateSlotIndex(int slot) {
        if (slot < 0 || slot >= this.stacks.size()) {
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + this.stacks.size() + ")");
        }
    }
}
