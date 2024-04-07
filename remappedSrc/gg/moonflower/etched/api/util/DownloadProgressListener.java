package gg.moonflower.etched.api.util;

import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

/**
 * @author Ocelot
 */
public interface DownloadProgressListener {

    /**
     * Called when a request is started.
     *
     * @param component The component to display
     */
    void progressStartRequest(Text component);

    /**
     * Called when data download begins.
     *
     * @param size The total size of the file in megabytes.
     */
    void progressStartDownload(float size);

    /**
     * Called each time a byte is downloaded.
     *
     * @param percentage The percent downloaded for the file
     */
    void progressStagePercentage(int percentage);

    /**
     * Called each time a byte is downloaded.
     *
     * @param percentage The percent downloaded for the file
     */
    default void progressStage(float percentage) {
        this.progressStagePercentage((int) (MathHelper.clamp(percentage, 0.0F, 1.0F) * 100.0F));
    }

    /**
     * Called when the file starts loading.
     */
    void progressStartLoading();

    /**
     * Called when the file successfully loads.
     */
    void onSuccess();

    /**
     * Called when there is an error and the file fails to load.
     */
    void onFail();
}
