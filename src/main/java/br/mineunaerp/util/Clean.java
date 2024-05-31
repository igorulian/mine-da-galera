package br.mineunaerp.util;

import org.bukkit.entity.Player;

public class Clean {
    public static void cleanChat(Player player){
        for(int x = 0; x < 15; x++){
            player.sendMessage("  ");
        }
    }
}
