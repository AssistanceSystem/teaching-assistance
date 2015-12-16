package util;

/**
 * Created by zhihu on 15/12/16.
 */
public class CheckRegister {


    public static boolean checkLoginName(String loginName){
//        String EMAIL_VERIFY = "/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-zA-Z0-9_-])+/";
        String EMAIL_VERIFY = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
        return loginName.matches(EMAIL_VERIFY);

    }

    public static boolean checkPasswordLength(String password){
        if (password.length()<=16 && password.length()>=6){
            return true;
        }else {
            return false;
        }
    }
}
