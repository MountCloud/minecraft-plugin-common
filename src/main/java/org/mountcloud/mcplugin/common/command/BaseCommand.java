package org.mountcloud.mcplugin.common.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.mountcloud.mcplugin.common.BasePlugin;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 上午12:42:41 
 * TODO:命令行父类
 */
public abstract class BaseCommand {
	
	/**
	 * 所有人权限的构造
	 * @param command 命令
	 * @param baseCommandSenderType 接收命令相应的人群
	 * @param minArgs 最小支持的参数列表
	 */
	public BaseCommand(String command,BaseCommandSenderType baseCommandSenderType,int minArgs) {
		this(command,baseCommandSenderType,minArgs,null);
	}

	/**
	 * 所有人权限的构造
	 * @param command 命令
	 * @param baseCommandSenderType 接收命令相应的人群
	 * @param minArgs 最小支持的参数列表
	 * @param hasPermissions 所需要的权限
	 */
	public BaseCommand(String command,BaseCommandSenderType baseCommandSenderType,int minArgs,List<String> hasPermissions) {
		this.command = command;
		this.commandSenderType = baseCommandSenderType;
		this.minArgs = minArgs;
		if(hasPermissions!=null) {
			this.hasPermissions = hasPermissions;
		}
	}
	
	/**
	 * 命令名称
	 */
	private String command;
	
	/**
	 * 最小要使用的参数个数
	 */
	private int minArgs = 0;
	
	/**
	 * 执行命令需要的权限
	 */
	private List<String> hasPermissions = new ArrayList<String>();
	
	/**
	 * 命令帮助
	 */
	private String usage;
	
	/**
	 * 命令别名
	 */
	private String lable;
	
	/**
	 * 是不是默认执行的命令
	 */
	private boolean isDefault = false;
	
	/**
	 * 允许谁来执行命令
	 */
	private BaseCommandSenderType commandSenderType = BaseCommandSenderType.ARBITRARLIY;
	
	/**
	 * 执行该命令
	 * @param commandSender 命令发送人
	 * @param args 命令参数
	 * @param commandSenderType 发送人类型
	 */
	public abstract void run(CommandSender commandSender, String[] args, BaseCommandSenderType commandSenderType);

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public int getMinArgs() {
		return minArgs;
	}

	public void setMinArgs(int minArgs) {
		this.minArgs = minArgs;
	}

	public List<String> getHasPermissions() {
		return hasPermissions;
	}

	public void setHasPermissions(List<String> hasPermissions) {
		this.hasPermissions = hasPermissions;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public BaseCommandSenderType getCommandSenderType() {
		return commandSenderType;
	}

	public void setCommandSenderType(BaseCommandSenderType commandSenderType) {
		this.commandSenderType = commandSenderType;
	}
	
}
