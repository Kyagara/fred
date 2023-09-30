package net.kyagara.fred.event;

import io.wispforest.owo.ui.core.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LeavingWorldEvent {
	public static void SendChatSessionSeparator(MinecraftClient client) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		client.inGameHud.getChatHud().addMessage(Text.literal("---------------- " + formatter.format(date) + " ----------------").styled(style -> style.withColor(Color.ofRgb(0x55FFFF).rgb())));
	}
}
