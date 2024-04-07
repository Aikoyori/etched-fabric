package gg.moonflower.etched.client.sound;

import javax.sound.sampled.AudioFormat;
import net.minecraft.client.sound.AudioStream;
import java.nio.ByteBuffer;

/**
 * Hack because Minecraft doesn't properly handle sound exceptions.
 *
 * @author Ocelot
 */
public enum EmptyAudioStream implements AudioStream {

    INSTANCE;

    private static final AudioFormat FORMAT = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 22050, 8, 1, 4, 1, true);

    @Override
    public AudioFormat getFormat() {
        return FORMAT;
    }

    @Override
    public ByteBuffer getBuffer(int i) {
        return ByteBuffer.allocateDirect(0);
    }

    @Override
    public void close() {
    }
}
