package org.example.app.config;

import java.io.InputStream;
import java.util.Properties;

public final class AppConfig {

    private AppConfig() {}

    public static final String DB_URL =
            "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres";

    public static final String DB_USER =
            "postgres.nvimuolinrhwdpmxfviq";

    public static final String DB_PASS = loadPassword();

    private static String loadPassword() {
        Properties props = new Properties();

        try (InputStream in = AppConfig.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (in == null) {
                throw new RuntimeException("config.properties not found in src/main/resources");
            }

            props.load(in);

            String pwd = props.getProperty("DB_PASSWORD");
            if (pwd == null || pwd.isBlank()) {
                throw new RuntimeException("DB_PASSWORD not found in config.properties");
            }
            return pwd;

        } catch (Exception e) {
            throw new RuntimeException("Cannot load DB password", e);
        }
    }
}

