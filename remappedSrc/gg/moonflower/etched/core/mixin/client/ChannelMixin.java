package gg.moonflower.etched.core.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.sound.AudioStream;
import net.minecraft.client.sound.Source;
import net.minecraft.client.sound.StaticSound;

@Mixin(Source.class)
public class ChannelMixin {

    @Unique
    private final AtomicBoolean etched$loaded = new AtomicBoolean(false);
    @Unique
    private final AtomicBoolean etched$stopped = new AtomicBoolean(false);

    @Inject(method = "stop", at = @At("HEAD"))
    public void stop(CallbackInfo ci) {
        if (!this.etched$loaded.get()) {
            this.etched$stopped.set(true);
        }
    }

    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    public void play(CallbackInfo ci) {
        if (this.etched$stopped.get()) {
            this.etched$stopped.set(false);
            ci.cancel();
        }
    }

    @Inject(method = "stopped", at = @At("TAIL"), cancellable = true)
    public void stopped(CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() && this.etched$stopped.get()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "attachStaticBuffer", at = @At("HEAD"))
    public void attachStaticBuffer(StaticSound soundBuffer, CallbackInfo ci) {
        this.etched$loaded.set(true);
    }

    @Inject(method = "attachBufferStream", at = @At("HEAD"))
    public void attachBufferStream(AudioStream audioStream, CallbackInfo ci) {
        this.etched$loaded.set(true);
    }
}
