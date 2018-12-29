package app.lbs.com.lbsapp.bean;

import java.util.List;

public class Limit {
    private String city;        //城市代号
    private String cityName;    //城市名称
    private String date;        //日期
    private String week;        //星期
    private List<String> time;  //限行时间
    private String area;        //限行区域
    private String summary;     //限行摘要
    private String numberRule;  //限行尾号
    private String number;      //尾号规则

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNumberRule() {
        return numberRule;
    }

    public void setNumberRule(String numberRule) {
        this.numberRule = numberRule;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
