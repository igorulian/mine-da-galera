package br.mineunaerp.util;
import org.bukkit.entity.Player;

public interface AnswerCallback {
    boolean isAnswer(String message);
    void onAnswer(Player player, String answer);
}
