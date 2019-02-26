package org.mountcloud.mcplugin.common.service.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.junit.Test;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月12日 上午1:13:42 
 * TODO:
 */
public class BaseCommandeSercviceTest {

	@Test
	public void testArgs() {
		String[] args = new String[] {};
		BaseCommandeSercvice baseCommandeSercvice = new BaseCommandeSercvice("prefix", null) {
			
			@Override
			public boolean notFoundCommand(CommandSender sender, Command command, String[] args) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean execCommand(CommandSender sender, BaseCommand command, String[] args,
					BaseCommandSenderType commandSenderType) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		String[] newArgs = baseCommandeSercvice.getCommandArgs(args);
		String commandName = baseCommandeSercvice.getCommandName(args);
		
		System.out.println(commandName+":"+newArgs.length);
	}
}
