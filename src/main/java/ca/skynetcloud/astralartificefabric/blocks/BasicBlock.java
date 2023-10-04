package ca.skynetcloud.astralartificefabric.blocks;



import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;


import java.util.function.Function;


public class BasicBlock extends Block {
    public BasicBlock(Function<Properties, Properties> properties) {
        super(properties.apply(Properties.of()));
    }

    public BasicBlock(SoundType sound, float hardness, float resistance) {
        super(Properties.of()
                .sound(sound)
                .strength(hardness, resistance)
        );
    }

    public BasicBlock(SoundType sound, float hardness, float resistance, boolean tool) {
        super(Properties.of()
                .sound(sound)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops()
        );
    }
}
