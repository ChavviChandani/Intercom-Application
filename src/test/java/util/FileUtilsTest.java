package util;

import constants.AppConstants;
import models.UserModel;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    /**
     * Test if the data gets stored in the csv file
     */
    @Test
    void testWriteCsv() {
        List<UserModel> userModelList = new ArrayList<>(Arrays.asList(new UserModel(8,"Eoin Ahearn"),new UserModel(11,"Richard Finnegan")));
        String path = "output/guests_2020_06_26_21_00.csv";
        assertTrue(new FileUtils().writeCsv(path,userModelList));
    }

    /**
     * Test if the data stored csv is equal to the data read in the UserModel List
     */
    @Test
    void readCSV() {
        String path = "output/guests_2020_06_26_21_00.csv";
        List<UserModel> userModelListActual = new ArrayList<>(Arrays.asList(new UserModel(8,"Eoin Ahearn"),new UserModel(11,"Richard Finnegan")));
        List<UserModel> userModelList = new FileUtils().readCsv(path);
        assertTrue(userModelListActual.equals(userModelList));
    }
}