package org.mountcloud.mcplugin.common.service.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.mountcloud.mcplugin.common.BasePlugin;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.common.language.BaseLanguageEnum;
import org.mountcloud.mcplugin.common.permission.BasePermissionEnum;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 上午12:42:41 
 * TODO:基础的命令服务
 */
public abstract class BaseCommandeSercvice implements CommandExecutor{

	protected BasePlugin basePlugin;
	/**
	 * 需要执行的命令
	 */
	private String exeCommand;

	/**
	 * 所有的子命令
	 */
	private Map<String, BaseCommand> commands = new HashMap<String, BaseCommand>();
	
	/**
	 * 默认执行命令
	 */
	private BaseCommand defualtCommand = null;
	
	public BaseCommandeSercvice(String execCommand,BasePlugin plugin) {
		this.basePlugin = plugin;
		this.exeCommand = execCommand;
	}
	
	/**
     * 命令执行
     *
     * @param sender 发送命令的对象
     * @param command 被执行的指令
     * @param label 被执行指令的别名
     * @param args 该指令的自变量数组
     * @return 如果返回值是true，你不会看到什么明显的事情发生，但如果返回值是false，则会返回你的plugin.yml里的'usage:property'然后发送给命令使用者. 
     */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		String commandName = getCommandName(args);
		String[] commandArgs = getCommandArgs(args);
		
		BaseCommand commandBean = getCommand(commandName);
		
		/**
		 * 是否找到了命令，找不到就用默认命令
		 */
		if(commandBean == null) {
			if(defualtCommand != null) {
				commandBean = defualtCommand;
			}else {
				return notFoundCommand(sender, command, commandArgs);
			}
		}
		
		//验证是否是属于发送命令的分组
		boolean isCommandSenderType = checkCommandSenderType(sender, commandBean);
		
		//如果不属于则发送提醒
		if(!isCommandSenderType) {
			BaseLanguageEnum languageEnum = BaseLanguageEnum.COMMAND_NOCOMMANDSENDERTYPEMESSAGE;
			this.basePlugin.getBaseMessageService().sendMessageByLanguage(sender, languageEnum.name(), languageEnum.getValue());
			return true;
		}
		
		//判断权限是否符合
		boolean isPermissions = checkPermission(sender,commandBean);
		
		
		//判断权限
		if(!isPermissions) {
			BaseLanguageEnum languageEnum = BaseLanguageEnum.COMMAND_PERMISSIONDENIEDMESSAGE;
			this.basePlugin.getBaseMessageService().sendMessageByLanguage(sender, languageEnum.name(), languageEnum.getValue(),command.getName());
			return true;
		}
		
		//判断最小参数是否符合要求
		if(commandBean.getMinArgs()<commandArgs.length) {
			BaseLanguageEnum languageEnum = BaseLanguageEnum.COMMAND_ARGS_ERROR;
			this.basePlugin.getBaseMessageService().sendMessageByLanguage(sender, languageEnum.name(), languageEnum.getValue(),command.getName());
			return true;
		}
		
		BaseCommandSenderType senderCommandSenderType = getCommandSenderType(sender);
		
		return execCommand(sender,commandBean,commandArgs,senderCommandSenderType);
	}
	
	
	/**
	 * 辨别命令发送方类型
	 * @param sender 命令发送方
	 * @return 发送方类型
	 */
	public BaseCommandSenderType getCommandSenderType(CommandSender sender) {
		BaseCommandSenderType senderCommandSenderType = BaseCommandSenderType.PLAYER;
		//获取命令发送方类型
		if(sender instanceof ConsoleCommandSender) {
			senderCommandSenderType = BaseCommandSenderType.CONSOLE;
		}
		return senderCommandSenderType;
	}
	
	/**
	 * 判断命令是否是命令发送方可使用的
	 * @param sender 命令发送方
	 * @param commandBean 命令实体
	 * @return 检查结果
	 */
	public boolean checkCommandSenderType(CommandSender sender,BaseCommand commandBean) {
		boolean isCommandSenderType = false;
		BaseCommandSenderType commandSenderType = commandBean.getCommandSenderType();
		
		BaseCommandSenderType senderCommandSenderType = getCommandSenderType(sender);
		
		//如果是任意
		if(commandSenderType.equals(BaseCommandSenderType.ARBITRARLIY)) {
			isCommandSenderType = true;
		}else {
			//命令预设类型是否与命令发送方一致
			if(commandSenderType.equals(senderCommandSenderType)) {
				isCommandSenderType = true;
			}
		}
		return isCommandSenderType;
	}
	
	/**
	 * 检查是否有权限
	 * @param sender 命令发送者
	 * @param commandBean 命令
	 * @return 检查结果
	 */
	public boolean checkPermission(CommandSender sender,BaseCommand commandBean) {
		boolean isPermissions = false;
		List<String> commandPermissions = commandBean.getHasPermissions();
		if(commandPermissions==null||commandPermissions.size()==0) {
			isPermissions = true;
		}else {
			//如果拥有管理员权限
			if(sender.hasPermission(BasePermissionEnum.ADMIN.getValue())) {
				isPermissions = true;
			}else {
				//如果拥有指定权限
				for(String permission : commandPermissions) {
					if(sender.hasPermission(permission)) {
						isPermissions = true;
						break;
					}
				}
			}
		}
		return isPermissions;
	}

	
	/**
	 * 执行命令
     * @param sender 发送命令的对象
     * @param command 被执行的指令
     * @param args 该指令的自变量数组
	 * @return 如果返回值是true，你不会看到什么明显的事情发生，但如果返回值是false，则会返回你的plugin.yml里的'usage:property'然后发送给命令使用者. 
	 */
	public abstract boolean execCommand(CommandSender sender,BaseCommand command,String[] args,BaseCommandSenderType commandSenderType);
	
	/**
	 * 
     * @param sender 发送命令的对象
     * @param command 被执行的指令
     * @param args 该指令的自变量数组
	 * @return 如果返回值是true，你不会看到什么明显的事情发生，但如果返回值是false，则会返回你的plugin.yml里的'usage:property'然后发送给命令使用者. 
	 */
	public abstract boolean notFoundCommand(CommandSender sender,Command command,String[] args);
	
	/**
	 * 注册命令
	 * @param commandStr 子命令名称
	 * @param commandBean 命令的实体
	 */
	public <T extends BaseCommand> void registerCommand(String commandStr,T commandBean) {
		commands.put(commandStr, commandBean);
		if(commandBean.isDefault()) {
			defualtCommand = commandBean;
		}
	}
	/**
	 * 将命令注册进来
	 * @param command 命令
	 */
	public <T extends BaseCommand> void registerCommand(T command) {
		registerCommand(command.getCommand(),command);
	}
	
	/**
	 * 提供注册的命令
	 * @param commandFileName 命令文件的filename
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseCommand> T getCommand(String commandName){
		T tempresult = null;
		BaseCommand tempcommand = commands.get(commandName);
		if(tempcommand!=null) {
			tempresult = (T)tempcommand;
		}
		return tempresult;
	}

	/**
	 * 返回需要执行的命令
	 * @return 需要执行的命令
	 */
	public String getExeCommand() {
		return exeCommand;
	}

	/**
	 * 返回所有的子命令
	 * @return 返回子命令列表
	 */
	public Map<String, BaseCommand> getCommands() {
		return commands;
	}
	
	/**
	 * 返回默认命令
	 * @return
	 */
	public BaseCommand getDefualtCommand() {
		return defualtCommand;
	}
	
	/**
	 * 提供参数列表
	 * @param args 原始参数列表
	 * @return 新的参数列表
	 */
	public String[] getCommandArgs(String[] args) {
		List<String> newArgs = new ArrayList<String>();
		for(int i=1;i<args.length;i++) {
			newArgs.add(args[i]);
		}
		
		String[] result = new String[newArgs.size()];
		result = newArgs.toArray(result);
		return result;
	}
	
	/**
	 * 返回子参数的名字
	 * @param args
	 * @return
	 */
	public String getCommandName(String[] args) {
		if(args.length<1) {
			return "";
		}
		return args[0];
	}
	
}
