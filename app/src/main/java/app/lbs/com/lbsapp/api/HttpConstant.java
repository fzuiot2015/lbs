package app.lbs.com.lbsapp.api;

public interface HttpConstant {
    String HOST = "http://47.101.34.9:8081/";

    String LOGIN = HOST + "user/login";
    String REGISTER = HOST + "user/register";
    String OIL_PROVINCE = HOST + "oil/province";//查询省份
    String INQUIRE = HOST + "oil/price";
    String Exam = HOST + "exam";
}
