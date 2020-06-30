package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.UserModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class JsonParserUtilsTest
{
    //TODO Fix error
    @Test
    void getGuestList() {
        List<UserModel> guestList = new ArrayList<>();
        String line = String.valueOf(new String[]{"latitude\":\"52.986375\", \"user_id\":12, \"name\":\"Christina McArdle\", \"longitude\":\"-6.043701"});
        JsonObject jsonObject = JsonParser.parseString(line).getAsJsonObject();
        if (new DistanceUtils().isWithinRadius(jsonObject.get("latitude").getAsDouble(),
                jsonObject.get("longitude").getAsDouble())) {
            UserModel userModel = new UserModel(
                    jsonObject.get("user_id").getAsInt(),
                    jsonObject.get("name").getAsString(),
                    jsonObject.get("latitude").getAsDouble(),
                    jsonObject.get("longitude").getAsDouble()
            );
            guestList.add(userModel);
        }
        assert jsonObject.get("user_id").getAsInt() == 12;
        assert jsonObject.get("name").getAsString().equals("Christina McArdle");
        assert jsonObject.get("latitude").getAsDouble() == 52.986375;
        assert jsonObject.get("longitude").getAsDouble() == -6.043701;
    }
}