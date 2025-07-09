package org.remote.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;

public class AppConfig {

    private List<Handler> remote_channel;

    private AppConfig() {

    }

    public List<Handler> getRemote_channel() {
        return remote_channel;
    }

    public void setRemote_channel(List<Handler> remote_channel) {
        this.remote_channel = remote_channel;
    }

    public static AppConfig getInstance() {
        return AppConfigHolder.instance;
    }

    private void loadConfig() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Yaml yaml = new Yaml();
            AppConfig config = yaml.loadAs(inputStream, AppConfig.class);
            this.remote_channel = config.getRemote_channel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class AppConfigHolder {
        private static final AppConfig instance = new AppConfig();

        static {
            instance.loadConfig();
        }
    }

    public static class Handler {
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
