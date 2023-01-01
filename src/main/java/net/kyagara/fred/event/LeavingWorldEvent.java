package net.kyagara.fred.event;

import net.kyagara.fred.config.FredConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class LeavingWorldEvent {
    public static void SendChatSessionSeparator(MinecraftClient client) {
        client.inGameHud.getChatHud().addMessage(Text.empty());

        client.inGameHud.getChatHud()
                .addMessage(Text.literal(FredConfig.chatSessionSeparator)
                        .styled(style -> style.withColor(Integer.decode(FredConfig.chatSessionSeparatorColor))));

        client.inGameHud.getChatHud().addMessage(Text.empty());
    }
}
