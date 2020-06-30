package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO : Fix bufferedReader Parameter

/**
 * JsonParserUtils class consists of a method that returns a UserModel List of users who stay
 * within 100km radius of Intercom Office
 *
 * @author Chavvi Chandani
 */
public class JsonParserUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonParserUtils.class);

    public List<UserModel> getGuestList(BufferedReader bufferedReader) {
        // List of UserModel to store guests
        List<UserModel> guestList = new ArrayList<>();
        if (bufferedReader == null) {
            return guestList;
        }
        DistanceUtils distanceUtils = new DistanceUtils();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Parse the Json data as Json Object
                JsonObject jsonObject = JsonParser.parseString(line).getAsJsonObject();
                // Check if the latitude and longitude of the user is within 100km radius of Intercom Dublin Office
                if (distanceUtils.isWithinRadius(jsonObject.get("latitude").getAsDouble(),
                        jsonObject.get("longitude").getAsDouble())) {
                    UserModel userModel = new UserModel(
                            jsonObject.get("user_id").getAsInt(),
                            jsonObject.get("name").getAsString(),
                            jsonObject.get("latitude").getAsDouble(),
                            jsonObject.get("longitude").getAsDouble()
                    );
                    guestList.add(userModel);
                }
            }
        } catch (JsonParseException | IOException e) {
            logger.error("Error occurred in getGuestList :" + e.getMessage());
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                logger.error("Error occurred while closing the buffered reader in getGuestList :" + e.getMessage());
            }
        }
        return guestList;
    }
}
