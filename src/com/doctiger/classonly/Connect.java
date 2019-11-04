package com.doctiger.classonly;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connect {

	static Connection con = null;

    public static Connection getConnectionObj() {
        try {
            Class.forName(Config.getInstance().getProperty("jdbcdriver"));
            con = (Connection) DriverManager.getConnection(Config.getInstance().getProperty("jdbclocalhost")+"/"+Config.getInstance().getProperty("sqldatabase"),Config.getInstance().getProperty("sqluser"),Config.getInstance().getProperty("sqlpassword")); 

        } catch (Exception e) {
            e.printStackTrace();

        }

        return con;
    }
	
}
