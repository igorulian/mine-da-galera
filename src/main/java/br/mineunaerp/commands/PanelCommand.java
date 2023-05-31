package br.mineunaerp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PanelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            System.out.println(ChatColor.RED + "[MineUnaerp] Comando apenas para players");
            return false;
        }

        Player player = (Player) commandSender;

        if(command.getName().equalsIgnoreCase("painel")){
            player.sendMessage("Comando painel");
            return true;
        }


        return false;
    }
}