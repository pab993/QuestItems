package org.redstool.questitems.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.redstool.questitems.config.CustomConfig;


public class MyEvents implements Listener{
	
	static String kw = CustomConfig.get().getString("keywords");
	static String cd = CustomConfig.get().getString("craftdeny");
	static String ud = CustomConfig.get().getString("usedeny");
	
	//Prevent to craft a block with a certain text lore
	@EventHandler
	public void questRecipeBlock(PrepareItemCraftEvent e){
		ItemStack air = new ItemStack(Material.AIR);
		for(HumanEntity entity : e.getViewers()) {
			if(entity instanceof Player) {
				Player p = (Player) entity;
				ItemStack[] items = e.getInventory().getMatrix();
				outerloop:
				for(int i = 0; i < 9; i++) {
					if(items[i] != null) {
						if(items[i].getItemMeta() != null) {
							if(items[i].getItemMeta().getLore() != null) {
								for(String s: items[i].getItemMeta().getLore()) {
									if(s.contains(kw)) {
										e.getInventory().setResult(air);
										p.sendMessage(ChatColor.RED + cd );
										break outerloop;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void questActionBlock(PlayerInteractEvent e) {
		if(e.getItem() != null) {
	        if(e.getItem().getItemMeta() != null) {
				if(e.getItem().getItemMeta().getLore() != null || !e.getItem().getItemMeta().getLore().isEmpty()) {
					for(String s: e.getItem().getItemMeta().getLore()) {
						if(s.contains(kw)) {
							e.getPlayer().sendMessage(ChatColor.RED + ud);
							e.setCancelled(true);
							break;
						}
					}
				}
			}
		}
	}
	
	
	//======================================================================================================
	// METHODS TESTS
	//======================================================================================================
	
	
//	//Prevent to place a block with a certain text lore
//	@EventHandler
//	public void questBlockPlace(BlockPlaceEvent e) {
//		Block block = e.getBlock();
//		Player player = e.getPlayer();
//		List<String> lores = e.getItemInHand().getItemMeta().getLore();
//		for(String s : lores) {
//			if(s.contains(kw)) {
//				block.setType(Material.AIR);
//				player.sendMessage(ChatColor.RED + pd );
//			}
//		}	
//	}
	
	
//	@EventHandler
//	public void questConsumBlock(PlayerItemConsumeEvent e) {
//		if(e.getItem().getItemMeta() != null) {
//			if(e.getItem().getItemMeta().getLore() != null || !e.getItem().getItemMeta().getLore().isEmpty()) {
//				for(String s: e.getItem().getItemMeta().getLore()) {
//					if(s.contains(kw)) {
//						e.getPlayer().sendMessage(ChatColor.RED + consumd);
//						e.setCancelled(true);
//					}
//				}
//			}
//		}
//	}
}
