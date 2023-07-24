package net.kyagara.fred.event;

import net.kyagara.fred.Fred;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class LeavingWorldEvent {
	public static void SendChatSessionSeparator(MinecraftClient client) {
		client.inGameHud.getChatHud().addMessage(Text.empty());
		client.inGameHud.getChatHud().addMessage(Text.literal(Fred.CONFIG.chatSessionSeparator()).styled(style -> style.withColor(Fred.CONFIG.chatSessionSeparatorColor().rgb())));
		client.inGameHud.getChatHud().addMessage(Text.empty());
	}
}
