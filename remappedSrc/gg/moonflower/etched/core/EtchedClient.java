package gg.moonflower.etched.core;

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
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.MinecartEntityModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Etched.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EtchedClient {

    @SubscribeEvent
    public static void registerReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(AlbumCoverItemRenderer.INSTANCE);
    }

    @SubscribeEvent
    public static void registerItemGroups(BuildCreativeModeTabContentsEvent event) {
        RegistryKey<ItemGroup> tab = event.getTabKey();
        if (tab == ItemGroups.TOOLS) {
            event.accept(EtchedItems.MUSIC_LABEL);
            event.accept(EtchedItems.BLANK_MUSIC_DISC);
            event.accept(EtchedItems.BOOMBOX);
            event.accept(EtchedItems.ALBUM_COVER);
        } else if (tab == ItemGroups.REDSTONE) {
            event.accept(EtchedItems.JUKEBOX_MINECART);
        } else if (tab == ItemGroups.FUNCTIONAL) {
            event.accept(EtchedBlocks.ETCHING_TABLE);
            event.accept(EtchedBlocks.ALBUM_JUKEBOX);
            event.accept(EtchedBlocks.RADIO);
        }
    }

    @SubscribeEvent
    public static void registerCustomModels(ModelEvent.RegisterAdditional event) {
        ResourceManager resourceManager = MinecraftClient.getInstance().getResourceManager();
        String folder = "models/item/" + AlbumCoverItemRenderer.FOLDER_NAME;
        event.register(new ModelIdentifier(new Identifier(Etched.MOD_ID, "boombox_in_hand"), "inventory"));
        for (Identifier location : resourceManager.findResources(folder, name -> name.getPath().endsWith(".json")).keySet()) {
            event.register(new ModelIdentifier(new Identifier(location.getNamespace(), location.getPath().substring(12, location.getPath().length() - 5)), "inventory"));
        }
    }

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EtchedEntities.JUKEBOX_MINECART.get(), JukeboxMinecartRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EtchedModelLayers.JUKEBOX_MINECART, MinecartEntityModel::createBodyLayer);
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
    }
}
