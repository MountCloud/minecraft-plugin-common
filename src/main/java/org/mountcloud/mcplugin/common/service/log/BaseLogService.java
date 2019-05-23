package org.mountcloud.mcplugin.common.service.log;

import org.mountcloud.mcplugin.common.BasePlugin;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 上午10:45:37 
 * TODO:
 */
public class BaseLogService {
	

	protected BasePlugin basePlugin;

	/**
	 * 构造函数
	 * @param basePlugin 插件
	 */
	public BaseLogService(BasePlugin basePlugin) {
		this.basePlugin = basePlugin;
	}
	
	/**
	 * 打印info日志
	 * @param log 日志
	 */
	public void info(String log) {
		this.basePlugin.getLogger().info(log);
	}
	/**
	 * 打印warning日志
	 * @param log 日志
	 */
	public void warning(String log) {
		this.basePlugin.getLogger().warning(log);
	}
	
}
