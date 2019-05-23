package org.mountcloud.mcplugin.common.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.mountcloud.mcplugin.common.BasePlugin;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 上午12:42:41 
 * TODO:所有配置文件的父类
 */
public abstract class BaseConfig {

	private boolean init = false;
	private String fileName;
	private File file;
	private FileConfiguration config;
	private BasePlugin basePlugin;

	/**
	  * 构造函数
	 * @param fileName 文件名
	 * @param plugin 插件
	 */
	public BaseConfig(String fileName,BasePlugin plugin) {
		this.fileName = fileName;
		this.basePlugin = plugin;
		this.file = new File(getConfigFilePath());
		
		try {
			if (!this.file.exists()) {
				this.file.getParentFile().mkdirs();

				InputStream inputStream = this.basePlugin.getResource(this.fileName);
				
				if(inputStream!=null) {
					java.nio.file.Files.copy(inputStream,
							this.file.toPath(), new CopyOption[0]);

					this.loadFileConfig();
					init = true;
				}else {
					if(createConfig()) {
						this.loadFileConfig();
						init = true;
					}
				}
			}else {
				this.loadFileConfig();
				init = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(this.isInit()) {
			load();
		}
	}
	
	/**
	 * 加载fileconfig
	 */
	protected void loadFileConfig() {
		if(this.config==null) {
			this.config = YamlConfiguration.loadConfiguration(this.file);
		}
	}
	
	/**
	 * 创建一个新的配置文件
	 */
	protected boolean createNewConfigFile() {
		boolean state = false;
		try {
			this.file.createNewFile();
			this.loadFileConfig();
			state = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	/**
	 * 配置全路径
	 * @return 配置全路径
	 */
	protected String getConfigFilePath() {
		return this.basePlugin.getDataFolder()+"/"+this.getFileName();
	}
	
	/**
	 * 创建配置
	 */
	protected abstract boolean createConfig();

	/**
	 * 保存配置
	 */
	public void saveConfig() {
		try {
			if(this.isInit()) {
				getConfig().save(getFile());
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}

	/**
	 * 配置的file
	 * @return 文件
	 */
	public File getFile() {
		return this.file;
	}

	/**
	 * 返回配置的config对象
	 * @return 返回配置文件
	 */
	public FileConfiguration getConfig() {
		return this.config;
	}

	/**
	 * 加载配置
	 */
	public void load() {
		if(this.isInit()) {
			loadConfig();
		}
	}

	/**
	 * 加载配置文件
	 */
	public abstract void loadConfig();

	/**
	 * 重新加载
	 */
	public void reload() {
		if(this.isInit()) {
			this.config = YamlConfiguration.loadConfiguration(this.file);
		}
	}
	
	/**
	 * 配置文件名
	 * @return 配置文件名
	 */
	public String getFileName() {
		return this.fileName;
	}
	
	/**
	 * 返回加载状态
	 * @return 加载状态
	 */
	public boolean isInit() {
		return this.init;
	}
	
	/**
	 * 添加配置
	 * @param key 配置的key
	 * @param val 配置的val
	 */
	public void setProperties(String key,Object val) {
		this.getConfig().set(key, val);
	}

	/**
	 * 返回插件
	 * @return 插件
	 */
	public BasePlugin getBasePlugin() {
		return basePlugin;
	}

	public void setBasePlugin(BasePlugin basePlugin) {
		this.basePlugin = basePlugin;
	}
	
	
}
