package net.kyagara.fred.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.kyagara.fred.Main;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ModCommands {
        public static void registerModCommands() {
                Main.LOGGER.debug("Registering commands from " + Main.MOD_ID);

                CommandRegistrationCallback.EVENT
                                .register((dispatcher, registryAccess, environment) -> RollCommand
                                                .register(dispatcher));

                CommandRegistrationCallback.EVENT
                                .register((dispatcher, registryAccess, environment) -> MagicBallCommand
                                                .register(dispatcher));

                CommandRegistrationCallback.EVENT
                                .register((dispatcher, registryAccess, environment) -> QuotesCommand
                                                .register(dispatcher));
        }

        // Returns a Text with a prefix
        public static Text CreateCommandText(String message) {
                return Text.literal("[" + Main.MOD_ID + "] ")
                                .styled(style -> style.withBold(true).withColor(Formatting.BLUE))
                                .append(Text.literal(message)
                                                .styled(style -> style.withBold(false).withColor(Formatting.WHITE)));
        }
}