package com.zookeeper.http.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.zookeeper.http.util.Json;

public class ConfigurationManager {
	
	private static ConfigurationManager myConfigurationManager;
	private static Configuration myCurrentConfiguration;
	
	private ConfigurationManager() {
		
	}
	public static ConfigurationManager getInstance() {
		if(myConfigurationManager == null)
			myConfigurationManager = new ConfigurationManager();
		return myConfigurationManager;
	}
	
	/**
	 * Used to load a configuration file by the file path
	 * 
	 * @throws IOException 
	 */
	public void loadConfigurationFile(String filePath) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		StringBuffer sb = new StringBuffer();
		int i;
		try {
			while( (i = fileReader.read())!= -1) {
				sb.append((char)i);
			}
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new HttpConfigurationException(e);
		}
		JsonNode conf;
		try {
			conf = Json.parse(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new HttpConfigurationException(e);
		} 
		try {
			myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			throw new HttpConfigurationException(e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new HttpConfigurationException(e);
		}
		
	}
	/**
	 * Return the current loaded configuration
	 */
	public Configuration getCurrentConfiguration() {
		if (myCurrentConfiguration == null) {
			throw new HttpConfigurationException("No Current Configuration Set.");
		}
		return myCurrentConfiguration;
	}
}
