package adelStruts;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TestLog4J {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Logger logger = LogManager.getLogger(TestLog4J.class);
		logger.info("Hello world");
		  logger.info("we are in logger info mode");
	}

}
