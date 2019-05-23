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

	//日志服务
	private BaseLogService baseLogService;

	//配置服务
	private BaseConfigService baseConfigService;

	//国际化语言服务
	private BaseLanguageService baseLanguageService;

	//消息服务
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
	 * 服务启用时调用
	 * @return 是都启用
	 */
	public abstract boolean enable();
	
	/**
	 * 提供基础命令用于注册命令执行者，如果不需要命令则只用返回null
	 * @return 返回默认的命令
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
	 * @param <T> 命令服务
	 */
	public<T extends BaseCommandeSercvice> void registerCommand(T commandServcie) {
		getCommand(commandServcie.getExeCommand()).setExecutor(commandServcie);
	}
	
	/**
	 * 注册事件监听器
	 * @param listener 事件监听器
	 * @param <T> 命令服务
	 */
	public <T extends BaseListener> void registerListener(T listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}

	/**
	 * 注册服务
	 * @param service 服务
	 * @param <T> 服务
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends BaseService> void registerService(T service) {
		Class serviceClass = service.getClass();
		Bukkit.getServicesManager().register(serviceClass, service, this, org.bukkit.plugin.ServicePriority.Normal);
	}

	/**
	 * 注册服务
	 * @param cls 服务的类
	 * @param service 服务
	 * @param <T> 服务类型
	 * @param <D> 服务类型
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends BaseService,D extends T> void registerService(Class<T> cls,D service) {
		Bukkit.getServicesManager().register(cls, service, this, org.bukkit.plugin.ServicePriority.Normal);
	}

	/**
	 * 返回日志服务
	 * @return 日志服务
	 */
	public BaseLogService getBaseLogService() {
		return baseLogService;
	}

	public void setBaseLogService(BaseLogService baseLogService) {
		this.baseLogService = baseLogService;
	}

	/**
	 * 返回配置服务
	 * @return 配置服务
	 */
	public BaseConfigService getBaseConfigService() {
		return baseConfigService;
	}

	public void setBaseConfigService(BaseConfigService baseConfigService) {
		this.baseConfigService = baseConfigService;
	}

	/**
	 * 返回国际化语言服务
	 * @return 语言服务
	 */
	public BaseLanguageService getBaseLanguageService() {
		return baseLanguageService;
	}

	public void setBaseLanguageService(BaseLanguageService baseLanguageService) {
		this.baseLanguageService = baseLanguageService;
	}

	/**
	 * 返回消息服务
	 * @return 消息服务
	 */
	public BaseMessageService getBaseMessageService() {
		return baseMessageService;
	}

	public void setBaseMessageService(BaseMessageService baseMessageService) {
		this.baseMessageService = baseMessageService;
	}
}
