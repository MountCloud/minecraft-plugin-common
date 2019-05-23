package org.mountcloud.mcplugin.common.config;

import org.mountcloud.mcplugin.common.BasePlugin;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 下午11:00:58 
 * TODO:
 */
public class BaseLanguageConfig extends BaseConfig {
	
	public static String configName = "lang.yml";

	/**
	 * 构造函数
	 * @param basePlugin 插件
	 */
	public BaseLanguageConfig(BasePlugin basePlugin) {
		super(BaseLanguageConfig.configName,basePlugin);
	}

	@Override
	public void loadConfig() {
		
	}

	@Override
	protected boolean createConfig() {
		return false;
	}


}
