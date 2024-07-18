package ru.squarecircle.elitertp.pluginevents;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.squarecircle.elitertp.core.RtpType;

public class PlayerRtpEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private RtpType type;
    @Nullable World world;
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public PlayerRtpEvent(Player player, RtpType type, World world) {
        this.player = player;
        this.type = type;
        this.world = world;
    }

    public Player getPlayer() {
        return player;
    }

    public RtpType getType() {
        return type;
    }

    @Nullable
    public World getWorld() {
        return world;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

