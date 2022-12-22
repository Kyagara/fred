package net.kyagara.fred.packet;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kyagara.fred.Main;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static final Identifier MY_MOVIE_SFX_PACKET = new Identifier(Main.MOD_ID, "my_movie_packet");
    public static final Identifier LINK_ITEM_PACKET = new Identifier(Main.MOD_ID, "link_item_packet");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(MY_MOVIE_SFX_PACKET, MyMovieSFXPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(LINK_ITEM_PACKET, LinkItemPacket::receive);
    }

    public static void registerModPackets() {
        Main.LOGGER.debug("Registering packets from " + Main.MOD_ID);
        registerC2SPackets();
    }
}
