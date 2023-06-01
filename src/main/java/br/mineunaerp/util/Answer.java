package br.mineunaerp.util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Answer implements Listener {
    private final JavaPlugin plugin;
    private final Map<UUID, AnswerCallback> answerCallbacks;
    private final Map<UUID, BukkitRunnable> cancelTasks;

    public Answer(JavaPlugin plugin) {
        this.plugin = plugin;
        this.answerCallbacks = new HashMap<>();
        this.cancelTasks = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void ask(Player player, String question, AnswerCallback callback) {
        player.sendMessage(" ");
        player.sendMessage(question);
        player.sendMessage(" ");
        UUID playerId = player.getUniqueId();
        answerCallbacks.put(playerId, callback);

        BukkitRunnable cancelTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (answerCallbacks.containsKey(playerId)) {
                    answerCallbacks.remove(playerId);
                    player.sendMessage(ChatColor.RED + "Você não respondeu a pergunta a tempo.");
                }
            }
        };
        cancelTask.runTaskLater(plugin, 200L); // 5 segundos (20 ticks por segundo, 5 segundos = 100 ticks)
        cancelTasks.put(playerId, cancelTask);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (answerCallbacks.containsKey(playerId)) {
            AnswerCallback callback = answerCallbacks.get(playerId);
            String message = event.getMessage();

            // Verificar se a mensagem faz parte de uma resposta
            if (callback.isAnswer(message)) {
                answerCallbacks.remove(playerId);
                BukkitRunnable cancelTask = cancelTasks.remove(playerId);
                if (cancelTask != null) {
                    cancelTask.cancel();
                }

                event.setCancelled(true);
                plugin.getServer().getScheduler().runTask(plugin, () -> {
                    callback.onAnswer(player, message);
                });
            }
        }
    }
}

