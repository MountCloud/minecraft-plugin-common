package org.mountcloud.mcplugin.common;


import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mountcloud.mcplugin.common.listener.BaseListener;
import org.mountcloud.mcplugin.common.service.command.BaseCommandeSercvice;
import org.mountcloud.mcplugin.common.service.config.BaseConfigService;
import org.mountcloud.mcplugin.common.service.language.BaseLanguageService;
import org.mountcloud.mcplugin.common.service.log.BaseLogService;
import org.mountcloud.mcplugin.common.service.message.BaseMessageService;
import org.mountcloud.mcplugin.common.service.BaseService;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 上午12:42:41 
 * TODO: 插件父类
 */
public abstract class BasePlugin extends JavaPlugin {
	
	private BaseLogService baseLogService;
	
	private BaseConfigService baseConfigService;
	
	private BaseLanguageService baseLanguageService;
	
	private BaseMessageService baseMessageService;

	
	@Override
	public void onEnable() {
		initService();
		boolean state = enable();
		if(!state) {
			/**
			 * 初始化异常
			 * 关闭插件
			 */
			BasePlugin tempPlugin = this;
			tempPlugin.setEnabled(false);

			this.baseLogService.info("Plugin enable state is false,set plugin enable false");
		}
	}
	
	private void initService() {
		baseLogService = new BaseLogService(this);
		baseConfigService = new BaseConfigService(this);
		baseLanguageService = new BaseLanguageService(this);
		baseMessageService = new BaseMessageService(this);
	}
	
	/**
	 * 服务启用时
	 * @return
	 */
	public abstract boolean enable();
	
	/**
	 * 提供基础命令用于注册命令执行者，如果不需要命令则只用返回null
	 * @return
	 */
	public String getDefaultCommand() {
		return null;
	}
	
	/**
	 * 根据玩家昵称查询玩家
	 * @param name 玩家昵称
	 * @return 返回玩家
	 */
	public Player getOnlinePlayerByName(String name) {
		return getServer().getPlayer(name);
	}
	
	/**
	 * 根据玩家UUID查询玩家
	 * @param uuid 玩家UUID
	 * @return 返回玩家
	 */
	public Player getOnlinePlayerByUUID(UUID uuid) {
		return getServer().getPlayer(uuid);
	}
	
	/**
	 * 根据玩家UUID查询玩家
	 * @param uuid 玩家UUID
	 * @return 返回玩家
	 */
	public Player getOnlinePlayerByUUID(String uuid) {
		UUID tempUUID = UUID.fromString(uuid);
		return getServer().getPlayer(tempUUID);
	}
	
	/**
	 * 根据玩家昵称查询玩家
	 * @param name 玩家昵称
	 * @return 返回玩家
	 */
	@Deprecated
	public OfflinePlayer getOfflinePlayerByName(String name) {
		return getServer().getOfflinePlayer(name);
	}
	
	/**
	 * 根据玩家UUID查询玩家
	 * @param uuid 玩家UUID
	 * @return 返回玩家
	 */
	public OfflinePlayer getOfflinePlayerByUUID(UUID uuid) {
		return getServer().getOfflinePlayer(uuid);
	}
	
	/**
	 * 根据玩家UUID查询玩家
	 * @param uuid 玩家UUID
	 * @return 返回玩家
	 */
	public OfflinePlayer getOfflinePlayerByUUID(String uuid) {
		UUID tempUUID = UUID.fromString(uuid);
		return getServer().getOfflinePlayer(tempUUID);
	}
	
	/**
	 * 注册命令执行者
	 * @param commandServcie 命令执行服务
	 */
	public<T extends BaseCommandeSercvice> void registerCommand(T commandServcie) {
		getCommand(commandServcie.getExeCommand()).setExecutor(commandServcie);
	}
	
	/**
	 * 注册事件监听器
	 * @param listener 事件监听器
	 */
	public <T extends BaseListener> void registerListener(T listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends BaseService> void registerService(T service) {
		Class serviceClass = service.getClass();
		Bukkit.getServicesManager().register(serviceClass, service, this, org.bukkit.plugin.ServicePriority.Normal);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends BaseService,D extends T> void registerService(Class<T> cls,D service) {
		Bukkit.getServicesManager().register(cls, service, this, org.bukkit.plugin.ServicePriority.Normal);
	}

	public BaseLogService getBaseLogService() {
		return baseLogService;
	}

	public void setBaseLogService(BaseLogService baseLogService) {
		this.baseLogService = baseLogService;
	}

	public BaseConfigService getBaseConfigService() {
		return baseConfigService;
	}

	public void setBaseConfigService(BaseConfigService baseConfigService) {
		this.baseConfigService = baseConfigService;
	}

	public BaseLanguageService getBaseLanguageService() {
		return baseLanguageService;
	}

	public void setBaseLanguageService(BaseLanguageService baseLanguageService) {
		this.baseLanguageService = baseLanguageService;
	}

	public BaseMessageService getBaseMessageService() {
		return baseMessageService;
	}

	public void setBaseMessageService(BaseMessageService baseMessageService) {
		this.baseMessageService = baseMessageService;
	}
}
