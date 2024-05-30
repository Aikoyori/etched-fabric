package gg.moonflower.etched.common.network;

import gg.moonflower.etched.common.network.play.EtchedPacket;
import net.minecraft.client.Minecraft;

@FunctionalInterface
public interface EtchedClientPacketHandlerInterface<MSG extends EtchedPacket> {
    public void handle(MSG packet, Minecraft client);
}
