package ru.squarecircle.elitertp.core;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import ru.squarecircle.elitertp.EliteRTP;
import ru.squarecircle.elitertp.pluginevents.PlayerRtpEvent;
import ru.squarecircle.elitertp.util.ChatUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RtpAPI {
    public static void rtpDefault(Player player) {
        World world = player.getWorld();
        Location randomLoc = generateDefaultLocation(world);
        int x = randomLoc.getBlockX();
        int z = randomLoc.getBlockZ();
        int y = player.getWorld().getHighestBlockYAt(x, z);
        if(!BlockBlocker.isBlockBlocked(getHighestBlockMaterial(randomLoc))) {
            player.teleport(randomLoc);
            EliteRTP.getInstance().getServer().getPluginManager().callEvent(new PlayerRtpEvent(player, RtpType.DEFAULT, player.getWorld()));
        }
    }
    public static void rtpNear(Player player) {
        List<Player> players = new ArrayList<>(player.getWorld().getPlayers());
        players.remove(player);
        if (players.size() < EliteRTP.getInstance().getConfig().getInt("channels.near.minOnlineForRtp")) {
            ChatUtil.message(player, EliteRTP.getInstance().getMessages().getString("messages.near-tp-none"));
        }
        else {
            Random random = new Random();
            Player randomPlayer = players.get(random.nextInt(players.size()));
            int x = randomPlayer.getLocation().getBlockX() - random.nextInt(100);
            int z = randomPlayer.getLocation().getBlockZ() - random.nextInt(10);
            int y = player.getWorld().getHighestBlockYAt(x, z);
            Location randomLoc = new Location(player.getWorld(), x, y, z);
            if(!BlockBlocker.isBlockBlocked(getHighestBlockMaterial(randomLoc))) {
                player.teleport(randomLoc);
                EliteRTP.getInstance().getServer().getPluginManager().callEvent(new PlayerRtpEvent(player, RtpType.NEAR, player.getWorld()));
            }
        }
    }

    public static void rtpFar(Player player) {
        World world = player.getWorld();
        Location randomLoc = generateFarLocation(world);
        if(!BlockBlocker.isBlockBlocked(getHighestBlockMaterial(randomLoc))) {
            player.teleport(randomLoc);
            EliteRTP.getInstance().getServer().getPluginManager().callEvent(new PlayerRtpEvent(player, RtpType.FAR, world));
        }
    }
    public static void rtpWorld(World world, Player player) {
        Location randomLoc = generateRandomLocation(world, EliteRTP.getInstance().getConfig().getInt("channels.world.radius"));
        if(!BlockBlocker.isBlockBlocked(getHighestBlockMaterial(randomLoc))) {
            player.teleport(randomLoc);
            EliteRTP.getInstance().getServer().getPluginManager().callEvent(new PlayerRtpEvent(player, RtpType.WORLD, world));
        }
        else {
            player.teleport(generateRandomLocation(world, EliteRTP.getInstance().getConfig().getInt("channels.world.radius")));
            EliteRTP.getInstance().getServer().getPluginManager().callEvent(new PlayerRtpEvent(player, RtpType.WORLD, world));
        }
    }

    public static Location generateRandomLocation(World world, int radius) {
        int x = new Random().nextInt(radius);
        int z = new Random().nextInt(radius);
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }
    public static Location generateDefaultLocation(World world) {
        Random random = new Random();
        int x = random.nextInt(EliteRTP.getInstance().getConfig().getInt("channels.default.radius"));
        int z = random.nextInt(EliteRTP.getInstance().getConfig().getInt("channels.default.radius"));
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }
    public static Location generateFarLocation(World world) {
        Random random = new Random();
        int x = random.nextInt(EliteRTP.getInstance().getConfig().getInt("channels.far.radius"));
        int z = random.nextInt(EliteRTP.getInstance().getConfig().getInt("channels.far.radius"));
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }
    public static Material getHighestBlockMaterial(Location location) {
        World world = location.getWorld();
        Block block = world.getHighestBlockAt(location);
        return block.getType();
    }
}