package models;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

/**
 * UserModel class
 *
 * @author Chavvi Chandani
 */
public class UserModel {
    /**
     * Id of the user
     * Parameter binding done for csv column : USER ID
     */
    @CsvBindByName(column = "User Id")
    private int userId;

    /**
     * Name of the user
     * Parameter binding done for csv column : NAME
     */
    @CsvBindByName(column = "User Name")
    private String name;

    /**
     * Latitude of user's location
     */
    private double latitude;

    /**
     * Longitude of user's location
     */
    private double longitude;

    /**
     * Default constructor to create an object of the UserModel Class
     * used by OpenCSV
     */
    public UserModel() {
    }

    /**
     * Parameterized constructor
     *
     * @param userId : Id of the user
     * @param name   : Name of the user
     */
    public UserModel(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    /**
     * Parameterized constructor
     *
     * @param userId    : Id of the user
     * @param name      : Name of the user
     * @param latitude  : latitude of the user's location
     * @param longitude : longitude of the user's location
     */
    public UserModel(int userId, String name, double latitude, double longitude) {
        this.userId = userId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return userId == userModel.userId &&
                Double.compare(userModel.latitude, latitude) == 0 &&
                Double.compare(userModel.longitude, longitude) == 0 &&
                Objects.equals(name, userModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, latitude, longitude);
    }
}
