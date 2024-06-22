package ru.squarecircle.elitertp;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.squarecircle.elitertp.commands.*;
import ru.squarecircle.elitertp.core.RtpWorldMenu;
import ru.squarecircle.elitertp.core.UpdateChecker;
import ru.squarecircle.elitertp.events.Events;
import ru.squarecircle.elitertp.events.RtpClickMenuEvent;
import ru.squarecircle.elitertp.events.RtpWorldClickEvent;

import java.io.File;

public final class EliteRTP extends JavaPlugin {
    private static EliteRTP instance;
    private static YamlConfiguration messages;

    @Override
    public void onEnable() {
        instance = this;
        new UpdateChecker(this, 112145).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("Обновлений не найдено");
            } else {
                getLogger().severe("Найдено обновление! Скачать можно на сайте: https://www.spigotmc.org/resources/elitertp.112145");
            }
        });
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().severe("PLaceholderAPI not hooked!");
        }
        getCommand("elitertp").setExecutor(new EliteRtpCommand());
        getCommand("rtpdefault").setExecutor(new RtpDefaultCommand());
        getCommand("rtpnear").setExecutor(new RtpNearCommand());
        getCommand("rtpfar").setExecutor(new RtpFarCommand());
        getCommand("rtpworld").setExecutor(new RtpWorldCommand());
        getCommand("rtp").setExecutor(new RtpCommand());
        getServer().getPluginManager().registerEvents(new RtpClickMenuEvent(), this);
        getServer().getPluginManager().registerEvents(new RtpWorldClickEvent(), this);
        getServer().getPluginManager().registerEvents(new Events(), this);
        saveDefaultConfig();
        loadMessages();
        RtpWorldMenu.setupMenu();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadMessages() {
        saveResource("messages.yml", false);
        File file = new File(getDataFolder().getAbsolutePath() + "/messages.yml");
        messages = YamlConfiguration.loadConfiguration(file);
    }

    public static EliteRTP getInstance() {
        return instance;
    }

    public YamlConfiguration getMessages() {
        return messages;
    }
}
