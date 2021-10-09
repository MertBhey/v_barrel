package me.mertbhey.v_barrel;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	ArrayList<Material> items = new ArrayList<>();
	ArrayList<Location> ignore = new ArrayList<>();
	
	@Override
	public void onEnable() {
		for(Material i:Material.values()) {
			items.add(i);
		}

		String[] remove = { 
			"AIR", "CAVE_AIR", "END_PORTAL",
			"COMMAND_BLOCK", "END_PORTAL_FRAME", "END_GATEWAY",
			"NETHER_PORTAL", "COMMAND_BLOCK_MINECART", "BARRIER",
			"CHAIN_COMMAND_BLOCK", "REPEATING_COMMAND_BLOCK",
			"STRUCTURE_BLOCK", "STRUCTURE_VOID"
		};
				    
		for(String r : remove) {
			items.remove(Material.getMaterial(r));
		}
		
		Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
	}
	
	@EventHandler
	public void onRightClick(InventoryOpenEvent e) {
		if(e.getInventory().getLocation().getBlock().getType() == Material.BARREL) {
			if(!ignore.contains(e.getInventory().getLocation())) {
				Random r = new Random();
				for(int i = 0; i < 27; i++) {
					Material m = items.get(r.nextInt(items.size()-1));
					ItemStack it = new ItemStack(m, r.nextInt(3));
					e.getInventory().addItem(it);
				}
				ignore.add(e.getInventory().getLocation());
			}
		}
	}
	
}
