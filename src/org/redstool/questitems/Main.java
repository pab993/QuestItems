package org.redstool.questitems;

import org.bukkit.plugin.java.JavaPlugin;
import org.redstool.questitems.config.CustomConfig;
import org.redstool.questitems.events.MyEvents;


public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		CustomConfig.setup();
		CustomConfig.get().addDefault("placedeny", "�No puedes colocar un objeto de misi�n!");
		CustomConfig.get().addDefault("craftdeny", "�No puedes craftear con un objeto de misi�n!");
		CustomConfig.get().addDefault("usedeny", "�No puedes usar un objeto de misi�n!");
		CustomConfig.get().addDefault("keywords", "Objeto de Misi�n");
		CustomConfig.get().options().copyDefaults(true);
		CustomConfig.save();
		this.getServer().getPluginManager().registerEvents(new MyEvents(), this);
	}

	@Override
	public void onDisable() {
		
	}
	
}
