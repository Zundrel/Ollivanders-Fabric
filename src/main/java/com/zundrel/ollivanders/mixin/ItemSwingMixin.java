package com.zundrel.ollivanders.mixin;

import com.zundrel.ollivanders.api.event.ItemSwingCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class ItemSwingMixin {
    @Inject(method = "swingHand", at = @At("HEAD"), cancellable = false)
    public void swingHand(final Hand hand, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity instanceof PlayerEntity)
            ItemSwingCallback.EVENT.invoker().interact((PlayerEntity) livingEntity);
    }

//    /**
//     * Added a proper swing event into method_12511.
//     * @author Zundrel
//     * @reason Activates the ServerChatCallback.
//     */
//    @Overwrite
//    public void method_12511(ServerPlayPacketListener serverPlayPacketListener_1) {
//        serverPlayPacketListener_1.onHandSwing(new HandSwingC2SPacket(hand));
//        if (serverPlayPacketListener_1 instanceof ServerPlayNetworkHandler) {
//            ServerPlayNetworkHandler serverPlayNetworkHandler = (ServerPlayNetworkHandler) serverPlayPacketListener_1;
//            ActionResult result = ItemSwingCallback.EVENT.invoker().interact(serverPlayNetworkHandler.player);
//        }
//    }
}
