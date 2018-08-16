package me.illuzionz.cf.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import java.util.List;

public class FishRewardEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private String rewardName;
    private List<String> commands, messages, broadcastmessages;
    private int expAmount;

    public FishRewardEvent(Player who, String rewardName, List<String> commands, List<String> messages, List<String> broadcastmessages,
                           int expAmount) {
        super(who);
        setRewardName(rewardName);
        setCommands(commands);
        setMessages(messages);
        setBroadcastmessages(broadcastmessages);
        setExpAmount(expAmount);
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getRewardName() {
        return rewardName;
    }

    private void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public List<String> getCommands() {
        return commands;
    }

    private void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public List<String> getMessages() {
        return messages;
    }

    private void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getBroadcastmessages() {
        return broadcastmessages;
    }

    private void setBroadcastmessages(List<String> broadcastmessages) {
        this.broadcastmessages = broadcastmessages;
    }

    public int getExpAmount() {
        return expAmount;
    }

    public void setExpAmount(int expAmount) {
        this.expAmount = expAmount;
    }
}
