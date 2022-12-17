package net.kyagara.fred.packets;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kyagara.fred.Main;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static final Identifier MY_MOVIE_SFX_PACKET = new Identifier(Main.MOD_ID, "my_movie_packet");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(MY_MOVIE_SFX_PACKET, MyMovieSFXPacket::receive);
    }

    public static void registerModPackets() {
        Main.LOGGER.debug("Registering packets from " + Main.MOD_ID);
    }
}
