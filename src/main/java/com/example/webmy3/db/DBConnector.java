package com.example.webmy3.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnector {
    String user;
    String password;
    String url;
    public DBConnector(){


        user = "s332890";
        password="pswrd";
        url ="jdbc:postgresql://localhost:5432/studs";
   }

   public Connection getConnection() throws Exception {
       try {
           Class.forName("org.postgresql.Driver");
       } catch (ClassNotFoundException e) {
           throw new Exception("Database driver not found");
       }
        return DriverManager.getConnection(url,user,password);
//   return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
    }


}