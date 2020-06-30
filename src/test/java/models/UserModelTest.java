package models;

import org.junit.jupiter.api.Test;

class UserModelTest {
    /**
     * Test if all the values entered are correct or not
     */
    @Test
    void testUserModelCase1() {
        UserModel userModel = new UserModel(1, "Stephen McArdle", 53.038056, -7.653889);
        assert userModel.getUserId() == 1;
        assert userModel.getName().equals("Stephen McArdle");
        assert userModel.getLatitude() == 53.038056;
        assert userModel.getLongitude() == -7.653889;
    }

    @Test
    void testUserModelCase2() {
        UserModel userModel = new UserModel();
        userModel.setUserId(1);
        userModel.setName("Alice Cahill");
        userModel.setLatitude(51.92893);
        userModel.setLongitude(-10.27699);
        assert userModel.getUserId() == 1;
        assert userModel.getName().equals("Alice Cahill");
        assert userModel.getLatitude() == 51.92893;
        assert userModel.getLongitude() == -10.27699;
    }
}