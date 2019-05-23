package org.mountcloud.mcplugin.common;

import org.mountcloud.mcplugin.common.service.log.BaseLogService;

/**
 * @author zhanghaishan
 * @version 创建时间：2019/5/22 23:37
 * TODO:
 */
public class McCommonPlugin extends BasePlugin {

    @Override
    public boolean enable() {
        getBaseLogService().info("Load McPluginCommon Success.");
        getBaseLogService().info("Go to http://www.mountcloud.org for more plug-in information.");
        return true;
    }
}
