package net.kyagara.fred;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.kyagara.fred.command.ModCommands;
import net.kyagara.fred.config.FredConfig;
import net.kyagara.fred.keybind.ModKeybinds;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.WorldSavePath;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeybinds.registerModKeybinds();
        ModCommands.registerClientCommands();

        // Events
        playConnectionEvents();
    }

    private void playConnectionEvents() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (!FredConfig.clearChat) {
                client.inGameHud.getChatHud().addMessage(Text.empty());

                client.inGameHud.getChatHud()
                        .addMessage(Text.literal("------------------------------------")
                                .styled(style -> style.withColor(Formatting.AQUA)));

                client.inGameHud.getChatHud().addMessage(Text.empty());
            }

            if (FredConfig.joinMessage) {
                String worldName;

                boolean isServer = client.world.getServer() != null;

                if (isServer) {
                    worldName = client.getCurrentServerEntry().name;
                } else {
                    worldName = client.getServer().getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString();
                }

                String day = Long.toString(client.world.getTime() / 24000);
                String difficulty = client.world.getDifficulty().getName();
                difficulty = difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1);

                client.inGameHud.getChatHud()
                        .addMessage(getRow(worldName, "Day: ", day, "Difficulty: ", difficulty));

                if (isServer) {
                    String count = Integer.toString(client.getCurrentServerEntry().playerListSummary.size());
                    String ping = Long.toString(client.getCurrentServerEntry().ping);

                    client.inGameHud.getChatHud()
                            .addMessage(getField("Players: ", count).append("  ").append(getField("Ping: ", ping)));
                }

                client.inGameHud.getChatHud().addMessage(Text.empty());
            }
        });
    }

    private Text getRow(String value1, String name2, String value2, String name3, String value3) {
        MutableText col1 = Text.literal(value1).styled(style -> style.withBold(true));
        MutableText col2 = getField(name2, value2);
        MutableText col3 = getField(name3, value3);

        return col1.append("  ").append(col2).append("  ").append(col3);
    }

    private MutableText getField(String name, String value) {
        return Text.literal(name).styled(style -> style.withBold(true))
                .append(Text.literal(value).styled(style -> style.withBold(false)));
    }
}