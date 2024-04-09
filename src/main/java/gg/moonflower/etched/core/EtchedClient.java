package gg.moonflower.etched.core;

import gg.moonflower.etched.client.screen.EtchingScreen;
import gg.moonflower.etched.core.registry.EtchedMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import gg.moonflower.etched.client.render.EtchedModelLayers;
import gg.moonflower.etched.client.render.JukeboxMinecartRenderer;
import gg.moonflower.etched.client.render.item.AlbumCoverItemRenderer;
import gg.moonflower.etched.common.item.BlankMusicDiscItem;
import gg.moonflower.etched.common.item.ComplexMusicLabelItem;
import gg.moonflower.etched.common.item.EtchedMusicDiscItem;
import gg.moonflower.etched.common.item.MusicLabelItem;
import gg.moonflower.etched.core.registry.EtchedBlocks;
import gg.moonflower.etched.core.registry.EtchedEntities;
import gg.moonflower.etched.core.registry.EtchedItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.MinecartModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

@ClientOnly
public class EtchedClient {

    public static void registerItemGroups() {
        MenuScreens.register(EtchedMenus.ETCHING_MENU, EtchingScreen::new);
        //TODO: Move to registrat
        /*ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(event -> {
    	    event.accept(EtchedBlocks.ETCHING_TABLE.asStack(1));
            event.accept(EtchedBlocks.ALBUM_JUKEBOX.asStack(1));
            event.accept(EtchedBlocks.RADIO.asStack(1));
        });*/
    }

    //TODO: FIX`
    /*
    @SubscribeEvent
    public static void registerCustomModels(ModelEvent.RegisterAdditional event) {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        String folder = "models/item/" + AlbumCoverItemRenderer.FOLDER_NAME;
        event.register(new ModelResourceLocation(new ResourceLocation(Etched.MOD_ID, "boombox_in_hand"), "inventory"));
        for (ResourceLocation location : resourceManager.listResources(folder, name -> name.getPath().endsWith(".json")).keySet()) {
            event.register(new ModelResourceLocation(new ResourceLocation(location.getNamespace(), location.getPath().substring(12, location.getPath().length() - 5)), "inventory"));
        }
    }

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EtchedEntities.JUKEBOX_MINECART.get(), JukeboxMinecartRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EtchedModelLayers.JUKEBOX_MINECART, MinecartModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, index) -> index == 0 || index == 1 ? MusicLabelItem.getLabelColor(stack) : -1, EtchedItems.MUSIC_LABEL.get());
        event.register((stack, index) -> index == 0 ? ComplexMusicLabelItem.getPrimaryColor(stack) : index == 1 ? ComplexMusicLabelItem.getSecondaryColor(stack) : -1, EtchedItems.COMPLEX_MUSIC_LABEL.get());

        event.register((stack, index) -> index > 0 ? -1 : ((BlankMusicDiscItem) stack.getItem()).getColor(stack), EtchedItems.BLANK_MUSIC_DISC.get());
        event.register((stack, index) -> {
            if (index == 0) {
                return EtchedMusicDiscItem.getDiscColor(stack);
            }
            if (EtchedMusicDiscItem.getPattern(stack).isColorable()) {
                if (index == 1) {
                    return EtchedMusicDiscItem.getLabelPrimaryColor(stack);
                }
                if (index == 2) {
                    return EtchedMusicDiscItem.getLabelSecondaryColor(stack);
                }
            }
            return -1;
        }, EtchedItems.ETCHED_MUSIC_DISC.get());
    } */
}
