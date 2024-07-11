package me.tuanzi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static me.tuanzi.utils.LoggerUtils.printWarnLog;

public class Database {

    public static Connection connect(){
        String url = "jdbc:sqlite:player.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            printWarnLog("连接数据库失败:" + e.getMessage());
        }

        return connection;
    }

    public static void initDatabase(){
        try{
            Connection connection = connect();
            Statement statement = connection.createStatement();

            String s =  "CREATE TABLE PLAYER " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";

            statement.executeUpdate(s);

        } catch (SQLException e) {
            printWarnLog("初始化数据库失败:" + e.getMessage());
        }
    }


}
