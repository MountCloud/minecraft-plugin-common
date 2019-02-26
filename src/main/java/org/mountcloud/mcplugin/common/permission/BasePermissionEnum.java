package org.mountcloud.mcplugin.common.permission;
/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月11日 下午11:49:11 
 * TODO:基础权限类型
 */
public enum BasePermissionEnum {
	
	ADMIN("admin");

	private String value;
	
	private BasePermissionEnum(String val) {
		this.value = val;
	}
	
	public String getValue() {
		return this.value;
	}
}
