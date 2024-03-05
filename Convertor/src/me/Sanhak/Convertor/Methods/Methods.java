package me.Sanhak.Convertor.Methods;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import net.md_5.bungee.api.ChatColor;

public class Methods
{
	static public String Header_Lapis = ChatColor.translateAlternateColorCodes('&', "&1Exp Levels");
	static public String Cost_Lapis = ChatColor.translateAlternateColorCodes('&', "&1Costs 1 Redstone");
	static public String Header_Gold = ChatColor.translateAlternateColorCodes('&', "&eGold Exchange");
	static public String Cost_Gold = ChatColor.translateAlternateColorCodes('&', "&eCosts 64 Redstone");
	
	
	public static void Title_Gold(Location location) 
	{
		Location loc = location.clone();
		CreateArmorStand(Header_Gold, loc);
		loc = loc.subtract(0 , 0.25 , 0);
		CreateArmorStand(Cost_Gold, loc);
		loc = loc.subtract(0 , 0.25 , 0);
	}		
	
	
	public static void Title_Lapis(Location location) 
	{
		Location loc = location.clone();
		CreateArmorStand(Header_Lapis, loc);
		loc = loc.subtract(0 , 0.25 , 0);
		CreateArmorStand(Cost_Lapis, loc);
		loc = loc.subtract(0 , 0.25 , 0);
	}		
	
	private static ArmorStand CreateArmorStand(String name , Location loc)
	{
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setGravity(false);
		stand.setVisible(false);
		stand.setCustomName(name);
		stand.setCustomNameVisible(true);
		return stand;
		
	} 
	
	public static String f(String msg) 
	{
	return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	
	
}
