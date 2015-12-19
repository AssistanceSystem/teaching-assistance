package util;

/**
 * Created by zhihu on 15/12/16.
 */
public class CheckRegister {


    public static boolean checkLoginName(String loginName) {
        final String emailVerify =
                "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return loginName.matches(emailVerify);

    }

    public static boolean checkPasswordLength(String password) {
        return password.length() <= 16 && password.length() >= 6;
    }
}
