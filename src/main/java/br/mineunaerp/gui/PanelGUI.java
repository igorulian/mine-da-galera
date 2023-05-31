package br.mineunaerp.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PanelGUI {
    Player player;
    Inventory inventory;

    public PanelGUI(Player p) {
        player = p;
        inventory = setupInventory();
    }

    public Inventory getInventory(){
        return  inventory;
    }

    private Inventory setupInventory(){
        Inventory inv = Bukkit.createInventory(player, 9*5, ChatColor.BLUE + "Painel");

        ItemStack itemChangeName = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemChangeNameMeta = itemChangeName.getItemMeta();
        itemChangeNameMeta.setDisplayName(ChatColor.YELLOW + "Alterar nome");
        ArrayList<String> itemChangeNameLore = new ArrayList<>();
        itemChangeNameLore.add(ChatColor.DARK_GRAY + "Caso não goste do seu nome no jogo");
        itemChangeNameLore.add(ChatColor.DARK_GRAY + "clique aqui para alterar :)");
        itemChangeNameMeta.setLore(itemChangeNameLore);
        itemChangeName.setItemMeta(itemChangeNameMeta);

        inv.setItem(10, itemChangeName);

        ItemStack itemClaimPlot = new ItemStack(Material.DIRT);
        ItemMeta itemClaimPlotMeta = itemClaimPlot.getItemMeta();
        itemClaimPlotMeta.setDisplayName(ChatColor.YELLOW + "Pegar terreno");
        ArrayList<String> itemClaimPlotLore = new ArrayList<>();
        itemClaimPlotLore.add(ChatColor.DARK_GRAY + "Clique para comprar o terreno que está atualmente em pé");
        itemClaimPlotLore.add(ChatColor.DARK_GRAY + "Lembrando: os terrenos são separados por chunks");
        itemClaimPlotMeta.setLore(itemClaimPlotLore);
        itemClaimPlot.setItemMeta(itemClaimPlotMeta);

        inv.setItem(28, itemClaimPlot);
        return  inv;
    }

    private ItemStack createItem(Material material, String name){
        ItemStack item = new ItemStack(material);
        return item;
    }

}
