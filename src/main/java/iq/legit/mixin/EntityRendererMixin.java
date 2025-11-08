package iq.legit.mixin;

import iq.legit.features.glow.MobGlow;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Inject(method = "getOutlineColor", at = @At("HEAD"), cancellable = true)
    private void onGetOutlineColor(Entity entity, CallbackInfoReturnable<Integer> cir) {
        
        if (MobGlow.hasOrComputeMobGlow(entity)) {
            cir.setReturnValue(MobGlow.getMobGlow(entity));
        }
    }
}