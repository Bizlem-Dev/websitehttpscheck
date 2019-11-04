package com.doctiger.classonly;

import java.util.Properties;

public class Config {

	 Properties configFile;
	 private static final Config INSTANCE = new Config();

	public Config() {
		configFile = new java.util.Properties();
		try {
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (Exception eta) {
			eta.printStackTrace();
		}
	}

	public String getProperty(String key) {
		String value = this.configFile.getProperty(key);
		return value;
	}

	 public static Config getInstance() {
	        return INSTANCE;
	    }
	
}
