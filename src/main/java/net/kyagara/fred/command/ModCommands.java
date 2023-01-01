package net.kyagara.fred.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.kyagara.fred.Main;
import net.kyagara.fred.config.FredConfig;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ModCommands {
        public static void registerClientCommands() {
                ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
                        ShrugCommand.register(dispatcher);
                        FlipTableCommand.register(dispatcher);
                        UnflipTableCommand.register(dispatcher);
                });
        }

        public static void registerCommands() {
                CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
                        if (FredConfig.enableRollCommand) {
                                RollCommand.register(dispatcher);
                        }

                        if (FredConfig.enableMagicBallCommand) {
                                MagicBallCommand.register(dispatcher);
                        }

                        if (FredConfig.enableQuotesCommand) {
                                QuotesCommand.register(dispatcher);
                        }
                });
        }

        // Returns a Text with a prefix
        // This looks horrible
        public static Text CreateCommandText(String message) {
                return Text.literal("[" + Main.MOD_ID + "] ")
                                .styled(style -> style.withBold(true).withColor(Formatting.BLUE))
                                .append(Text.literal(message)
                                                .styled(style -> style.withBold(false).withColor(Formatting.WHITE)));
        }

        public static void registerModCommands() {
                Main.LOGGER.debug("Registering commands from " + Main.MOD_ID);
                registerCommands();
        }
}