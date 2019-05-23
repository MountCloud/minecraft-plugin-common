package org.mountcloud.mcplugin.common.service.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.mountcloud.mcplugin.common.BasePlugin;
import org.mountcloud.mcplugin.common.config.BaseConfig;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 上午12:42:41 
 * TODO:配置服务
 */
public class BaseConfigService {
	
	private Map<String,BaseConfig> configs = new HashMap<String,BaseConfig>();
	
	protected BasePlugin basePlugin;

	/**
	 * 构造函数
	 * @param plugin 插件
	 */
	public BaseConfigService(BasePlugin plugin) {
		this.basePlugin = plugin;
	}
	
	/**
	 * 将配置注册进来
	 * @param config 配置
	 * @param <T> 配置文件
	 */
	public <T extends BaseConfig> void registerConfig(T config) {
		if(config.isInit()) {
			String tempkey = config.getFileName();
			configs.put(tempkey, config);
		}else {
			basePlugin.getBaseLogService().warning(config.getFileName() + " cant registerConfig,because config is not init!");
		}
	}

	/**
	 * 重新加载某个config
	 * @param configFileName 文件名
	 */
	public void reloadConfig(String configFileName) {
		BaseConfig tempconfig = configs.get(configFileName);
		tempconfig.reload();
	}
	
	/**
	 * 重载所有配置文件
	 */
	public void reloadAllConfig() {
		Set<String> tempkeys = this.configs.keySet();
		for(String tempkey : tempkeys) {
			reloadConfig(tempkey);
		}
	}
	
	/**
	 * 提供注册的配置
	 * @param configFileName 配置文件的filename
	 * @param <T> 配置文件
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseConfig> T getConfig(String configFileName){
		T tempresult = null;
		BaseConfig tempconfig = configs.get(configFileName);
		if(tempconfig!=null) {
			tempresult = (T)tempconfig;
		}
		return tempresult;
	}
}
