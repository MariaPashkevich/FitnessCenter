package connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

public class DataSource {

    private static DataSource dataSource;
    private ComboPooledDataSource comboPooledDataSource;

    private DataSource() throws IOException, SQLException, PropertyVetoException {

        comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/fitness_center");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if(dataSource == null){
            return new DataSource();
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
