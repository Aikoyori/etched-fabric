package gg.moonflower.etched.common.network.play;

import gg.moonflower.etched.common.network.EtchedMessages;
import gg.moonflower.etched.common.network.play.handler.EtchedClientPlayPacketHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

/**
 * @param exception The exception to set in the etching table
 * @author Jackson
 */
@ApiStatus.Internal
public record ClientboundInvalidEtchUrlPacket(String exception) implements EtchedPacket {

    public ClientboundInvalidEtchUrlPacket(FriendlyByteBuf buf) {
        this(buf.readUtf());
    }

    @Override
    public void writePacketData(FriendlyByteBuf buf) {
        buf.writeUtf(this.exception);
    }

    @Override
    public ResourceLocation getPacketId() {
        return EtchedMessages.CLIENT_INVALID_ETCH_URL;
    }
}
