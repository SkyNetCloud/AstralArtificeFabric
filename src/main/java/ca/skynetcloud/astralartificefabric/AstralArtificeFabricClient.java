package ca.skynetcloud.astralartificefabric;

import ca.skynetcloud.astralartificefabric.client.blockentity.StarAltarRenderer;
import ca.skynetcloud.astralartificefabric.client.blockentity.StarPedestalRenderer;
import ca.skynetcloud.astralartificefabric.init.BlockEntitiesInit;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class AstralArtificeFabricClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {



        BlockEntityRenderers.register(BlockEntitiesInit.STAR_PEDESTAL, StarPedestalRenderer::new);
        BlockEntityRenderers.register(BlockEntitiesInit.STAR_ALTAR, StarAltarRenderer::new);

    }
}
