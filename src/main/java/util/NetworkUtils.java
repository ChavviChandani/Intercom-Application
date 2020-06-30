package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NetworkUtils {

    private static final Logger logger = LoggerFactory.getLogger(NetworkUtils.class);

    /**
     * Connects to the url using java's URL library
     * Returns buffered reader object after reading from the url
     */
    public BufferedReader getUrlContent(String customerUrl) {
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(customerUrl);
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            logger.error("Error occurred in getUrlContent :" + e.getMessage());
        }
        return bufferedReader;
    }
}
