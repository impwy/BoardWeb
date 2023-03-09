package com.springbook.biz.common;

import java.sql.*;

public class JDBCUtil {
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","exdb","1234");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static void close(PreparedStatement stmt, Connection conn) {
        if (stmt != null) {
            try{
                if(!stmt.isClosed()) stmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                stmt = null;
            }
        }
        if (conn != null) {
            try{
                if(!conn.isClosed()) conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                conn = null;
            }
        }
    }
    public static void close(ResultSet rs,PreparedStatement stmt, Connection conn) {
        if (rs != null) {
            try{
                if(!rs.isClosed()) rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                rs = null;
            }
        }
        if (stmt != null) {
            try{
                if(!stmt.isClosed()) stmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                stmt = null;
            }
        }
        if (conn != null) {
            try{
                if(!conn.isClosed()) conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                conn = null;
            }
        }
    }

}
