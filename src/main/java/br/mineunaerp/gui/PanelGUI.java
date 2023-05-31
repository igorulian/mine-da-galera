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
        Inventory inv = Bukkit.createInventory(player, 9*3, "Painel");


        ItemStack itemChangeName = createItem(
                Material.PLAYER_HEAD,
                "Alterar nome",
                new String[]{
                        "Caso queira alterar seu nome",
                        "basta clicar e em seguida digitar",
                        "o nome desejado"
        });

        ItemStack itemChangeSkin = createItem(
                Material.ARMOR_STAND,
                "Alterar skin",
                new String[]{
                        "Caso queira alterar sua skin",
                        "basta clicar e em seguida digitar",
                        "o nick da skin desejada"
                });

        ItemStack itemClaimPlot = createItem(
                Material.GRASS_BLOCK,
                "Comprar terreno",
                new String[]{
                        "Clique para comprar o terreno que",
                        "está atualmete em cima",
                        "Preço atual: " + ChatColor.BLUE +  " 00 Diamantes",
                        ChatColor.DARK_GRAY + "Lembrando: os terrenos são separados por chunks",
                        ChatColor.DARK_GRAY + "Lembrando: os terrenos custão DIAMANTES"
        });

        ItemStack itemShowPlot = createItem(
                Material.ENDER_EYE,
                "Mostrar o terreno",
                new String[]{
                        "Clique para mostrar o terreno que",
                        "está atualmete em cima"
        });

        ItemStack itemShowNearPlot = createItem(
                Material.COMPASS,
                "Mostrar terrenos ocupados por perto",
                new String[]{
                        "Clique para mostrar os terrenos que",
                        "estão ocupado em uma area de 40 blocos"
        });

        ItemStack itemUnclaimPlot = createItem(
                Material.BARRIER,
                ChatColor.RED + "Desocupar terreno",
                new String[]{
                        "Clique para desocupar o terreno que",
                        "está atualmete em cima"
        });

        ItemStack itemAccessPlot = createItem(
                Material.CREEPER_BANNER_PATTERN,
                "Acesso ao terreno",
                new String[]{
                        "Clique para " + ChatColor.GOLD + "conceder/remover" + ChatColor.DARK_GRAY + "o acesso",
                        "de um determinado player aos seus terrenos"
        });

        inv.setItem(1, itemChangeName);
        inv.setItem(2, itemChangeSkin);


        inv.setItem(19, itemClaimPlot);
        inv.setItem(20, itemShowPlot);
        inv.setItem(21, itemShowNearPlot);

        inv.setItem(24, itemUnclaimPlot);
        inv.setItem(25, itemAccessPlot);
        return  inv;
    }

    private ItemStack createItem(Material material, String name, String[] lore){
        ItemStack item = new ItemStack(material);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + name);

        ArrayList<String> itemLore = new ArrayList<>();

        for (String line : lore) {
            itemLore.add(ChatColor.DARK_GRAY + line);
        }

        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        return item;
    }

}
