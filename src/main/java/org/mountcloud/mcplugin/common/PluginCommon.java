package org.mountcloud.mcplugin.common;

import org.mountcloud.mcplugin.common.service.log.BaseLogService;

/**
 * TODO: 加载插件使用的运行类
 * org.mountcloud.mcplugin.common
 * 2019/2/26.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class PluginCommon extends BasePlugin {

    private BaseLogService baseLogService;

    @Override
    public boolean enable() {
        baseLogService = new BaseLogService(this);
        baseLogService.info("Load PluginComm Success.");
        baseLogService.info("Go to http://www.mountcloud.org for more plug-in information.");
        return true;
    }
}
