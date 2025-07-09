package org.remote.spring.config;

import java.util.List;

public class ChannelConfig {

    private List<Channel> channel;

    public List<Channel> getChannel() {
        return channel;
    }

    public void setChannel(List<Channel> channel) {
        this.channel = channel;
    }

    public static class Channel {
        private String id;
        private String handler;

        // Getter and Setter
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

        @Override
        public String toString() {
            return "Channel{id='" + id + "', handler='" + handler + "'}";
        }
    }
}
