package iq.legit.features.glow;

import iq.legit.config.ConfigManager;
import iq.legit.utils.LocationUtils; // <-- Nosso novo detector
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MagmaCubeEntity;

public class KuudraGlowAdder extends MobGlowAdder {
	
	private static final KuudraGlowAdder INSTANCE = new KuudraGlowAdder();
	private static final int KUUDRA_COLOUR = 0xf7510f; // Laranja/Vermelho

	// init() vazio para forçar o carregamento da classe
	public static void init() {}

	@Override
	public int computeColour(Entity entity) {
		// Esta é a lógica exata do Skyblocker
		return entity instanceof MagmaCubeEntity magmaCube && 
			   ConfigManager.kuudraHitbox && 
			   magmaCube.getSize() == LocationUtils.KUUDRA_MAIN_SIZE 
			   ? KUUDRA_COLOUR : NO_GLOW;
	}

	@Override
	public boolean isEnabled() {
		// Esta é a checagem crucial que faltava
		return LocationUtils.isInKuudra();
	}
}