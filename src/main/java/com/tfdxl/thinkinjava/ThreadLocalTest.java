package com.tfdxl.thinkinjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tianfeng on 2017/4/21.
 */
public class ThreadLocalTest
{
    public static String DB_URL = "";
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>(){

        @Override
        protected Connection initialValue() {
            try {
                return DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

}
