Overview: 
Custom fishing is a plugin that allows your players to have a totally new and unique fishing experience,
with unlimited custom rewards, sounds, permissions and much more. Constant updates to this plugin incorparating your ideas
to make this plugin fun for anyone.

Contribution: 
If you would like to fix any code up, optimize, or add your own feature, feel free to submit a pull request.
Requirements as follow:
- Clean code
- Follow Java naming conventions
- Correct use of the Spigot/Bukkit API

For the config go to the config.yml in the resources file

Api:
This is just for developers, ignore otherwise.

There is only one event followed by all the methods

```java
@EventHandler
public void onFish(FishRewardEvent event){

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
```
