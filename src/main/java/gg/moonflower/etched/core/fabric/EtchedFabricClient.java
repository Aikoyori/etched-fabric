package gg.moonflower.etched.core.fabric;

import gg.moonflower.etched.common.network.EtchedMessages;
import gg.moonflower.etched.core.Etched;
import net.fabricmc.api.ClientModInitializer;

//import gg.moonflower.etched.common.entity.MinecartJukebox;
import gg.moonflower.etched.core.EtchedClient;
import net.fabricmc.fabric.api.event.client.player.ClientPickBlockGatherCallback;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class EtchedFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EtchedMessages.init();
        EtchedMessages.initClient();
        EtchedClient.registerItemGroups();

        ClientPickBlockGatherCallback.EVENT.register((player, result) -> {
            if (result.getType() == HitResult.Type.ENTITY && player.getAbilities().instabuild) {
                Entity entity = ((EntityHitResult) result).getEntity();
                //FIXME
                //if (entity instanceof MinecartJukebox minecart) {
                //    return new ItemStack(minecart.getDropItem());
                //}
            }
            return ItemStack.EMPTY;
        });
        EtchedClient.registerItemColors();
    }
}
