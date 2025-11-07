package iq.legit.features;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import iq.legit.config.ConfigManager;
import java.util.regex.Pattern;

public class PartyJoinSound {
    
    private static final Pattern PARTY_JOIN_PATTERN = Pattern.compile ("joined the party.");

    public static void init() {
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            
            if (!ConfigManager.partyJoinSound) return;

            String chatMessage = message.getString();
            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) return;

            if (PARTY_JOIN_PATTERN.matcher(chatMessage).find()) {
                client.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 5.0f, 4.0f);
            }
        });
    }
}