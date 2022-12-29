package net.kyagara.fred.event;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.WorldSavePath;

public class JoiningWorldEvent {
    public static void SendJoinMessage(MinecraftClient client) {
        String worldName;

        boolean isServer = client.world.getServer() != null || client.getCurrentServerEntry() != null;

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

    private static Text getRow(String value1, String name2, String value2, String name3, String value3) {
        MutableText col1 = Text.literal(value1).styled(style -> style.withBold(true));
        MutableText col2 = getField(name2, value2);
        MutableText col3 = getField(name3, value3);

        return col1.append("  ").append(col2).append("  ").append(col3);
    }

    private static MutableText getField(String name, String value) {
        return Text.literal(name).styled(style -> style.withBold(true))
                .append(Text.literal(value).styled(style -> style.withBold(false)));
    }
}
