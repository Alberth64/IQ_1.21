package iq.legit;

import iq.legit.features.AlreadyPicking;
import iq.legit.features.ManaDrainNotify;
import iq.legit.features.PartyJoinSound;
import net.fabricmc.api.ClientModInitializer;

import com.terraformersmc.modmenu.util.mod.Mod;

import iq.legit.config.ConfigManager;
import iq.legit.keybinds.ModKeybinds;

public class Iq implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigManager.loadConfig();
        ModKeybinds.register();
        PartyJoinSound.init();
        AlreadyPicking.init();
        ManaDrainNotify.init();
    }
}
