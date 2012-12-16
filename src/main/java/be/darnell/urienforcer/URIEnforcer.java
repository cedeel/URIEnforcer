package be.darnell.urienforcer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class URIEnforcer extends JavaPlugin implements Listener {
  
  private static final int DEFAULT_PORT = 25565;
  private String ip;
  
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    
    saveDefaultConfig();
    FileConfiguration config = getConfig();
    ip = config.getString("ip", "localhost");
  }
  
  @EventHandler
  public void onLogin(PlayerLoginEvent e) {
    String desiredname = ip + ":" + getServer().getPort();
    if(!e.getHostname().equalsIgnoreCase(desiredname)) {
      if(getServer().getPort() != DEFAULT_PORT) { 
        e.disallow(Result.KICK_OTHER, "\u00A77Please log in using \u00A7F" + desiredname);
      } else {
        e.disallow(Result.KICK_OTHER, "\u00A77Please log in using \u00A7F" + ip);
      }
      getLogger().info("[URIEnforcer] " + e.getPlayer().getName() + " tried to log in using " + e.getHostname());
    }
  }

}
