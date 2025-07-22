package org.remote.spring.config;

import java.util.List;


public class RemoteChannelConfig {
    private List<RemoteChannel> remoteChannel;

    public List<RemoteChannel> getRemoteChannel() {
        return remoteChannel;
    }

    public void setRemoteChannel(List<RemoteChannel> remoteChannel) {
        this.remoteChannel = remoteChannel;
    }

    public static class RemoteChannel {
        private String id;
        private String handler;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHandler() {
            return handler;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }
    }
}
