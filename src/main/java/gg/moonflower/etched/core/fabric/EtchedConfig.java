package gg.moonflower.etched.core.fabric;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import gg.moonflower.etched.core.Etched;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;


public class EtchedConfig {
    public static ConfigClassHandler<EtchedConfig> HANDLER = ConfigClassHandler.createBuilder(EtchedConfig.class)
            .id(new ResourceLocation(Etched.MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("etched.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                    .setJson5(true)
                    .build())
            .build();

    public static void save(){
        EtchedConfig.HANDLER.save();
    }
    public static void load(){
        EtchedConfig.HANDLER.load();
    }

    @SerialEntry(comment = "Disables right clicking music discs into boomboxes and allows the menu to be used by shift right-clicking.")
    public boolean useBoomboxMenu = false;

    @SerialEntry(comment = "Disables right clicking music discs into album covers and allows the menu to be used by shift right-clicking")
    public boolean useAlbumCoverMenu = false;

    @SerialEntry(comment = "Displays note particles appear above jukeboxes while a record is playing.")
    public boolean showNotes = true;

    @SerialEntry(comment = "Always plays tracks in stereo even when in-world.")
    public boolean forceStereo = false;

    public Screen getConfigScreen(Screen screen){
        return YetAnotherConfigLib.createBuilder()
                .title(Component.literal("Etched"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Server"))
                        .group(OptionGroup.createBuilder()
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Use Boombox Menu"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Component.literal("Disables right clicking music discs into boomboxes and allows the menu to be used by shift right-clicking."))
                                                .build())

                                        .binding(false,() -> this.useBoomboxMenu,aBoolean -> {this.useBoomboxMenu = aBoolean;})
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Use Album Cover"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Component.literal("Disables right clicking music discs into album covers and allows the menu to be used by shift right-clicking"))
                                                .build())
                                        .binding(false,() -> this.useAlbumCoverMenu,aBoolean -> {this.useAlbumCoverMenu = aBoolean;})
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build()
                )
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Client"))
                        .group(OptionGroup.createBuilder()
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Force Stereo"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Component.literal("Always plays tracks in stereo even when in-world."))
                                                .build())
                                        .binding(true,() -> this.forceStereo,aBoolean -> {this.forceStereo = aBoolean;})
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Show Notes"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Component.literal("Displays note particles appear above jukeboxes while a record is playing."))
                                                .build())
                                        .binding(false,() -> this.showNotes,aBoolean -> {this.showNotes = aBoolean;})
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build()
                )
                .save(EtchedConfig::save)
                .build().generateScreen(screen);
    };

}
