package ru.squarecircle.elitertp.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RtpUtils {
    public static List<Player> getNearbyPlayers(Player player, double radius) {
        List<Player> players = new ArrayList<>();
        for(Player online : Bukkit.getOnlinePlayers()) {
            double distanceSquared = player.getLocation().distanceSquared(online.getLocation()); // Метод distanceSquared для оптимизации вычислений

            if (distanceSquared <= radius * radius) { // Проверяем, находится ли игрок в радиусе
                players.add(online);
            }
        }
        return players;
    }
}
