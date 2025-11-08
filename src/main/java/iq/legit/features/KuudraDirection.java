package iq.legit.features;

import iq.legit.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class KuudraDirection {

    public static void init() {
        // Este é o 'register("tick", ...)'
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            
            // 1. Verifica se a feature está ligada
            if (!ConfigManager.kuudraDirection) return;
            
            if (client.world == null || client.player == null) return;

            // 2. Procura por Kuudra
            MagmaCubeEntity kuudra = findKuudra(client);

            if (kuudra != null) {
                float currentHP = kuudra.getHealth();

                // 3. KUUDRA SPAWN DETECT (lógica do .js)
                if (currentHP <= 25000 && currentHP > 24900) {
                    double x = kuudra.getX();
                    double z = kuudra.getZ();

                    // 4. Mostra o título
                    showDirectionTitle(client, x, z);
                }
            }
        });
    }

    /**
     * Itera por todos os MagmaCubes e encontra o que corresponde ao Kuudra.
     */
    private static MagmaCubeEntity findKuudra(MinecraftClient client) {
        for (Entity entity : client.world.getEntities()) {
            if (entity instanceof MagmaCubeEntity) {
                MagmaCubeEntity cube = (MagmaCubeEntity) entity;

                // Lógica do .js: width.toFixed(0) == 15 && HP <= 100000
                if (Math.round(cube.getWidth()) == 15 && cube.getHealth() <= 100000) {
                    return cube; // Encontramos o Kuudra
                }
            }
        }
        return null; // Kuudra não encontrado
    }

    /**
     * Mostra o título na tela com base nas coordenadas.
     */
    private static void showDirectionTitle(MinecraftClient client, double x, double z) {
        Text title = null;

        if (x < -128) {
            title = Text.literal("RIGHT!").formatted(Formatting.RED, Formatting.BOLD);
        } else if (z > -84) {
            title = Text.literal("FRONT!").formatted(Formatting.DARK_GREEN, Formatting.BOLD);
        } else if (x > -72) {
            title = Text.literal("LEFT!").formatted(Formatting.GREEN, Formatting.BOLD);
        } else if (z < -132) {
            title = Text.literal("BACK!").formatted(Formatting.DARK_RED, Formatting.BOLD);
        }

        if (title != null) {
            client.inGameHud.setTitle(title);
            client.inGameHud.setSubtitle(Text.literal(""));
            client.inGameHud.setTitleTicks(0, 25, 5); // (fadeIn, stay, fadeOut)
        }
    }
}