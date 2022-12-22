package net.kyagara.fred.keybind;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kyagara.fred.mixin.client.accessor.HandledScreenAccessor;
import net.kyagara.fred.packet.ModPackets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.Slot;

public class LinkItemKeybind {
    public static void SendLinkItemPacket(MinecraftClient client) {
        if (client.currentScreen == null || !(client.currentScreen instanceof HandledScreen)) {
            return;
        }

        Slot focusedSlot = ((HandledScreenAccessor) client.currentScreen).getFocusedSlot();

        if (focusedSlot != null && focusedSlot.hasStack()) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeText(focusedSlot.getStack().toHoverableText());

            ClientPlayNetworking.send(ModPackets.LINK_ITEM_PACKET, buf);
        }
    }
}
