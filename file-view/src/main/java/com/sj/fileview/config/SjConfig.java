package com.sj.fileview.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sj")
public class SjConfig {
    /** 上传路径 */
    private static String profile;

    public static String getDownloadPath()
    {
        return profile + "download/";
    }
}
