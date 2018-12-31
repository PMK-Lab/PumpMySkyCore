package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import fr.pumpmyskycore.IslandManager.IslandManagerConstant;
import fr.pumpmyskycore.exceptions.PlayerAlreadyInvited;
import fr.pumpmyskycore.exceptions.PlayerDoesNotInvited;

public class IslandInvites {
	
	public final static String INVITES_STRING = "invites.";
	
	public static IslandInvites init(Path indexPath) throws IOException, InvalidConfigurationException {
		
		File file = new File(indexPath + File.separator + IslandManagerConstant.ISLAND_INVITES_FILE_NAME);			
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileConfiguration fileConf = new YamlConfiguration();
		fileConf.load(file);
		
		return new IslandInvites(file,fileConf);
		
	}

	private File file;
	private FileConfiguration fileConf;

	private IslandInvites(File f, FileConfiguration fc) throws IOException {
		
		this.file = f;
		this.fileConf = fc;
		
		this.init();
		
	}
	
	private void init() throws IOException {
		
		this.fileConf.createSection("invites");
		this.save();
		
	}
	
	private void save() throws IOException {
		
		this.fileConf.save(this.file);
		
	}

	public File getFile() {
		return file;
	}

	public FileConfiguration getYaml() {
		return this.fileConf;
	}

	public boolean contains(UUID minecraftUUID) {
		
		return this.fileConf.contains(INVITES_STRING + minecraftUUID.toString());
		
	}
	
	private void setPlayerInvites(UUID uuid , List<String> invites) throws IOException {
		
		this.fileConf.set(INVITES_STRING + uuid.toString(), invites);
		this.save();
		
	}
	
	public List<String> getPlayerInvites(UUID uuid){
		
		if(this.contains(uuid)) {
			
			this.fileConf.getStringList(INVITES_STRING + uuid.toString());
			
		}
		
		return new ArrayList<>();		
		
	}
	
	public boolean isInvites(UUID uuid, Island island) {
		
		return this.getPlayerInvites(uuid).contains(island.getID());		
		
	}
	
	public void addInvites(UUID uuid, Island island) throws PlayerAlreadyInvited, IOException {
		
		if(this.isInvites(uuid, island)) {
			
			throw new PlayerAlreadyInvited(uuid,island);
			
		}
		
		List<String> invites = this.getPlayerInvites(uuid);
		
		invites.add(island.getID());
		
		this.setPlayerInvites(uuid, invites);
		
	}
	
	public void removeInvites(UUID uuid, Island island) throws PlayerDoesNotInvited, IOException {
		
		if(!this.isInvites(uuid, island)) {
			
			throw new PlayerDoesNotInvited(uuid,island);
			
		}
		
		List<String> invites = this.getPlayerInvites(uuid);
		
		invites.add(island.getID());
		
		this.setPlayerInvites(uuid, invites);
		
	}
	
}
