package br.mineunaerp;

import br.mineunaerp.commands.PanelCommand;
import br.mineunaerp.events.InventoryClickEvent;
import br.mineunaerp.events.JoinLeaveEvent;
import br.mineunaerp.util.Answer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineUnaerp extends JavaPlugin implements Listener {
    private Answer answer;

    private static MineUnaerp instance;

    public static MineUnaerp getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        answer = new Answer(this);

        Bukkit.getPluginManager().registerEvents(this, this);

        System.out.println("[MineUnaerp] Plugin started");

        getServer().getPluginManager().registerEvents(answer, this);
        getServer().getPluginManager().registerEvents(new JoinLeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickEvent(answer), this);

        getCommand("painel").setExecutor(new PanelCommand());

    }

    public Answer getAnswer() {
        return answer;
    }

    @Override
    public void onDisable() {
        System.out.println("[MineUnaerp] Plugin stoped");
    }

}
