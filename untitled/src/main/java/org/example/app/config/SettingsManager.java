package org.example.app.config;

import java.util.Properties;

public final class SettingsManager {

    private static final SettingsManager INSTANCE = new SettingsManager();
    private final Properties properties = new Properties();

    private SettingsManager() {}

    public static SettingsManager getInstance() {
        return INSTANCE;
    }

    public void set(String key, String value) {
        properties.setProperty(key, value);
    }


    public String get(String key) {
        return properties.getProperty(key);
    }
}

