package iq.legit.features;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import iq.legit.config.ConfigManager;

public class AlreadyPicking {

    private static final String ALREADY_PICKING_MESSAGE = "Someone else is currently trying to pick up these supplies!";

    public static void init() {
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            if (!ConfigManager.alreadyPicking) return;

            String chatMessage = message.getString();
            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) return;

            if (chatMessage.contains(ALREADY_PICKING_MESSAGE)) {
                Text title = Text.literal("Already Picking!").formatted(Formatting.RED);
                client.inGameHud.setTitle(title);
                client.inGameHud.setSubtitle(Text.literal(""));
                client.inGameHud.setTitleTicks(0, 25, 0);
            }
        });
    }
}
