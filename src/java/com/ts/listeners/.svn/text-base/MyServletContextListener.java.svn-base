/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.listeners;

import com.ts.junction.Model.JunctionModel;
import com.ts.junction.tableClasses.Junction;
import com.ts.tcpServer.ClientResponder;
import com.ts.tcpServer.TcpServer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 * @author Tarun
 */
public class MyServletContextListener implements ServletContextListener {

    private TcpServer tcpServer;

    @Override
    public void contextInitialized(final ServletContextEvent ctxEvent) {
        // When the application starts 2 things are being done:
        // 1: Load All the junction table records in bean and it to the map.
        // 2: Start the tcps.
        System.out.println("\n\nTrafficSignals contextInitialized. SC_HC: " + ctxEvent.getServletContext().hashCode());
        // Task 1: Load junction records.
        final ServletContext ctx = ctxEvent.getServletContext();
        final String driverClass = ctx.getInitParameter("driverClass");
        final String connectionString = ctx.getInitParameter("connectionString");
        final String db_userName = ctx.getInitParameter("db_userName");
        final String db_userPassword = ctx.getInitParameter("db_userPassword");
        JunctionModel junctionModel = new JunctionModel();
        junctionModel.setDriverClass(driverClass);
        junctionModel.setConnectionString(connectionString);
        junctionModel.setDb_userName(db_userName);
        junctionModel.setDb_userPasswrod(db_userPassword);
        junctionModel.setConnection();
        final Junction junction = new Junction();
        final Map<Integer, Junction> junctionList = junctionModel.getJunctionList();
        final Map<String, ClientResponder> map = new HashMap<String, ClientResponder>();
        (new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    int serverPort = 8089;
                    System.out. println(ctx);
                    tcpServer = new TcpServer(serverPort);
                    tcpServer.setDriverClass(driverClass);
                    tcpServer.setConnectionString(connectionString);
                    tcpServer.setDb_userName(db_userName);
                    tcpServer.setDb_userPasswrod(db_userPassword);
                    ctx.setAttribute("tcpServer", tcpServer);
                    tcpServer.setJunctionList(junctionList);
                    tcpServer.setMap(map);
                    tcpServer.setContext(ctx);
                    tcpServer.setJunction(junction);
                    tcpServer.interceptRequests();
                } catch (IOException ioEx) {
                    System.out.println("MyServletContextListener contextInitialized() TCPServer Error: " + ioEx);
                }
            }
        })).start();
        System.out.println("\n\nTcpServer started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\n\nTrafficSignals contextDestroyed...");
        tcpServer.closeServerSocket();
    }
}
