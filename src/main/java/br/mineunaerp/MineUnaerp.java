package br.mineunaerp;

import org.bukkit.plugin.java.JavaPlugin;

public final class MineUnaerp extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[MineUnaerp] Plugin started");

    }

    @Override
    public void onDisable() {
        System.out.println("[MineUnaerp] Plugin stoped");
    }
}
