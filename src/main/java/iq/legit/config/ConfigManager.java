package iq.legit.config;

import com.google.gson.*;
import java.nio.file.*;

public class ConfigManager {
    private static final Path CONFIG_PATH = Paths.get("config", "iq-1-21.json");

    // -----------------------------
    // ⚙️ Suas configurações antigas
    // -----------------------------
    public static boolean partyJoinSound = true;
    public static boolean alreadyPicking = true;
    public static boolean manaDrainNotify = true;
    public static boolean freshMessage = true;
    public static boolean kuudraDirection = true;
    public static boolean kuudraHitbox = true;

    // -----------------------------
    // 🔴 Configurações do KuudraHitbox
    // -----------------------------
    public static float kuudraColorR = 1.0f;  // vermelho padrão
    public static float kuudraColorG = 0.0f;  // verde
    public static float kuudraColorB = 0.0f;  // azul
    public static float kuudraLineWidth = 3.0f; // espessura padrão

    // -----------------------------
    // 📥 Carrega configuração
    // -----------------------------
    public static void loadConfig() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                String content = Files.readString(CONFIG_PATH);
                JsonObject json = JsonParser.parseString(content).getAsJsonObject();

                // ⚙️ Configs antigas
                partyJoinSound = json.has("partyJoinSound") ? json.get("partyJoinSound").getAsBoolean() : true;
                alreadyPicking = json.has("alreadyPicking") ? json.get("alreadyPicking").getAsBoolean() : true;
                manaDrainNotify = json.has("manaDrainNotify") ? json.get("manaDrainNotify").getAsBoolean() : true;
                freshMessage = json.has("freshMessage") ? json.get("freshMessage").getAsBoolean() : true;
                kuudraDirection = json.has("kuudraDirection") ? json.get("kuudraDirection").getAsBoolean() : true;
                kuudraHitbox = json.has("kuudraHitbox") ? json.get("kuudraHitbox").getAsBoolean() : true;

                // 🎨 Cor e espessura do hitbox
                kuudraColorR = json.has("kuudraColorR") ? json.get("kuudraColorR").getAsFloat() : 1.0f;
                kuudraColorG = json.has("kuudraColorG") ? json.get("kuudraColorG").getAsFloat() : 0.0f;
                kuudraColorB = json.has("kuudraColorB") ? json.get("kuudraColorB").getAsFloat() : 0.0f;
                kuudraLineWidth = json.has("kuudraLineWidth") ? json.get("kuudraLineWidth").getAsFloat() : 3.0f;

            } else {
                saveConfig();
            }
        } catch (Exception e) {
            System.err.println("❌ Error loading config: " + e.getMessage());
            saveConfig();
        }
    }

    // -----------------------------
    // 💾 Salva configuração
    // -----------------------------
    public static void saveConfig() {
        try {
            JsonObject json = new JsonObject();

            // ⚙️ Configs antigas
            json.addProperty("partyJoinSound", partyJoinSound);
            json.addProperty("alreadyPicking", alreadyPicking);
            json.addProperty("manaDrainNotify", manaDrainNotify);
            json.addProperty("freshMessage", freshMessage);
            json.addProperty("kuudraDirection", kuudraDirection);
            json.addProperty("kuudraHitbox", kuudraHitbox);

            // 🎨 Cor e espessura do hitbox
            json.addProperty("kuudraColorR", kuudraColorR);
            json.addProperty("kuudraColorG", kuudraColorG);
            json.addProperty("kuudraColorB", kuudraColorB);
            json.addProperty("kuudraLineWidth", kuudraLineWidth);

            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(json));

        } catch (Exception e) {
            System.err.println("❌ Error saving config: " + e.getMessage());
        }
    }
}
