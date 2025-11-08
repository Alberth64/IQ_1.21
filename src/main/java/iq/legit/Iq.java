package iq.legit;

import iq.legit.features.AlreadyPicking;
import iq.legit.features.ManaDrainNotify;
import iq.legit.features.PartyJoinSound;
import iq.legit.features.FreshMessage;
import iq.legit.features.KuudraDirection;

import iq.legit.features.glow.MobGlow;
import iq.legit.features.glow.KuudraGlowAdder;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import com.terraformersmc.modmenu.util.mod.Mod;

import iq.legit.config.ConfigManager;
import iq.legit.keybinds.ModKeybinds;
import iq.legit.config.ModMenuIntegration;
import iq.legit.utils.LocationUtils;

public class Iq implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigManager.loadConfig();
        ModKeybinds.register();
        PartyJoinSound.init();
        AlreadyPicking.init();
        ManaDrainNotify.init();
        FreshMessage.init();
        KuudraDirection.init();

        MobGlow.init();
        KuudraGlowAdder.init(); 

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
        if (ModKeybinds.OPEN_CONFIG_KEY.wasPressed()) {
            client.setScreen(ModMenuIntegration.getConfigScreen().create(client.currentScreen));
        }
    });
    }
}