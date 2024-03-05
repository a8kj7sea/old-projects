package me.Sanhak.Convertor.Methods;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PayMethod 
{

	
	
	public static boolean pay(Player ply, Material type, int amount) {
	      PlayerInventory inv = ply.getInventory();
	      int available = 0;
	      Map<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();

	      int unpaidAmount;
	      for(unpaidAmount = 0; unpaidAmount < inv.getSize(); ++unpaidAmount) {
	         ItemStack stack = inv.getItem(unpaidAmount);
	         if (stack != null && stack.getType() == type) {
	            items.put(unpaidAmount, stack);
	            available += stack.getAmount();
	         }
	      }

	      if (available < amount) {
	         return false;
	      } else {
	         unpaidAmount = amount;
	         Iterator<?> var8 = items.entrySet().iterator();

	         while(var8.hasNext()) {
	            @SuppressWarnings("unchecked")
				Entry<Integer, ItemStack> stackEntry = (Entry<Integer, ItemStack>)var8.next();
	            int invSlot = (Integer)stackEntry.getKey();
	            ItemStack stack = (ItemStack)stackEntry.getValue();
	            if (stack.getAmount() >= unpaidAmount) {
	               if (stack.getAmount() == unpaidAmount) {
	                  inv.setItem(invSlot, (ItemStack)null);
	               } else {
	                  stack.setAmount(stack.getAmount() - unpaidAmount);
	               }
	               break;
	            }

	            unpaidAmount -= stack.getAmount();
	            inv.setItem(invSlot, (ItemStack)null);
	         }

	         ply.updateInventory();
	         return true;
	      }
	   }
	
	
	
	
	
	
	
	
	
	
}
