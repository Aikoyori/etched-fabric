package gg.moonflower.etched.common.network;

import gg.moonflower.etched.common.network.play.*;
import gg.moonflower.etched.common.network.play.handler.EtchedClientPlayPacketHandler;
import gg.moonflower.etched.common.network.play.handler.EtchedServerPlayPacketHandler;
import gg.moonflower.etched.core.Etched;
import io.netty.handler.codec.EncoderException;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.function.Function;

public class EtchedMessages {

    public static final Logger LOGGER = LogManager.getLogger("Etched/Networking");

    public static final ResourceLocation CLIENT_INVALID_ETCH_URL = key("client_invalid_url");
    public static final ResourceLocation CLIENT_PLAY_ENTITY_MUSIC = key("client_play_entity_music");
    public static final ResourceLocation CLIENT_PLAY_MUSIC = key("client_play_music");
    public static final ResourceLocation CLIENT_SET_URL = key("client_set_url");
    public static final ResourceLocation SERVER_EDIT_MUSIC_LABEL = key("server_edit_music_label");
    public static final ResourceLocation SERVER_SET_URL = key("server_set_url");
    public static final ResourceLocation SHARED_SET_ALBUM_JUKEBOX_TRACK = key("shared_set_album_jukebox_track");

    private static ResourceLocation key(String name) {
        return new ResourceLocation(Etched.MOD_ID, name);
    }

    public static synchronized void init() {
        client_register(ClientboundInvalidEtchUrlPacket.class, CLIENT_INVALID_ETCH_URL, EtchedClientPlayPacketHandler::handleSetInvalidEtch);
        client_register(ClientboundPlayEntityMusicPacket.class, CLIENT_PLAY_ENTITY_MUSIC, EtchedClientPlayPacketHandler::handlePlayEntityMusicPacket);
        client_register(ClientboundPlayMusicPacket.class, CLIENT_PLAY_MUSIC, EtchedClientPlayPacketHandler::handlePlayMusicPacket);
        client_register(ClientboundSetUrlPacket.class, CLIENT_SET_URL, EtchedClientPlayPacketHandler::handleSetUrl);

        server_register(ServerboundSetUrlPacket.class, SERVER_SET_URL, EtchedServerPlayPacketHandler::handleSetUrl);
        server_register(ServerboundEditMusicLabelPacket.class, SERVER_EDIT_MUSIC_LABEL, EtchedServerPlayPacketHandler::handleEditMusicLabel);


        server_register(SetAlbumJukeboxTrackPacket.class, SHARED_SET_ALBUM_JUKEBOX_TRACK, EtchedServerPlayPacketHandler::handleSetAlbumJukeboxTrack);
        client_register(SetAlbumJukeboxTrackPacket.class, SHARED_SET_ALBUM_JUKEBOX_TRACK, EtchedClientPlayPacketHandler::handleSetAlbumJukeboxTrack);
        /*register(ClientboundInvalidEtchUrlPacket.class, ClientboundInvalidEtchUrlPacket::new, NetworkDirection.PLAY_TO_CLIENT);
        register(ClientboundPlayEntityMusicPacket.class, ClientboundPlayEntityMusicPacket::new, NetworkDirection.PLAY_TO_CLIENT);
        register(ClientboundPlayMusicPacket.class, ClientboundPlayMusicPacket::new, NetworkDirection.PLAY_TO_CLIENT);
        register(ClientboundSetUrlPacket.class, ClientboundSetUrlPacket::new, NetworkDirection.PLAY_TO_CLIENT);
        register(ServerboundSetUrlPacket.class, ServerboundSetUrlPacket::new, NetworkDirection.PLAY_TO_SERVER);
        register(ServerboundEditMusicLabelPacket.class, ServerboundEditMusicLabelPacket::new, NetworkDirection.PLAY_TO_SERVER);
        register(SetAlbumJukeboxTrackPacket.class, SetAlbumJukeboxTrackPacket::new, null); // Bidirectional
        */
    }

    private static <MSG extends EtchedPacket> void client_register(Class<MSG> clazz, ResourceLocation packet_id, EtchedClientPacketHandlerInterface<MSG> packetHandler) {
        ClientPlayNetworking.registerGlobalReceiver(packet_id, (client, handler, buf, responseSender) -> {
            try {
                packetHandler.handle(clazz.getDeclaredConstructor(FriendlyByteBuf.class).newInstance(buf), client);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                LOGGER.error(e);
            }
        });
    }
    private static <MSG extends EtchedPacket> void server_register(Class<MSG> clazz, ResourceLocation packet_id, EtchedServerPacketHandlerInterface<MSG> packetHandler) {
        ServerPlayNetworking.registerGlobalReceiver(packet_id, (server, player, handler, buf, responseSender) -> {
            try {
                packetHandler.handle(clazz.getDeclaredConstructor(FriendlyByteBuf.class).newInstance(buf), server, player);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                LOGGER.error(e);
            }
        });
    } 

}
