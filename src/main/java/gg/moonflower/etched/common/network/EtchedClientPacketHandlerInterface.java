package gg.moonflower.etched.common.network;

import gg.moonflower.etched.common.network.play.EtchedPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@FunctionalInterface
public interface EtchedClientPacketHandlerInterface<MSG extends EtchedPacket> {
    @Environment(EnvType.CLIENT)
    public void handle(MSG packet, Minecraft client);
}
