package iq.legit.keybinds; // Pacote mudou

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class ModKeybinds {
    public static KeyBinding OPEN_CONFIG_KEY;
    
    // Categoria de Keybind mudou
    private static final String CATEGORY = "IQ";

    public static void register() {
        OPEN_CONFIG_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Config Menu", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_O, CATEGORY
        ));
        
    }
}