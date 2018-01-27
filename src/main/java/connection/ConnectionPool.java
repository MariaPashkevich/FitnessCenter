package connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static ConnectionPool connectionPool;
    private ComboPooledDataSource pooledDatasource;

    private final String URL = "jdbc:mysql://localhost/fitness";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWORD = "root";

    private ConnectionPool() throws IOException, SQLException, PropertyVetoException {

        pooledDatasource = new ComboPooledDataSource();
        pooledDatasource.setDriverClass(DRIVER);
        pooledDatasource.setJdbcUrl(URL);
        pooledDatasource.setUser(USER);
        pooledDatasource.setPassword(PASSWORD);

        pooledDatasource.setMinPoolSize(10);
        pooledDatasource.setAcquireIncrement(5);
        pooledDatasource.setMaxPoolSize(20);
        pooledDatasource.setMaxStatements(180);

    }

    public static synchronized ConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
            return connectionPool;
        } else {
            return connectionPool;
        }
    }

    public Connection getConnection() throws SQLException {
        return pooledDatasource.getConnection();
    }
}
