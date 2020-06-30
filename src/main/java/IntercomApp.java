import constants.AppConstants;
import models.UserModel;
import util.FileUtils;
import util.JsonParserUtils;
import util.NetworkUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * The IntercomApp reads the Json data from the URL
 * Json data is parsed using JsonParser
 * All the users within 100km radius are stored in a List of UserModel
 * List is sorted by UserId
 * Sorted list is stored in a csv file
 *
 * @author Chavvi Chandani
 */
public class IntercomApp {

    public static void main(String[] args) throws IOException {
        new IntercomApp().doTask();
    }

    public void doTask() {
        // Reading text file from the given url
        BufferedReader bufferedReader = new NetworkUtils().getUrlContent(AppConstants.CUSTOMER_URL);
        // Extracting the user information using Json parser and storing it in a List of UserModel
        List<UserModel> guestList = new JsonParserUtils().getGuestList(bufferedReader);
        // Sorting the guestList by UserId
        guestList.sort(Comparator.comparingInt(UserModel::getUserId));
        // Stored list is stored as csv file
        new FileUtils().storeUserDataToCsv(AppConstants.OUTPUT_FILE, guestList);
    }

}
