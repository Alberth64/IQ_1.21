package iq.legit.utils;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;

public class LocationUtils {

    private static String currentLocation = "";

    public static void init() {
        // Escuta a Action Bar
        ClientReceiveMessageEvents.ACTION_BAR.register((message, overlay) -> {
            String actionBarText = message.getString();
            
            // O texto da localização do Hypixel geralmente tem "§" (códigos de cor)
            // Esta é uma checagem simples, pode precisar de ajuste
            if (actionBarText.contains("§7Kuudra's Hollow")) {
                currentLocation = "Kuudra's Hollow";
            } else if (!actionBarText.contains("§")) {
                // Se a action bar não tiver códigos de cor, provavelmente não é
                // uma mensagem de localização, então limpamos.
                currentLocation = "";
            }
        });
    }

    /**
     * Verifica se estamos em Kuudra's Hollow.
     */
    public static boolean isInKuudra() {
        return currentLocation.equals("Kuudra's Hollow");
    }

    /**
     * O 'direction.js' original usava este tamanho (15)
     */
    public static final int KUUDRA_SPAWN_SIZE = 15;

    /**
     * O 'kuudrahitbox.js' (glow) original usava este tamanho (15)
     * (O 'getSize()' do Skyblocker usa 15)
     */
    public static final int KUUDRA_MAIN_SIZE = 15;
}