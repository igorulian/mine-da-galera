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
    private int INVENTORY_SIZE = 9*3;
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
        Inventory inv = Bukkit.createInventory(player, INVENTORY_SIZE, "Painel");

        ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta bmeta = background.getItemMeta();
        bmeta.setDisplayName(" ");
        background.setItemMeta(bmeta);

        for (int x = 0; x < INVENTORY_SIZE; x++){
            inv.setItem(x, background);
        }


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
                Material.LIME_CONCRETE,
                "Conceder acesso ao terreno",
                new String[]{
                        "Clique para " + ChatColor.GREEN + "conceder" + ChatColor.DARK_GRAY + " o acesso",
                        "de um determinado jogador"
        });

        ItemStack itemRemoveAccessPlot = createItem(
                Material.RED_CONCRETE,
                "Remover acesso ao terreno",
                new String[]{
                        "Clique para " + ChatColor.RED + "remover" + ChatColor.DARK_GRAY + " o acesso",
                        "de um determinado jogador"
                });

        inv.setItem(1, itemChangeName);
        inv.setItem(2, itemChangeSkin);


        inv.setItem(19, itemClaimPlot);
        inv.setItem(20, itemShowPlot);
        inv.setItem(21, itemShowNearPlot);

        inv.setItem(23, itemUnclaimPlot);
        inv.setItem(24, itemAccessPlot);
        inv.setItem(25, itemRemoveAccessPlot);
        return  inv;
    }

    private ItemStack createItem(Material material, String name, String[] lore){
        ItemStack item = new ItemStack(material);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);

        ArrayList<String> itemLore = new ArrayList<>();

        for (String line : lore) {
            itemLore.add(ChatColor.DARK_GRAY + line);
        }

        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        return item;
    }

}
