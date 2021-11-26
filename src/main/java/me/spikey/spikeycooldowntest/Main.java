package me.spikey.spikeycooldowntest;

import me.spikey.spikeycooldownapi.API;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public API api;

    @Override
    public void onEnable() {
        api = new API(this.getName(), "test");

        getCommand("test").setExecutor(new TestCommand(api));
        getCommand("another").setExecutor(new AnotherCommand(api));
    }

    @Override
    public void onDisable() {

    }

    public API getApi() {
        return api;
    }
}
