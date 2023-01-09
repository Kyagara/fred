package net.kyagara.fred.event;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.WorldSavePath;

import java.util.List;

public class JoiningWorldEvent {
	public static void SendJoinMessage(MinecraftClient client) {
		String worldName;

		boolean isServer = (client.world != null ? client.world.getServer() : null) != null || client.getCurrentServerEntry() != null;

		if (isServer) {
			worldName = client.getCurrentServerEntry().name;
		} else {
			worldName = client.getServer().getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString();
		}

		String day = Long.toString(client.world.getTime() / 24000);
		String difficulty = client.world.getDifficulty().getName();
		difficulty = difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1);

		client.inGameHud.getChatHud().addMessage(getRow(worldName, day, difficulty));

		if (isServer) {
			List<Text> playerListSummary = client.getCurrentServerEntry().playerListSummary;

			String count = Integer.toString(playerListSummary == null ? 0 : playerListSummary.size());
			String ping = Long.toString(client.getCurrentServerEntry().ping);

			client.inGameHud.getChatHud().addMessage(getField("Players: ", count).append("  ").append(getField("Ping: ", ping)));
		}

		client.inGameHud.getChatHud().addMessage(Text.empty());
	}

	private static Text getRow(String value1, String value2, String value3) {
		MutableText col1 = Text.literal(value1).styled(style -> style.withBold(true));
		MutableText col2 = getField("Day: ", value2);
		MutableText col3 = getField("Difficulty: ", value3);

		return col1.append("  ").append(col2).append("  ").append(col3);
	}

	private static MutableText getField(String name, String value) {
		return Text.literal(name).styled(style -> style.withBold(true)).append(Text.literal(value).styled(style -> style.withBold(false)));
	}
}
