package iq.legit.features;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;

// Importe o ConfigManager
import iq.legit.config.ConfigManager;

import java.util.regex.Pattern;

public class PartyJoinSound {
    
    private static final Pattern PARTY_JOIN_PATTERN = Pattern.compile ("joined the party.");

    public static void init() {
        ClientReceiveMessageEvents.CHAT.register((message, signedMessage, sender, params, receptionTimestamp) -> {
            
            // ADICIONE ESTA VERIFICAÇÃO:
            // Se a feature estiver desligada no config, não faça nada.
            if (!ConfigManager.partyJoinSound) return;

            String chatMessage = message.getString();
            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) return;

            if (PARTY_JOIN_PATTERN.matcher(chatMessage).matches()) {
                client.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 2.0f, 1.0f);
            }
        });
    }
}