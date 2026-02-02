package org.example.app.db;

import java.sql.Connection;
import java.sql.SQLException;

public final class DbManager {

    private static final DbManager INSTANCE = new DbManager();
    private final IDatabase database = new PostgresDatabase();


    private DbManager() {}

    public static DbManager getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return database.getConnection();
    }
}
