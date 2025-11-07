package iq.legit.config; // Pacote mudou

import com.google.gson.*;
import java.nio.file.*;

public class ConfigManager {
    // Caminho do arquivo mudou
    private static final Path CONFIG_PATH = Paths.get("config", "iq-1-21.json"); 
    
    // --- Configurações do seu 'autoLogout' ---
    public static boolean isModEnabled = true;
    public static float healthThreshold = 10.0f;
    public static float swapHealthThreshold = 10.0f;
    public static boolean dropFunctionActive = true;
    public static boolean halfHpEnabled = true;
    public static boolean swapHandsEnabled = true;
    public static int minCps = 11;
    public static int maxCps = 13;

    // --- ADICIONEI AS CONFIGURAÇÕES DAS SUAS FEATURES ---
    public static boolean partyJoinSound = true;
    public static boolean alreadyPicking = true;
    public static boolean manaDrainNotify = true;
    public static boolean backboneAlert = true;
    // (Você pode adicionar o resto das suas settings aqui)
    // ---

    public static void loadConfig() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                String content = Files.readString(CONFIG_PATH);
                JsonObject json = JsonParser.parseString(content).getAsJsonObject();
                
                // Carrega 'autoLogout'
                isModEnabled = json.get("enabled").getAsBoolean();
                healthThreshold = json.get("threshold").getAsFloat();
                swapHealthThreshold = json.has("swapHealthThreshold") ? json.get("swapHealthThreshold").getAsFloat() : 10.0f;
                dropFunctionActive = json.get("dropFunctionActive").getAsBoolean();
                minCps = json.get("minCps").getAsInt();
                maxCps = json.get("maxCps").getAsInt();
                halfHpEnabled = json.has("halfHpEnabled") ? json.get("halfHpEnabled").getAsBoolean() : true;
                swapHandsEnabled = json.has("swapHandsEnabled") ? json.get("swapHandsEnabled").getAsBoolean() : true;

                // Carrega suas settings
                partyJoinSound = json.has("partyJoinSound") ? json.get("partyJoinSound").getAsBoolean() : true;
                alreadyPicking = json.has("alreadyPicking") ? json.get("alreadyPicking").getAsBoolean() : true;
                manaDrainNotify = json.has("manaDrainNotify") ? json.get("manaDrainNotify").getAsBoolean() : true;
                backboneAlert = json.has("backboneAlert") ? json.get("backboneAlert").getAsBoolean() : true;

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

            // Salva 'autoLogout'
            json.addProperty("enabled", isModEnabled);
            json.addProperty("threshold", healthThreshold);
            json.addProperty("swapHealthThreshold", swapHealthThreshold);
            json.addProperty("dropFunctionActive", dropFunctionActive);
            json.addProperty("minCps", minCps);
            json.addProperty("maxCps", maxCps);
            json.addProperty("halfHpEnabled", halfHpEnabled);
            json.addProperty("swapHandsEnabled", swapHandsEnabled);

            // Salva suas settings
            json.addProperty("partyJoinSound", partyJoinSound);
            json.addProperty("alreadyPicking", alreadyPicking);
            json.addProperty("manaDrainNotify", manaDrainNotify);
            json.addProperty("backboneAlert", backboneAlert);
            
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