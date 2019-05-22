package org.mountcloud.mcplugin.common;

import org.mountcloud.mcplugin.common.service.log.BaseLogService;

/**
 * @author zhanghaishan
 * @version 创建时间：2019/5/22 23:37
 * TODO:
 */
public class McCommonPlugin extends BasePlugin {

    private BaseLogService baseLogService;

    public McCommonPlugin(){
        baseLogService = new BaseLogService(this);
    }

    @Override
    public boolean enable() {
        baseLogService.info("install mount cloud plugin common success!!!");
        return true;
    }
}
