package net.kyagara.fred.event;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class LeavingWorldEvent {
    public static void ClearChat(MinecraftClient client) {
        client.inGameHud.getChatHud().addMessage(Text.empty());

        client.inGameHud.getChatHud()
                .addMessage(Text.literal("------------------------------------")
                        .styled(style -> style.withColor(Formatting.AQUA)));

        client.inGameHud.getChatHud().addMessage(Text.empty());
    }
}
