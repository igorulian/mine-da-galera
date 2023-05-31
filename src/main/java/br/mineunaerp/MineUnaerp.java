package br.mineunaerp;

import br.mineunaerp.events.JoinLeaveEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import br.mineunaerp.commands.*;

public final class MineUnaerp extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("[MineUnaerp] Plugin started");

        getServer().getPluginManager().registerEvents(new JoinLeaveEvent(), this);

        getCommand("painel").setExecutor(new PanelCommand());
    }

    @Override
    public void onDisable() {
        System.out.println("[MineUnaerp] Plugin stoped");
    }

}
