package gg.moonflower.etched.core.registry;

import gg.moonflower.etched.common.item.*;
import gg.moonflower.etched.core.Etched;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import java.util.function.Function;
import java.util.function.Supplier;

import com.tterrag.registrate.util.entry.ItemEntry;

public class EtchedItems {

    //public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Etched.MOD_ID);

    public static final ItemEntry<MusicLabelItem> MUSIC_LABEL = 
        Etched.REGISTRATE.item("music_label", MusicLabelItem::new)
                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register();
    //register("music_label", () -> new MusicLabelItem(new Item.Properties()));
    public static final ItemEntry<ComplexMusicLabelItem> COMPLEX_MUSIC_LABEL = 
        Etched.REGISTRATE.item("complex_music_label", ComplexMusicLabelItem::new)
                .register();
    //register("complex_music_label", () -> new ComplexMusicLabelItem(new Item.Properties()));
    public static final ItemEntry<BlankMusicDiscItem> BLANK_MUSIC_DISC = 
        Etched.REGISTRATE.item("blank_music_disc", BlankMusicDiscItem::new)
                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
        .register();
    //register("blank_music_disc", () -> new BlankMusicDiscItem(new Item.Properties()));
    public static final ItemEntry<EtchedMusicDiscItem> ETCHED_MUSIC_DISC = 
        Etched.REGISTRATE.item("etched_music_disc", EtchedMusicDiscItem::new)
        .properties(p->p.stacksTo(1))
        .register();
    //register("etched_music_disc", () -> new EtchedMusicDiscItem(new Item.Properties().stacksTo(1)));
    public static final ItemEntry<MinecartJukeboxItem> JUKEBOX_MINECART = 
        Etched.REGISTRATE.item("jukebox_minecart", MinecartJukeboxItem::new)
        .properties(p->p.stacksTo(1))
                .tab(CreativeModeTabs.REDSTONE_BLOCKS)
        .register();
    //register("jukebox_minecart", () -> new MinecartJukeboxItem(new Item.Properties().stacksTo(1)));
    public static final ItemEntry<BoomboxItem> BOOMBOX = 
        Etched.REGISTRATE.item("boombox", BoomboxItem::new)
        .properties(p->p.stacksTo(1))
                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
        .register();
    //register("boombox", () -> new BoomboxItem(new Item.Properties().stacksTo(1)));
    public static final ItemEntry<AlbumCoverItem> ALBUM_COVER = 
        Etched.REGISTRATE.item("album_cover", AlbumCoverItem::new)
        .properties(p->p.stacksTo(1))
                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
        .register();
    /*
    public static final ItemEntry<PortalRadioItem> PORTAL_RADIO_ITEM =
        Etched.REGISTRATE.item("portal_radio", PortalRadioItem::new)
        .properties(p->p.st(1))
                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
        .register();*/
    //register("album_cover", () -> new AlbumCoverItem(new Item.Properties().stacksTo(1)));
    public static void register() {}
}
