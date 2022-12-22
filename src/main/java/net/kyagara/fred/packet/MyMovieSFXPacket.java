package net.kyagara.fred.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kyagara.fred.sound.ModSounds;
import net.kyagara.fred.stat.ModStatistics;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

public class MyMovieSFXPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player,
            ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {

        ServerWorld world = player.getWorld();

        world.playSoundFromEntity(null, player, ModSounds.MY_MOVIE_SFX, SoundCategory.PLAYERS, 0.7F, 1F);

        player.incrementStat(ModStatistics.MY_MOVIE_COUNT);
    }
}