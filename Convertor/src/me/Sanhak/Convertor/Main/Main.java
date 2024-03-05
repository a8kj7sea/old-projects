package me.Sanhak.Convertor.Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.Sanhak.Convertor.Listeners.Listeners;
import me.Sanhak.Convertor.Methods.Methods;

public class Main extends JavaPlugin 
{
   public void onEnable() 
   {
    Bukkit.getPluginManager().registerEvents(new Listeners(), this);
    Bukkit.getServer().getConsoleSender().sendMessage(Methods.f("&cRedstone - Convertor"));
    Bukkit.getServer().getConsoleSender().sendMessage(Methods.f("&A&lThe plugin ENABLED"));
    Bukkit.getServer().getConsoleSender().sendMessage(Methods.f("&7Developer : Sanhak#6142"));
    
   }
   public void onDisable() 
   {
	Bukkit.getServer().getConsoleSender().sendMessage(Methods.f("&cRedstone - Convertor"));
    Bukkit.getServer().getConsoleSender().sendMessage(Methods.f("&c&lThe plugin DISABLED"));
	Bukkit.getServer().getConsoleSender().sendMessage(Methods.f("&7Developer : Sanhak#6142"));
    
   }

  
}