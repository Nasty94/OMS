package com.demo.h2;

import java.io.IOException;
import java.sql.*;

import org.h2.tools.Server;

public class App {
    private static final String DBNAME = "oms";

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // open the in-memory database within a VM

        Class.forName("org.h2.Driver"); // (1)
        Connection conn 
			= DriverManager.getConnection("jdbc:h2:mem:" + DBNAME, "sa", "sa"); // (2)
        // username:password are very important and must be used for connecting via H2 Console

        Statement stat = conn.createStatement(); // (3)
        stat.executeUpdate("create table mytbl(id int primary key, name varchar(255))");
        stat.executeUpdate("insert into mytbl values(1, 'Hello')");
        stat.executeUpdate("insert into mytbl values(2, 'World')");

        // Verify that sample data was really inserted
        ResultSet rs = stat.executeQuery("select * from mytbl");
        System.out.println("ResultSet output:");
        while (rs.next()) {
            System.out.println("> " + rs.getString("name"));
        }
        
        final String[] arg = new String[] {
        		"-tcpPort", "8092",
        		"-tcpAllowOthers","true" };

        // start a TCP server
        Server server = Server.createTcpServer(arg).start(); // (4)
        // .. use in embedded mode ..

        // or use it from another process:
        System.out.println("Server started and connection is open.");
        System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:" + DBNAME);

        System.out.println("\n");
        System.out.println(
                "now start the H2 Console in another process using:\n" +
                "$ cd h2/bin; java -cp h2-1.4.185.jar org.h2.tools.Console -web -browser");

        System.out.println("Press [Enter] to stop.");
        System.in.read();

        System.out.println("Stopping server and closing the connection");

        rs.close();
        server.stop();
        conn.close();
        System.out.println("Server is STOPPED");
    }
}