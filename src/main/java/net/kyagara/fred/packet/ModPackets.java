package net.kyagara.fred.packet;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kyagara.fred.Fred;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static final Identifier LINK_ITEM_PACKET = new Identifier(Fred.MOD_ID, "link_item_packet");

    public static void registerC2SPackets() {
        if (Fred.CONFIG.enableLinkItemInChat()) {
            ServerPlayNetworking.registerGlobalReceiver(LINK_ITEM_PACKET, LinkItemPacket::receive);
        }
    }

    public static void registerModPackets() {
        Fred.LOGGER.debug("Registering packets from " + Fred.MOD_ID);
        registerC2SPackets();
    }
}
