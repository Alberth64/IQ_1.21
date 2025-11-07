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
                .setTitle(Text.literal("IQ Settings")) // Título mudou
                .setSavingRunnable(() -> {
                    ConfigManager.saveConfig();
                    KeyBinding.updateKeysByCode();
                });

            ConfigEntryBuilder entry = builder.entryBuilder();
            
            // --- General Features ---
            builder.getOrCreateCategory(Text.literal("General Features"))
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
                    .build());

            builder.getOrCreateCategory(Text.literal("Keybinds"))
                .addEntry(entry.startKeyCodeField(Text.literal("Open Config Menu"), ModKeybinds.OPEN_CONFIG_KEY.getDefaultKey())
                    .setKeySaveConsumer(key -> ModKeybinds.OPEN_CONFIG_KEY.setBoundKey(key))
                    .build());

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