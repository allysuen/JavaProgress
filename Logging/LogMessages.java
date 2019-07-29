package Logging;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogMessages {

	static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static void main(String[] args) {
		
		logger.setLevel(Level.ALL);
		
		
		//Simple Log Method: 
		logger.log(Level.SEVERE,"this is a simple log method");
		
		//Level convenience methods: 
		logger.fine("This is a fine level convenience log method");
		logger.severe("This is a severe level convenience log method");
		logger.info("This is a info level convenience log method");
		logger.warning("This is a warning level convenience log method");
		
		String in = "IN";
		String out = "OUT";
		
		
		preciseLevelLogging(in, out);
		

	}
	private static void preciseLevelLogging(String in, String out) {
	

		logger.logp(Level.SEVERE, "Logging.logMessages", "preciseLevelLogging", "this is a precise level logging method");
		logger.logp(Level.SEVERE, "Logging.logMessages", "preciseLevelLogging", "Parameters can go {0} and {1}",new Object[] {in,out});
	}


}
