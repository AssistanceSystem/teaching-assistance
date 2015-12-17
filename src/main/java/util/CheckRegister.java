package util;

/**
 * Created by zhihu on 15/12/16.
 */
public class CheckRegister {


    public static boolean checkLoginName(String loginName) {
        final String emailVerify = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
        return loginName.matches(emailVerify);

    }

    public static boolean checkPasswordLength(String password) {
        return password.length() <= 16 && password.length() >= 6;
    }
}
