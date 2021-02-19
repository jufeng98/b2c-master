package org.javamaster.maven.plugin.dubbo.model;

/**
 * @author yudong
 * @date 2020/8/20
 */
public class Dubbo {
    private String interfaceName;
    private String newVersion;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }
}
