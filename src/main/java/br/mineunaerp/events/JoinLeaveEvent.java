package br.mineunaerp.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinLeaveEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage("§7[§a+§7] " + player.getDisplayName());

        player.sendTitle("Bem-vindo", "Bem-vindo ao mine da unaerp :)");
    }
}
