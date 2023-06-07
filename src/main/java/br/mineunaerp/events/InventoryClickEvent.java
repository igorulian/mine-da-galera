package br.mineunaerp.events;

import br.mineunaerp.util.Answer;
import br.mineunaerp.util.AnswerCallback;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import net.md_5.bungee.api.chat.TextComponent;

import java.awt.*;

public class InventoryClickEvent implements Listener {
    private Answer answer;
    public InventoryClickEvent(Answer a){
        answer = a;
    }

    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event){
        String invName = event.getView().getTitle();

        if(!(event.getWhoClicked() instanceof Player)){
            return;
        }

        Player player = (Player) event.getWhoClicked();

        String changeName = ChatColor.YELLOW + "Alterar nome";

        if(invName.equalsIgnoreCase("Painel")){
            event.setCancelled(true);

            if(event.getCurrentItem() == null){
                return;
            }

            if(!event.getCurrentItem().hasItemMeta()){
                return;
            }

            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            player.closeInventory();

            if(itemName.equalsIgnoreCase("Alterar nome")){
                answer.ask(player, "Digite para qual nick deseja alterar:", new AnswerCallback() {
                    @Override
                    public boolean isAnswer(String message) {
                        return true;
                    }

                    @Override
                    public void onAnswer(Player player, String answer) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "displaynameplayer " + player.getName() + " " + answer);
                    }
                });
            }

            if(itemName.equalsIgnoreCase("Alterar skin")){
                answer.ask(player, "Digite para qual skin deseja alterar:", new AnswerCallback() {
                    @Override
                    public boolean isAnswer(String message) {
                        return true;
                    }

                    @Override
                    public void onAnswer(Player player, String answer) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "skin set " + player.getName() + " " + answer);
                    }
                });
            }

            if(itemName.equalsIgnoreCase("Comprar terreno")){
                player.performCommand("chunk claim");
            }

            if(itemName.equalsIgnoreCase("Mostrar o terreno")){
                player.performCommand("chunk show 10");
            }

            if(itemName.equalsIgnoreCase("Mostrar terrenos ocupados por perto")){
                player.performCommand("chunk show claimed 40 10");
            }

            if(itemName.equalsIgnoreCase(ChatColor.RED +"Desocupar terreno")){
                player.performCommand("chunk unclaim");
            }

            if(itemName.equalsIgnoreCase("Conceder acesso ao terreno")){
                answer.ask(player, "Digite o nick do jogador que deseja conceder o acesso:", new AnswerCallback() {
                    @Override
                    public boolean isAnswer(String message) {
                        return true;
                    }

                    @Override
                    public void onAnswer(Player player, String answer) {
//                        player.setDisplayName(answer);
                        player.sendMessage(" ");
                        player.sendMessage("Conceder acesso de " + ChatColor.GOLD + answer + ChatColor.RESET + " para:");
                        player.sendMessage(" ");

                        TextComponent all = new TextComponent("[Todos os meus terrenos]");
                        all.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                        all.setBold(true);
                        all.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/chunk access "+ answer + " allChunks:true"));
                        all.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new ComponentBuilder("Clique para conceder ao jogador " + answer + " acesso a TODOS os seus terrenos")
                                        .color(net.md_5.bungee.api.ChatColor.DARK_GRAY)
                                        .italic(true)
                                        .create()
                        ));
                        player.spigot().sendMessage(all);

                        player.sendMessage(" ");

                        TextComponent just = new TextComponent("[Apenas o terro que estou em cima]");
                        just.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                        just.setBold(true);
                        just.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/chunk access "+ answer + " allChunks:false"));
                        just.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new ComponentBuilder("Clique para conceder ao jogador " + answer + " acesso apenas ao terreno que você está atualmente em cima")
                                        .color(net.md_5.bungee.api.ChatColor.DARK_GRAY)
                                        .italic(true)
                                        .create()
                        ));
                        player.spigot().sendMessage(just);

                        player.sendMessage(" ");
                        player.sendMessage(ChatColor.DARK_GRAY + "Clique na opção desejada a cima");
                    }
                });
            }

            if(itemName.equalsIgnoreCase("Remover acesso ao terreno")){
                answer.ask(player, "Digite o nick do jogador que deseja remover o acesso:", new AnswerCallback() {
                    @Override
                    public boolean isAnswer(String message) {
                        return true;
                    }

                    @Override
                    public void onAnswer(Player player, String answer) {
//                        player.setDisplayName(answer);
                        player.sendMessage(" ");
                        player.sendMessage("Remover acesso de " + ChatColor.GOLD + answer + ChatColor.RESET + " para:");
                        player.sendMessage(" ");

                        TextComponent all = new TextComponent("[Todos os meus terrenos]");
                        all.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                        all.setBold(true);
                        all.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "chunk access "+ answer + " allChunks:true"));
                        all.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new ComponentBuilder("Clique para remove o acesso do jogador " + answer + " a TODOS os seus terrenos")
                                        .color(net.md_5.bungee.api.ChatColor.DARK_GRAY)
                                        .italic(true)
                                        .create()
                        ));
                        player.spigot().sendMessage(all);

                        player.sendMessage(" ");

                        TextComponent just = new TextComponent("[Apenas o terro que estou em cima]");
                        just.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                        just.setBold(true);
                        just.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "chunk access "+ answer + " allChunks:false"));
                        just.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new ComponentBuilder("Clique para remove o acesso do jogador " + answer + " apenas ao terreno que você está atualmente em cima")
                                        .color(net.md_5.bungee.api.ChatColor.DARK_GRAY)
                                        .italic(true)
                                        .create()
                        ));
                        player.spigot().sendMessage(just);

                        player.sendMessage(" ");
                        player.sendMessage(ChatColor.DARK_GRAY + "Clique na opção desejada a cima");
                    }
                });
            }
        }
    }
}
