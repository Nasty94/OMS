package com.demo.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class SQLServerConnUtils {
 
  
 //Connect to SQLServer, using SQLJDBC Library.
 static Connection getSQLServerConnection_SQLJDBC() throws ClassNotFoundException, SQLException {
    
     Connection conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
     return conn;
 }
 
}