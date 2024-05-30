package gg.moonflower.etched.core;

import com.tterrag.registrate.Registrate;
import gg.moonflower.etched.api.sound.download.SoundSourceManager;
import gg.moonflower.etched.common.network.EtchedMessages;
import gg.moonflower.etched.common.sound.download.BandcampSource;
import gg.moonflower.etched.common.sound.download.SoundCloudSource;
import gg.moonflower.etched.core.fabric.EtchedConfig;
import gg.moonflower.etched.core.registry.*;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Etched {

    public static final String MOD_ID = "etched";
    public static final Registrate REGISTRATE = Registrate.create(MOD_ID);
    public static final Logger LOGGER = LogManager.getLogger("Etched/General");
    public static final ModelResourceLocation BOOMBOX_IN_HAND_MODEL = new ModelResourceLocation(new ResourceLocation(Etched.MOD_ID, "boombox_in_hand"), "inventory");


    public Etched() {

    }

    public static void init() {
        //i guess following is needed to preload classes before registration or they will
        // not be registered
        EtchedTags.register();
        EtchedMessages.init();
        EtchedBlocks.register();
        EtchedEntities.register();
        EtchedItems.register();
        EtchedMenus.register();
        EtchedRecipes.register();
        EtchedSounds.register();

        // for some reason POI is broken so I guess I will add Crafting Recipe for the table for now
        //EtchedVillagers.registers();

        REGISTRATE.register();
        EtchedConfig.HANDLER.load();
        SoundSourceManager.registerSource(new SoundCloudSource());
        SoundSourceManager.registerSource(new BandcampSource());
    }

}

