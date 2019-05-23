package org.mountcloud.mcplugin.common.service.message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.mountcloud.mcplugin.common.BasePlugin;


/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 上午12:42:41 
 * TODO:消息服务
 */
public class BaseMessageService {
	
	protected BasePlugin basePlugin;

	/**
	 * 构造函数
	 * @param basePlugin 插件
	 */
	public BaseMessageService(BasePlugin basePlugin) {
		this.basePlugin = basePlugin;
	}
	
	/**
	 * 根据语言发送消息
	 * @param commandSender 接收消息的人
	 * @param key 配置key
	 * @param defualt 默认使用的
	 * @param prms 需要替换的字符串
	 */
	public void sendMessageByLanguage(CommandSender commandSender,String key,String defualt,String ...prms) {
		String language = basePlugin.getBaseLanguageService().getLanguage(key, defualt, prms);
		sendMessage(commandSender,language);
	}
	
	/**
	 * 发送消息
	 * @param commandSender 接收消息的人
	 * @param message 消息内容
	 */
	public void sendMessage(CommandSender commandSender,String message) {
		String remessage = ChatColor.translateAlternateColorCodes('&', message);
		commandSender.sendMessage(remessage);
	}

}
