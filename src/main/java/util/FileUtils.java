package util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * FileUtils class contains method to store and read user data to and from csv file
 *
 * @author Chavvi Chandani
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * Calls the WriteCsv method to write the user data to csv
     * Each file is named as per current timestamp
     *
     * @param outputFile : Relative path of csv file
     * @param guestList  : List of users to be stored in csv file
     * @return Output file path
     */
    public String storeUserDataToCsv(String outputFile, List<UserModel> guestList) {
        long fileTimeStamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        outputFile = outputFile.replace("#TIMESTAMP", sdf.format(fileTimeStamp));
        boolean isWriteSuccess = writeCsv(outputFile, guestList);
        if (!isWriteSuccess)
            return null;
        return outputFile;
    }

    /**
     * Write User data to csv file
     *
     * @param path      : Relative path of csv file
     * @param guestList : List of users to be stored in csv file
     * @return true if data is written successfully into the csv file else returns false
     */
    public boolean writeCsv(String path, List<UserModel> guestList) {
        // AutoClosable interface
        try (OutputStream outputStream = new FileOutputStream(path);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
             CSVWriter csvWriter = new CSVWriter(outputStreamWriter)) {
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(csvWriter)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(guestList);
        } catch (CsvException | IOException e) {
            logger.error("Error occurred in writeCsv " + guestList + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Read the stored csv for testing
     *
     * @param path : Relative path of csv file
     * @return The data stored in the csv as a UserModel list
     */
    public List<UserModel> readCsv(String path) {
        List<UserModel> userModelList = null;
        try {
            userModelList = new CsvToBeanBuilder(new FileReader(path)).withType(UserModel.class).build().parse();
        } catch (IOException e) {
            logger.error("Error occurred in readCSV " + path + ": " + e.getMessage());
        }
        return userModelList;
    }
}

