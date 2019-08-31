package org.redstool.questitems.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfig {

	private static File file;
	private static FileConfiguration customFile;
	
	//Find or create the lang.yml
	public static void setup() {
		new File(Bukkit.getServer().getPluginManager().getPlugin("QuestItems").getDataFolder().toString()).mkdirs();
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("QuestItems").getDataFolder() + File.separator, "lang.yml");
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		customFile = YamlConfiguration.loadConfiguration(file);
	}
	
	public static FileConfiguration get() {
		return customFile;
	}
	
	public static void save() {
		try {
			customFile.save(file);
		}catch(IOException e) {
			e.printStackTrace();
			 System.out.println("Couldn't save file");
		}
	}
	
	public static void reload() {
		customFile = YamlConfiguration.loadConfiguration(file);
	}
}
