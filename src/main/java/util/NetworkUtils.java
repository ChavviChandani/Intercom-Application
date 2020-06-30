package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class NetworkUtils {

    private static final Logger logger = LoggerFactory.getLogger(NetworkUtils.class);

    /**
     * Connects to the url using java's URL library
     * Returns passed text file as a String object after reading from the url
     */
    public String getUrlContent(String customerUrl) {
       String content = null;
        try {
            URL url = new URL(customerUrl);
            try (InputStream is = url.openStream();
                 BufferedReader br = new BufferedReader(
                         new InputStreamReader(is, StandardCharsets.UTF_8))) {
                content = br.lines().collect(
                        Collectors.joining(System.lineSeparator()));
            }catch (IOException e) {
                logger.error("Error occurred in getUrlContent :", e);
            }
        } catch (IOException e) {
            logger.error("Error occurred in getUrlContent :" + e.getMessage());
        }
        return content;
    }
}


