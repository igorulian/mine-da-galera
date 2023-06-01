package br.mineunaerp.events;

import br.mineunaerp.util.Answer;
import br.mineunaerp.util.AnswerCallback;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
//                        player.setDisplayName(answer);
                        player.sendMessage(" ");
                        player.sendMessage(ChatColor.GREEN + "Usuário alterado com sucesso!");
                        player.sendMessage(" ");
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

            if(itemName.equalsIgnoreCase("Acesso ao terreno")){
                answer.ask(player, "Digite o nick do jogador que deseja conceder/remover o acesso:", new AnswerCallback() {
                    @Override
                    public boolean isAnswer(String message) {
                        return true;
                    }

                    @Override
                    public void onAnswer(Player player, String answer) {
//                        player.setDisplayName(answer);
                        player.performCommand("chunk access "+ answer);
                    }
                });
            }
        }
    }
}
