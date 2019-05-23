package org.mountcloud.mcplugin.common.service.language;

import org.mountcloud.mcplugin.common.BasePlugin;
import org.mountcloud.mcplugin.common.config.BaseLanguageConfig;


/**
 * @author zhanghaishan
 * @version 创建时间：2018年10月11日 下午10:58:13 TODO:
 */
public class BaseLanguageService {
	
	protected BasePlugin basePlugin;

	/**
	 * 构造函数
	 * @param basePlugin 插件
	 */
	public BaseLanguageService(BasePlugin basePlugin) {
		this.basePlugin = basePlugin;
	}

	/**
	 * 返回字符串
	 * @param str 需要替换的字符串
	 * @param prms 需要填充的字符串
	 * @return 替换后的字符串
	 */
	public String getString(String str, String... prms) {
		String tempString = str;
		if (tempString != null) {
			for (int i = 0; i < prms.length; i++) {
				String holder = "{" + i + "}";
				tempString = tempString.replace(holder, String.valueOf(prms[i]));
			}
		}
		return tempString;
	}
	
	/**
	 * 返回语言配置
	 * @param key 语言配置名称
	 * @param defualt 默认语言
	 * @param prms 需要替换的字符串
	 * @return 语言文本
	 */
	public String getLanguage(String key,String defualt,String ...prms) {
		BaseLanguageConfig baseLanguageConfig = this.basePlugin.getBaseConfigService().getConfig(BaseLanguageConfig.configName);
		
		String language = null;
		if(baseLanguageConfig!=null) {
			language = baseLanguageConfig.getConfig().getString(key, defualt);
			if(language==null) {
				language = defualt;
			}			
		}else {
			language = defualt;
		}
		language = getString(language, prms);
		return language;
	}
}
