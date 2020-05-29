/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ts.dbcon;

/**
 *
 * @author Pooja
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
//import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class DBConnection {

    private static Connection conn = null;
    public static synchronized Connection getConnection(ServletContext ctx, HttpSession session) throws SQLException {
        try {
            Class.forName(ctx.getInitParameter("driverClass"));
            conn = DriverManager.getConnection(ctx.getInitParameter("connectionString"), (String) session.getAttribute("db_userName"), (String) session.getAttribute("db_userPassword"));
        } catch (Exception e) {
            System.out.println("DBConncetion getConnection() Error: " + e);
        }
        return conn;
    }

    public static synchronized Connection getConnection(ServletContext ctx) throws SQLException {
        Connection conn = null;
        try {
            Class.forName(ctx.getInitParameter("driverClass"));
            conn = DriverManager.getConnection(ctx.getInitParameter("connectionString"), (String) ctx.getInitParameter("db_userName"), (String) ctx.getInitParameter("db_userPassword"));
        } catch (Exception e) {
            System.out.println("DBConncetion getConnection() Error: " + e);
        }
        return conn;
    }

    public  static synchronized Connection getConnectionForUtf(ServletContext ctx, HttpSession session)  throws SQLException {
         Connection conn = null;
        try {
            Class.forName(ctx.getInitParameter("driverClass"));
            conn = (Connection) DriverManager.getConnection(ctx.getInitParameter("connectionString") + "?useUnicode=true&characterEncoding=UTF-8&character_set_results=utf8",(String) session.getAttribute("user_name"), (String) session.getAttribute("user_password"));
        } catch (Exception e) {
            System.out.println(" setConnection() Error: " + e);
        }
        return conn;
    }
    public static void closeConncetion(Connection con) {

        try {
            con.close();
        } catch (Exception e) {
            System.out.println("DBConncetion closeConnection() Error: " + e);
        }

    }

}

