package com.example.boot.db;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Administrator zhoulk
 * date: 2018/9/24 0024
 */
public class JdbcUtil {
    static BasicDataSource bs = new BasicDataSource();

    //获取连接
    public static  Connection getConnection() throws Exception{
        Connection conn = null;
        try {
            conn = bs.getConnection();
            return conn;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭连接
    public static void close(Connection conn, Statement statement, ResultSet rs){
        if(null!=rs){
            try {
                if(rs!=null){
                    rs.close();
                }
                if(statement!=null){
                    statement.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{

            }
        }
    }

}
