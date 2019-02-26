package org.mountcloud.mcplugin.common.language;

import org.junit.Test;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 下午11:24:33 
 * TODO:
 */
public class BaseLanguageEnumTest {
	
	@Test
	public void testEnum() {
		BaseLanguageEnum baseLanguageEnum = BaseLanguageEnum.COMMAND_NOT_FOUND_COMMAND;
		
		String enumname = baseLanguageEnum.name();
		
		System.out.println(BaseLanguageEnum.getEnumByName(enumname).getValue());
	}

}
