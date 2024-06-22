package ru.squarecircle.elitertp.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.squarecircle.elitertp.EliteRTP;

public class ChatUtil {
    public static void message(CommandSender player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', EliteRTP.getInstance().getMessages().getString("messages.prefix") + " " + message));
    }
    public static void messageTranslated(CommandSender player, String path) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', EliteRTP.getInstance().getMessages().getString("messages.prefix") + " " + EliteRTP.getInstance().getConfig().getString(path)));
    }
}
