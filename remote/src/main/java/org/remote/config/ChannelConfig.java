package org.remote.config;


import org.remote.constant.PropertiesConstants;
import org.remote.enums.RemoteCallWay;
import org.remote.invocationHandler.RemoteChannelHandler;
import org.remote.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelConfig {

    private static final ChannelConfig instance = new ChannelConfig();

    private final Map<String, RemoteChannelHandler> map = new HashMap<>();

    private ChannelConfig() {
        for (int count = 0; Config.containsKey(String.format(PropertiesConstants.CHANNEL_ID, count)); count++) {
            String idStr = Config.get(String.format(PropertiesConstants.CHANNEL_ID, count));
            if (StringUtils.isEmpty(idStr)) {
                continue;
            }
            RemoteCallWay remoteCallWay = Enum.valueOf(RemoteCallWay.class, Config.get(String.format(PropertiesConstants.CHANNEL_HANDLER, count), "netty").toUpperCase());
            map.put(idStr, remoteCallWay.getHandler());
        }
        AppConfig appConfig = AppConfig.getInstance();
        List<AppConfig.Handler> remoteChannel = appConfig.getRemote_channel();
        for (AppConfig.Handler handler : remoteChannel) {
            if (!StringUtils.isEmpty(handler.getId())) {
                RemoteCallWay remoteCallWay = Enum.valueOf(RemoteCallWay.class, (StringUtils.isEmpty(handler.getHandler()) ? "netty" : handler.getHandler()).toUpperCase());
                map.put(handler.getId(), remoteCallWay.getHandler());
            }
        }
    }

    public static Map<String, RemoteChannelHandler> get() {
        return instance.map;
    }

    public static RemoteChannelHandler get(String key) {
        return instance.map.get(key);
    }
}
