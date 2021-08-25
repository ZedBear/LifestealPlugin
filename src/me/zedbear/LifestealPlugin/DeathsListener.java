package me.zedbear.LifestealPlugin;

import com.mysql.jdbc.ByteArrayRow;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.net.http.WebSocket;

public class DeathsListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        if (killed.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent dmgEvent = (EntityDamageByEntityEvent) killed.getLastDamageCause();
            if (dmgEvent.getDamager() instanceof Player && dmgEvent.getDamager() != null) {
                Player killer = (Player) dmgEvent.getDamager();
                    double killermaxhealth = killer.getHealth();
                    double killedmaxhealth = killed.getHealth();

                    double newkillershealth = killermaxhealth+2;
                    killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newkillershealth);

                    killed.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(killedmaxhealth-2);
            }
        }
    }

}
