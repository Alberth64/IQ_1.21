package iq.legit.features;

import iq.legit.config.ConfigManager;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;

public class FreshMessage {

    private static final String FRESH_CRITERIA = "Your Fresh Tools Perk bonus doubles your building speed for the next 10 seconds!";

    public static void init() {
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            
            // 1. Verifica se a feature está ligada
            if (!ConfigManager.freshMessage) return;

            String chatMessage = message.getString();
            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null || client.player.networkHandler == null) return;

            // 2. Verifica se a mensagem é a correta
            if (chatMessage.contains(FRESH_CRITERIA)) {
                
                // 3. Envia a mensagem no party chat
                // Nota: A porcentagem (%bp) será adicionada quando
                // traduzirmos os scripts de leitura do Kuudra.
                client.player.networkHandler.sendChatMessage("/pc FRESH!");
            }
        });
    }
 }
