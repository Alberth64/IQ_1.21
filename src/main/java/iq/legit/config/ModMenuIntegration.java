package iq.legit.config; // Pacote mudou

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

// Importações mudaram para nossos novos pacotes
import iq.legit.config.ConfigManager; 
import iq.legit.keybinds.ModKeybinds;

public class ModMenuIntegration implements ModMenuApi {
    private static ConfigScreenFactory<?> configScreenFactory;
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        configScreenFactory = createConfigScreenFactory();
        return configScreenFactory;
    }
    
    private static ConfigScreenFactory<?> createConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("IQ-1-21 Settings")) // Título mudou
                .setSavingRunnable(() -> {
                    ConfigManager.saveConfig();
                    KeyBinding.updateKeysByCode();
                });

            ConfigEntryBuilder entry = builder.entryBuilder();
            
            // --- Categoria para SUAS features ---
            builder.getOrCreateCategory(Text.literal("IQ Features"))
                .addEntry(entry.startBooleanToggle(Text.literal("Party Join Sound"), ConfigManager.partyJoinSound)
                    .setDefaultValue(true)
                    .setSaveConsumer(val -> ConfigManager.partyJoinSound = val)
                    .build())
                .addEntry(entry.startBooleanToggle(Text.literal("Already Picking Alert"), ConfigManager.alreadyPicking)
                    .setDefaultValue(true)
                    .setSaveConsumer(val -> ConfigManager.alreadyPicking = val)
                    .build())
                .addEntry(entry.startBooleanToggle(Text.literal("Mana Drain Notify"), ConfigManager.manaDrainNotify)
                    .setDefaultValue(true)
                    .setSaveConsumer(val -> ConfigManager.manaDrainNotify = val)
                    .build())
                .addEntry(entry.startBooleanToggle(Text.literal("Backbone Alert"), ConfigManager.backboneAlert)
                    .setDefaultValue(true)
                    .setSaveConsumer(val -> ConfigManager.backboneAlert = val)
                    .build());
            
            // --- Categoria para as features 'autoLogout' ---
            builder.getOrCreateCategory(Text.literal("AutoDrop Settings"))
                .addEntry(entry.startBooleanToggle(Text.literal("Mod Enabled"), ConfigManager.isModEnabled)
                    .setDefaultValue(true)
                    .setSaveConsumer(val -> ConfigManager.isModEnabled = val)
                    .setTooltip(Text.literal("Enable or disable the entire mod"))
                    .build())
                // ... (o resto das settings de 'autoLogout'...)
                .addEntry(entry.startBooleanToggle(Text.literal("Auto Drop Function"), ConfigManager.dropFunctionActive)
                    .setDefaultValue(true)
                    .setSaveConsumer(val -> ConfigManager.dropFunctionActive = val)
                    .build())
                .addEntry(entry.startBooleanToggle(Text.literal("Half HP Drop"), ConfigManager.halfHpEnabled)
                    .setDefaultValue(true)
                    .setSaveConsumer(val -> ConfigManager.halfHpEnabled = val)
                    .build())
                .addEntry(entry.startFloatField(Text.literal("Health Threshold"), ConfigManager.healthThreshold)
                    .setDefaultValue(10.0f)
                    .setSaveConsumer(val -> ConfigManager.healthThreshold = val)
                    .build());

            // ... (O resto das categorias: Swap Hands, SpaceSpammer, Keybinds) ...
            // (Você pode adicionar/remover o que quiser daqui)
            
            builder.getOrCreateCategory(Text.literal("SpaceSpammer Settings"))
                .addEntry(entry.startIntSlider(Text.literal("Min CPS"), ConfigManager.minCps, 1, 20)
                    .setDefaultValue(11)
                    .setSaveConsumer(val -> ConfigManager.minCps = val)
                    .build())
                .addEntry(entry.startIntSlider(Text.literal("Max CPS"), ConfigManager.maxCps, 1, 20)
                    .setDefaultValue(13)
                    .setSaveConsumer(val -> ConfigManager.maxCps = val)
                    .build());

            builder.getOrCreateCategory(Text.literal("Keybinds"))
                .addEntry(entry.startKeyCodeField(Text.literal("Open Config Menu"), ModKeybinds.OPEN_CONFIG_KEY.getDefaultKey())
                    .setKeySaveConsumer(key -> ModKeybinds.OPEN_CONFIG_KEY.setBoundKey(key))
                    .build());
            // ... (o resto das keybinds)

            return builder.build();
        };
    }
    
    public static ConfigScreenFactory<?> getConfigScreen() {
        if (configScreenFactory == null) {
            configScreenFactory = createConfigScreenFactory();
        }
        return configScreenFactory;
    }
}