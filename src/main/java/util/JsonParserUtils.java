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

/**
 * JsonParserUtils class consists of a method that returns a UserModel List of users who stay
 * within 100km radius of Intercom Office
 *
 * @author Chavvi Chandani
 */
public class JsonParserUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonParserUtils.class);

    public List<UserModel> getGuestList(String data) {
        // List of UserModel to store guests
        List<UserModel> guestList = new ArrayList<>();
        if (data == null) {
            return guestList;
        }
        DistanceUtils distanceUtils = new DistanceUtils();
        try {
            String[] jsonStringList = data.split(System.lineSeparator());
            for (String line : jsonStringList) {
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
        } catch (JsonParseException e) {
            logger.error("Error occurred in getGuestList :" + e.getMessage());
        }
        return guestList;
    }
}
