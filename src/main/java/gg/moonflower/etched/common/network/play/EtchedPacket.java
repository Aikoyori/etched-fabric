package gg.moonflower.etched.common.network.play;

import gg.moonflower.etched.common.network.EtchedMessages;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.ApiStatus;

import java.io.IOException;
import java.util.Collection;

/**
 * A message intended for the specified message handler.
 *
 * @author Ocelot
 */
@ApiStatus.Internal
public interface EtchedPacket {

    /**
     * Writes the raw message data to the data stream.
     *
     * @param buf The buffer to write to
     */
    void writePacketData(FriendlyByteBuf buf) throws IOException;
    ResourceLocation getPacketId();

    private FriendlyByteBuf getBuf() {
        FriendlyByteBuf buf = PacketByteBufs.create();
        try {
            writePacketData(buf);
        }
        catch (Exception exception) {
            EtchedMessages.LOGGER.error("Could not write buf for packet "+ getPacketId(), exception);
        }
        return buf;
    }

    default void sendToClient(ServerPlayer player) {
        var buf = getBuf();
        ServerPlayNetworking.send(player, getPacketId(), buf);
        EtchedMessages.LOGGER.info(getPacketId()+" (server -> client)");
    }

    default void sendToClients(Collection<ServerPlayer> players) {
        var buf = getBuf();
        players.forEach(serverPlayer -> {
            ServerPlayNetworking.send(serverPlayer, getPacketId(), buf);
        });
        EtchedMessages.LOGGER.info(getPacketId()+" (server -> clients)");
    }

    default void sendToServer() {
        var buf = getBuf();
        ClientPlayNetworking.send(getPacketId(), buf);
        EtchedMessages.LOGGER.info(getPacketId()+" (client -> server)");
    }
}
