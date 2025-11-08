package iq.legit.features.glow;

import net.minecraft.entity.Entity;

public abstract class MobGlowAdder {
	protected static final int NO_GLOW = MobGlow.NO_GLOW;

	protected MobGlowAdder() {
		MobGlow.registerGlowAdder(this);
	}

	public abstract int computeColour(Entity entity);
	public abstract boolean isEnabled();
}