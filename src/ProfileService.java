

import javax.swing.JOptionPane;

public class ProfileService {

    public static void getDetailUser(User users) {
       // System.out.println(users.getUsername() + " a " + users.getAddress().toString());
    }

    public static void showChangePhoneForm() {
        ChangePhoneForm tel = new ChangePhoneForm();
        tel.show();
    }

    public static void showChangeAddressForm() {
        ChangeAddressForm address = new ChangeAddressForm();
        address.show();
    }
    public static void showMainPageForm() {
       MainPage mainpage = new MainPage();
        mainpage.show();
    }

    public static void showProfileForm() {
        ProfileForm profile = new ProfileForm();
        profile.show();
    }

    public static void showMainpageForm() {
        MainPage mainpage = new MainPage();
        mainpage.show();
    }

    public static boolean checkNull(String textNull) {
        if (textNull.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "กรุณากรอกข้อมูล",
                    "พบข้อผิดพลาด",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static User getUser(String username) {
        return UserDao.getUser(username);
    }

    public static boolean changePhone(User user, String phone) {
        User thisUser = user;
        thisUser.setPhone(phone);
        if (UserDao.updatePhone(thisUser)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean changeAddress(User user, String address) {
        User thisUser = user;
        thisUser.setAddress(address);
        if (UserDao.updateAddress(thisUser)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkLengthPhone(String length) {

        if (length.length() == 10) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "กรุณากรอกหมายเลขโทรศัพท์ให้ครบ 10 หลัก",
                    "พบข้อผิดพลาด",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean checkFristNumber(String length) {

        if (length.substring(0, 1).equals("0")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "กรุณาตรวจสอบหมายเลขโทรศัพท์อีกครั้ง",
                    "พบข้อผิดพลาด",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
