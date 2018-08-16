package me.illuzionz.cf.events;

import me.illuzionz.cf.CustomFishing;
import me.illuzionz.cf.api.FishRewardEvent;
import me.illuzionz.cf.util.MessageManager;
import me.illuzionz.cf.util.Msg;
import me.illuzionz.cf.util.SoundManager;
import me.illuzionz.cf.util.title.CTitle;
import mkremins.fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.List;
import java.util.Random;

public class FishingHandler implements Listener {

    private CustomFishing plugin = CustomFishing.cf;

    private FileConfiguration config = plugin.getManager().getRewards();
    private String[] items;
    private int ch;
    private CTitle title = new CTitle();
    private SoundManager sm = SoundManager.getInstance();
    private MessageManager mm = MessageManager.getInstance();

    /**
     * Generate all the chances and items to be picked out from
     *
     * @param player The player who's chances are being randomized
     */
    public void randomize(Player player) {
        int index = 0;
        ch = 0;

        for (String key : config.getConfigurationSection("Rewards").getKeys(false)) {
            for (int i = 0; i < config.getInt("Rewards." + key + ".chance"); i++) {
                ch++;
            }

        }

        if (ch <= 0) {
            return;
        }

        items = new String[ch];

        for (String key : config.getConfigurationSection("Rewards").getKeys(false)) {
            // Set each item in the array

            for (int i = 0; i < config.getInt("Rewards." + key + ".chance"); i++) {
                items[index++] = key;
            }

        }

    }

    @EventHandler
    public void onFish(PlayerFishEvent e) {

        Player p = e.getPlayer();
        Random ran = new Random();
        Random r = new Random();
        Entity fish = e.getCaught();


        if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH) {

            randomize(p);

            e.setExpToDrop(config.getInt("expToDrop"));

            if (r.nextInt(100) <= config.getDouble("NoRewardChance")) {
                return;
            }

            if (items.length == 0) {
                MessageManager.getInstance().sendMsg(p, Msg.MSG_PLAYER_NOREWARDS);
                return;
            }

            String name = items[ran.nextInt(ch)];

            List<String> message = config.getStringList("Rewards." + name + ".message");
            List<String> command = config.getStringList("Rewards." + name + ".command");
            List<String> broadcast = config.getStringList("Rewards." + name + ".broadcast");
            Boolean vanilla = config.getBoolean("Rewards." + name + ".vanillaRewards");

            FishRewardEvent event = new FishRewardEvent(p, name, command, message, broadcast, config.getInt("expToDrop"));
            Bukkit.getServer().getPluginManager().callEvent(event);

            if (config.getString("Rewards." + name + ".sound") != null) {
                try {
                    sm.playSound(p, Sound.valueOf(config.getString("Rewards." + name + ".sound")));

                } catch (Exception ex) {
                    Bukkit.getConsoleSender().sendMessage(MessageManager.getInstance().colorMsg("&cThat is not a valid sound name"));
                }
            }
            ;


            e.setExpToDrop(config.getInt("Rewards." + name + ".expAmount"));

            if (!vanilla) {
                fish.remove();
            }

            if (config.getBoolean("Rewards." + name + ".titleEnabled")) {

                String titleMessage = new FancyMessage(mm.colorMsg(config.getString("Rewards." + name
                        + ".Title"))).toJSONString();

                String subTitleMessage = new FancyMessage(mm.colorMsg(config.getString("Rewards." + name
                        + ".SubTitle"))).toJSONString();

                title.sendTitle(p, titleMessage, subTitleMessage);

            }

            for (String com : command) {
                com = com.replaceAll("%player%", p.getPlayer().getName());
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), com);
            }
            for (String mess : message) {
                mm.sendMsg(p, mess);
            }
            if (config.getBoolean("Rewards." + name + ".broadcastEnable") == true) {
                for (String brod : broadcast) {
                    brod = brod.replaceAll("%player%", p.getPlayer().getName());
                    Bukkit.getServer().broadcastMessage(mm.colorMsg(brod));
                }
            }

        }

    }

}
