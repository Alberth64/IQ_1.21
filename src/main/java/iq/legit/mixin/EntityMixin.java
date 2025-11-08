package iq.legit.mixin;

import iq.legit.features.glow.MobGlow;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "isGlowing", at = @At("HEAD"), cancellable = true)
    private void onIsGlowing(CallbackInfoReturnable<Boolean> cir) {
        
        Entity entity = (Entity)(Object)this;
        if (MobGlow.hasOrComputeMobGlow(entity)) {
            cir.setReturnValue(true);
        }
    }
}