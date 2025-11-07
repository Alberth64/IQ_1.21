package iq.legit.features;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;

public class ManaDrainNotify {

    public static void init() {
        ClientReceiveMessageEvents.CHAT.register((message, signedMessage, sender, params, receptionTimestamp) -> {
            if (message.getString().contains("Used Extreme Focus!")) {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client.player != null) {
                    client.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 2.0f, 1.0f);
                }
            }
        });
    }
}
