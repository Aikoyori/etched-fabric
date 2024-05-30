package gg.moonflower.etched.core.registry;

import com.google.common.collect.ImmutableSet;
import com.tterrag.registrate.fabric.RegistryObject;
import com.tterrag.registrate.util.entry.EntityEntry;
import gg.moonflower.etched.common.entity.MinecartJukebox;
import gg.moonflower.etched.core.Etched;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class EtchedEntities {
    //TODO: Fix
    //public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Etched.MOD_ID);
    /* public static final EntityEntry<Entity> JUKEBOX_MINECART =
            Etched.REGISTRATE.entity("jukebox_minecart",MinecartJukebox::new,MobCategory.MISC)

                    .lang("Minecart with Jukebox")
                    .register();
*/
    public static final EntityType<MinecartJukebox> JUKEBOX_MINECART = FabricEntityTypeBuilder.<MinecartJukebox>create( MobCategory.MISC,MinecartJukebox::new).dimensions(EntityDimensions.fixed(0.98F, 0.7F)).trackRangeChunks(8).build();


    public static <R extends Entity> EntityType<R> register(String name, Supplier<EntityType<R>>value) {
        var id = new ResourceLocation(Etched.MOD_ID, name);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, value.get());
    }
    public ResourceKey<PoiType> BARD_POI_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation(Etched.MOD_ID+":bard"));
    //public static VillagerProfession MUSIC_BARD = new VillagerProfession(Etched.MOD_ID + ":bard", poi -> poi.is(BARD_POI.getId()), poi -> poi.is(BARD_POI.getId()), ImmutableSet.of(), ImmutableSet.of(), null));
    public static void register() {
        Registry.register(BuiltInRegistries.ENTITY_TYPE,new ResourceLocation(Etched.MOD_ID,"jukebox_minecart"),JUKEBOX_MINECART);
        //register("jukebox_minecart", () -> EntityType.Builder.<MinecartJukebox>of(MinecartJukebox::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8).build("minecart_jukebox"));

    }
}
