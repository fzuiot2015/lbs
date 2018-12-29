package app.lbs.com.lbsapp.api;

public interface HttpConstant {
    //    String HOST = "http://47.101.34.9:8081/";
    String HOST = "http://192.168.1.5:8081/";

    String LOGIN = HOST + "user/login";
    String REGISTER = HOST + "user/register";
    String OIL_PROVINCE = HOST + "oil/province";//查询省份
    String INQUIRE = HOST + "oil/price";
    String Exam = HOST + "exam";
    String USER_INFO = HOST + "personal/user";
    String CAR_INFO = HOST + "personal/car";
    String INSURANCE_INFO = HOST + "personal/insurance";
    String LIMIT_CITY = HOST + "limit/city";
    String LIMIT = HOST + "limit";
    String ACCIDENT = HOST + "accident";

    String HOSPITAL_PHONE = "15060123639";
}
