/*******************************************************************************
 * Copyright (c) 2014 University of Luxembourg.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Alfredo Capozucca - initial API and implementation
 ******************************************************************************/
package lu.uni.lassy.excalibur.examples.icrash.dev.java.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4JUtils {

	// our log4j category reference
	 final Logger log = Logger.getLogger(Log4JUtils.class);
	 final String LOG_PROPERTIES_FILE = "conf/Log4J.properties";



	//Eager singleton pattern
	private static volatile Log4JUtils instance = new Log4JUtils();
 
    // private constructor
    private Log4JUtils() {
    	initializeLogger();
		log.debug("Log4JUtils - leaving the constructor ...");
    }
 
    public static Log4JUtils getInstance() {
        return instance;
    }


    public Logger getLogger() {
        return log;
    }




	private void initializeLogger() {
		Properties logProperties = new Properties();

		try {
			// load our log4j properties / configuration file
			logProperties.load(new FileInputStream(LOG_PROPERTIES_FILE));
			PropertyConfigurator.configure(logProperties);
			log.debug("Logging initialized.");
		} catch (IOException e) {
			throw new RuntimeException("Unable to load logging property "
					+ LOG_PROPERTIES_FILE);
		}
	}




	public static void main(String[] args) {
		// call our constructor
		Logger log = Log4JUtils.getInstance().getLogger();

		// Log4J is now loaded; try it
		log.info("leaving the main method of Log4JUtils");


		// Log4J is now loaded; try it
		log.debug("and here in DEBUG mode message: leaving the main method of Log4JUtils");

	}

	
}
