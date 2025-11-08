package iq.legit.features.glow;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.Entity;
import java.util.ArrayList;
import java.util.List;

public class MobGlow {
	public static final int NO_GLOW = 0;
	private static final List<MobGlowAdder> ADDERS = new ArrayList<>();
	private static final Object2IntMap<Entity> CACHE = new Object2IntOpenHashMap<>();

	public static void init() {
		// Limpa o cache a cada tick
		ClientTickEvents.END_WORLD_TICK.register(client -> clearCache());
	}

	protected static void registerGlowAdder(MobGlowAdder adder) {
		ADDERS.add(adder);
	}

	public static boolean hasOrComputeMobGlow(Entity entity) {
		if (CACHE.containsKey(entity)) {
			return true;
		}
		int color = computeMobGlow(entity);
		if (color != NO_GLOW) {
			CACHE.put(entity, color);
			return true;
		}
		return false;
	}

	public static int getMobGlow(Entity entity) {
		return CACHE.getInt(entity);
	}

	public static void clearCache() {
		CACHE.clear();
	}

	private static int computeMobGlow(Entity entity) {
		for (MobGlowAdder adder : ADDERS) {
			// Este é o passo crucial que estava faltando:
			if (adder.isEnabled()) { 
				int glowColour = adder.computeColour(entity);
				if (glowColour != NO_GLOW) return glowColour;
			}
		}
		return NO_GLOW;
	}
}