package br.mineunaerp.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnDead implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Entity entity = event.getEntity();

        if(!(entity instanceof Player)) return;

        Player player = ((Player) entity).getPlayer();


        int x = (int) player.getLocation().getX();
        int y = (int) player.getLocation().getY();
        int z = (int) player.getLocation().getZ();
        player.sendMessage("Você morreu nas coordenadas:: " + ChatColor.RED + "X: " + x + " Y: " + y + " Z: " + z);
    }

}
