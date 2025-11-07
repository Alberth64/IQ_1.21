package iq.legit.features;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import iq.legit.config.ConfigManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManaDrainNotify {

    private static final Pattern MANA_DRAIN_PATTERN = Pattern.compile ("Used Extreme Focus!\\s*\\((\\d+)\\s*Mana\\)");

    public static void init() {
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            if (!ConfigManager.manaDrainNotify) return;
            String chatMessage = message.getString();
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.world == null || client.getNetworkHandler() == null) {
                return;
            }
            
            Matcher matcher = MANA_DRAIN_PATTERN.matcher(chatMessage);
            if (matcher.find()) {  
                String mana = matcher.group(1);
                int playersFound = 0;

                for (PlayerEntity player : client.world.getPlayers()) {
                    if (client.player.distanceTo(player) <= 5) {
                        PlayerListEntry playerEntry = client.getNetworkHandler().getPlayerListEntry(player.getUuid());
                        if (playerEntry != null && playerEntry.getLatency() != 1) {
                            playersFound++;

                        }
                    }
                }

                String mensagem = "/pc Used " + mana + " mana on " + playersFound + " players!";
                client.player.networkHandler.sendChatMessage(mensagem);
            }
        });
    }
}