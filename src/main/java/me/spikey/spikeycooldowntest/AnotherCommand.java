package me.spikey.spikeycooldowntest;

import me.spikey.spikeycooldownapi.API;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.Instant;

public class AnotherCommand implements CommandExecutor {
    private API api;

    public AnotherCommand(API api) {

        this.api = api;
        api.registerCooldown(1, "another");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This is a player only command.");
            return true;
        }

        Player player = (Player) sender;

        if (api.isOnCooldown(player.getUniqueId(), 1)) {
            sender.sendMessage( "You cannot run this command for %s minute(s).".formatted("" + api.getRemainingFormatted(player.getUniqueId(), 1)));
            return true;
        }

        sender.sendMessage("Command Complete!");
        player.getWorld().spawnParticle(Particle.CRIMSON_SPORE, player.getLocation(), 10);

        api.updateCooldown(player.getUniqueId(), 1);

        return true;
    }
}
