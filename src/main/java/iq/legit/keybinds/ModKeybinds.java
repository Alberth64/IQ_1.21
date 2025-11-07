package iq.legit.keybinds; // Pacote mudou

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class ModKeybinds {
    public static KeyBinding DROP_KEY;
    public static KeyBinding TOGGLE_FUNCTION_KEY;
    public static KeyBinding OPEN_CONFIG_KEY;
    public static KeyBinding INCREASE_THRESHOLD_KEY;
    public static KeyBinding DECREASE_THRESHOLD_KEY;
    public static KeyBinding TOGGLE_HALF_HP_KEY;
    public static KeyBinding TOGGLE_SPACE_SPAMMER_KEY;
    
    // Categoria de Keybind mudou
    private static final String CATEGORY = "category.iq-1-21";

    public static void register() {
        DROP_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.iq-1-21.drop", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_Q, CATEGORY
        ));
        
        TOGGLE_FUNCTION_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.iq-1-21.toggle", InputUtil.Type.KEYSYM, -1, CATEGORY
        ));
        
        OPEN_CONFIG_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.iq-1-21.config", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_O, CATEGORY
        ));
        
        INCREASE_THRESHOLD_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.iq-1-21.increase_threshold", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_UP, CATEGORY
        ));
        
        DECREASE_THRESHOLD_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.iq-1-21.decrease_threshold", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_DOWN, CATEGORY
        ));
        
        TOGGLE_HALF_HP_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.iq-1-21.toggle_halfhp", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_H, CATEGORY
        ));
        
        TOGGLE_SPACE_SPAMMER_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.iq-1-21.toggle_spacespammer", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_PERIOD, CATEGORY
        ));
    }
}