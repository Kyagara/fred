package net.kyagara.fred.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class LinkItemPacket {
	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler ignoredHandler, PacketByteBuf buf, PacketSender ignoredResponseSender) {
		Text text = Text.translatable("misc.fred.link_item", player.getDisplayName(), buf.readText());

		server.getPlayerManager().broadcast(text, false);
	}
}