package ca.skynetcloud.astralartificefabric.util.handler;


import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class BasicItemStackHandler extends BasicForgeItemStackHandler {
    private final Runnable onContentsChanged;
    private final Map<Integer, Integer> slotSizeMap;
    private BiFunction<Integer, ItemStack, Boolean> canInsert = null;
    private Function<Integer, Boolean> canExtract = null;
    private int maxStackSize = 64;
    protected int[] outputSlots = null;
    private final int[] availableSlots;

    protected BasicItemStackHandler(int size, Runnable onContentsChanged) {
        super(size);
        this.onContentsChanged = onContentsChanged;
        this.slotSizeMap = new HashMap();
        this.availableSlots = new int[this.getContainerSize()];

        for(int i = 0; i < this.getContainerSize(); this.availableSlots[i] = i++) {
        }

    }
    public ItemStack insertItem(int slot, ItemStack stack) {
        return this.insertItem(slot, stack, false);
    }

    public ItemStack insertItem(int slot, ItemStack stack, boolean container) {
        return !container && this.outputSlots != null && ArrayUtils.contains(this.outputSlots, slot) ? stack : super.insertItem(slot, stack);
    }

    public int getSlotLimit(int slot) {
        return this.slotSizeMap.containsKey(slot) ? (Integer)this.slotSizeMap.get(slot) : this.maxStackSize;
    }

    public int getMaxStackSize() {
        return this.maxStackSize;
    }

    public boolean canPlaceItem(int slot, ItemStack stack) {
        return this.canInsert == null || this.canInsert.apply(slot, stack);
    }

    public void clearContent() {
        this.stacks.clear();
        this.setChanged();
    }

    public void setChanged() {
        if (this.onContentsChanged != null) {
            this.onContentsChanged.run();
        }

    }
    public NonNullList<ItemStack> getStacks() {
        return this.stacks;
    }

    public void setDefaultSlotLimit(int size) {
        this.maxStackSize = size;
    }

    public void setCanInsert(BiFunction<Integer, ItemStack, Boolean> validator) {
        this.canInsert = validator;
    }

    public void setOutputSlots(int... slots) {
        this.outputSlots = slots;
    }

    public static BasicItemStackHandler create(int size) {
        return create(size, (builder) -> {
        });
    }


    public static BasicItemStackHandler create(int size, Consumer<BasicItemStackHandler> builder) {
        return create(size, (Runnable)null, builder);
    }

    public static BasicItemStackHandler create(int size, Runnable onContentsChanged, Consumer<BasicItemStackHandler> builder) {
        BasicItemStackHandler handler = new BasicItemStackHandler(size, onContentsChanged);
        builder.accept(handler);
        return handler;
    }

    public int[] getSlotsForFace(Direction side) {
        return this.availableSlots;
    }

    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.canPlaceItem(slot, stack) && (this.outputSlots == null || !ArrayUtils.contains(this.outputSlots, slot));
    }

    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return (this.canExtract == null || (Boolean)this.canExtract.apply(slot)) && (this.outputSlots == null || ArrayUtils.contains(this.outputSlots, slot));
    }
}
