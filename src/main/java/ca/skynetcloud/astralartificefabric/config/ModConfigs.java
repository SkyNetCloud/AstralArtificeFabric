package ca.skynetcloud.astralartificefabric.config;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

public class ModConfigs {
    public static final Builder BUILDER = new Builder();
    public static final ForgeConfigSpec CONFIG;



    private static IntValue TILLING_RANGE;
    private static IntValue MOISTURE;

    static {
        final var common = new ForgeConfigSpec.Builder();

        common.comment("General configuration options.").push("General");

        //cultivator
        TILLING_RANGE = BUILDER.comment("\r\nRange of cultivator item").defineInRange("cultivator.range", 9, 2, 32);
        MOISTURE = BUILDER.comment("\r\nMoisture level set by cultivator").defineInRange("cultivator.moisture", 7, 0, 7);

        BUILDER.pop();
        CONFIG = BUILDER.build();
    }
    public static int getTillingRange() {
        return TILLING_RANGE.get();
    }
    public static int getMoisture() {
        return MOISTURE.get();
    }
}