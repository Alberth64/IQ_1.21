package iq.legit.config; // Pacote mudou

import com.google.gson.*;
import java.nio.file.*;

public class ConfigManager {
    // Caminho do arquivo mudou
    private static final Path CONFIG_PATH = Paths.get("config", "iq-1-21.json"); 
    
    // --- ADICIONEI AS CONFIGURAÇÕES DAS SUAS FEATURES ---
    public static boolean partyJoinSound = true;
    public static boolean alreadyPicking = true;
    public static boolean manaDrainNotify = true;
    // (Você pode adicionar o resto das suas settings aqui)
    // ---

    public static void loadConfig() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                String content = Files.readString(CONFIG_PATH);
                JsonObject json = JsonParser.parseString(content).getAsJsonObject();
                // Carrega suas settings
                partyJoinSound = json.has("partyJoinSound") ? json.get("partyJoinSound").getAsBoolean() : true;
                alreadyPicking = json.has("alreadyPicking") ? json.get("alreadyPicking").getAsBoolean() : true;
                manaDrainNotify = json.has("manaDrainNotify") ? json.get("manaDrainNotify").getAsBoolean() : true;

            } else {
                saveConfig();
            }
        } catch (Exception e) {
            System.err.println("Error loading config: " + e.getMessage());
            saveConfig();
        }
    }

    public static void saveConfig() {
        try {
            JsonObject json = new JsonObject();

            // Salva suas settings
            json.addProperty("partyJoinSound", partyJoinSound);
            json.addProperty("alreadyPicking", alreadyPicking);
            json.addProperty("manaDrainNotify", manaDrainNotify);
            
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(json));
        } catch (Exception e) {
            System.err.println("Error saving config: " + e.getMessage());
        }
    }
}