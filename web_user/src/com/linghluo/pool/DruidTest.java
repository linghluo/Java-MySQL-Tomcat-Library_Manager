package com.linghluo.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DruidTest {

    private static DataSource ds;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();;

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);

            ds = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    //连接池获取对象方法
    public static Connection getConnection(){
        try {
            Connection connection = threadLocal.get();
            if(connection == null){
                connection = ds.getConnection();
                threadLocal.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //回收方法
    public static void release(){
        try{
            Connection connection = threadLocal.get();
            if(connection != null){
                threadLocal.remove();
                connection.close();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

