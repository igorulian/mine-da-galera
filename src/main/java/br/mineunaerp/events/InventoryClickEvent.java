package br.mineunaerp.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class InventoryClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event){
        String invName = event.getView().getTitle();

        if(invName.equalsIgnoreCase("Painel")){
            event.setCancelled(true);
        }
    }
}
