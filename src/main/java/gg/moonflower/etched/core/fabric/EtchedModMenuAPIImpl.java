package gg.moonflower.etched.core.fabric;

import com.terraformersmc.modmenu.ModMenuModMenuCompat;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import dev.isxander.yacl3.api.*;
import net.minecraft.network.chat.Component;

public class EtchedModMenuAPIImpl extends ModMenuModMenuCompat {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> (EtchedConfig.HANDLER.instance().getConfigScreen(screen));
    }
}
