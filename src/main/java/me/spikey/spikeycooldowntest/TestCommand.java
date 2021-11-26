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

public class TestCommand implements CommandExecutor {
    private API api;

    public TestCommand(API api) {

        this.api = api;
        api.registerCooldown(0, "test");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This is a player only command.");
            return true;
        }

        Player player = (Player) sender;

        if (api.isOnCooldown(player, 0)) {
            sender.sendMessage( "You cannot run this command for another %s.".formatted("" + api.getRemainingFormatted(player, 0)));
            return true;
        }

        sender.sendMessage("Command Complete!");
        player.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, player.getLocation(), 10);

        api.updateCooldown(player, 0);

        return true;
    }
}
