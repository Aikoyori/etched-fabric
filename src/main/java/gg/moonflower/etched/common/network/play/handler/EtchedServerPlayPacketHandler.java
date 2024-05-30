package gg.moonflower.etched.common.network.play.handler;

import gg.moonflower.etched.common.item.SimpleMusicLabelItem;
import gg.moonflower.etched.common.menu.AlbumJukeboxMenu;
import gg.moonflower.etched.common.menu.EtchingMenu;
import gg.moonflower.etched.common.menu.RadioMenu;
//import gg.moonflower.etched.common.network.EtchedMessages;
import gg.moonflower.etched.common.network.play.ServerboundEditMusicLabelPacket;
import gg.moonflower.etched.common.network.play.ServerboundSetUrlPacket;
import gg.moonflower.etched.common.network.play.SetAlbumJukeboxTrackPacket;
import gg.moonflower.etched.core.registry.EtchedItems;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class EtchedServerPlayPacketHandler {

    public static void handleSetUrl(ServerboundSetUrlPacket pkt, MinecraftServer server, ServerPlayer player) {
        if (player == null) {
            return;
        }

        if (player.containerMenu instanceof EtchingMenu menu) {
            server.execute(() -> menu.setUrl(pkt.url()));
        } else if (player.containerMenu instanceof RadioMenu menu) {
            server.execute(() -> menu.setUrl(pkt.url()));
        }
    }

    public static void handleEditMusicLabel(ServerboundEditMusicLabelPacket pkt, MinecraftServer server, ServerPlayer player) {
        int slot = pkt.slot();
        if (!Inventory.isHotbarSlot(slot) && slot != 40) {
            return;
        }

        if (player == null) {
            return;
        }

        ItemStack labelStack = player.getInventory().getItem(slot);
        if (!labelStack.is(EtchedItems.MUSIC_LABEL.asItem())) {
            return;
        }

        server.execute(() -> {
            SimpleMusicLabelItem.setTitle(labelStack, StringUtils.normalizeSpace(pkt.title()));
            SimpleMusicLabelItem.setAuthor(labelStack, StringUtils.normalizeSpace(pkt.author()));
        });
    }

    public static void handleSetAlbumJukeboxTrack(SetAlbumJukeboxTrackPacket pkt, MinecraftServer server, ServerPlayer player) {
        if (player == null) {
            return;
        }

        if (player.containerMenu instanceof AlbumJukeboxMenu menu) {
            server.execute(() -> {
                ServerLevel level = player.serverLevel();
                if (menu.setPlayingTrack(level, pkt)) {
                    var packet = new SetAlbumJukeboxTrackPacket(pkt.playingIndex(), pkt.track());
                    packet.sendToClients(PlayerLookup.tracking(level, level.getChunkAt(menu.getPos()).getPos()));
                    //EtchedMessages.PLAY.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(menu.getPos())), new SetAlbumJukeboxTrackPacket(pkt.playingIndex(), pkt.track()));
                }
            });
        }
    }
}
