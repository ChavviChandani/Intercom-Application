package util;

import models.UserModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class JsonParserUtilsTest {
    @Test
    void testgetGuestList() {
        String data = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\"," +
                " \"longitude\": \"-6.043701\"}" +
                System.lineSeparator() +
                "{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", " +
                "\"longitude\": \"-10.27699\"}";
        // Although 2 user details are passed but only 1 user is added to the guestList because they are within 100km
        // radius of Intercom Dublin Office
        List<UserModel> guestListActual = new ArrayList<>(Arrays.asList(
                new UserModel(12, "Christina McArdle", 52.986375, -6.043701)
        ));
        List<UserModel> guestList = new JsonParserUtils().getGuestList(data);
        assert guestListActual.equals(guestList);
    }
}