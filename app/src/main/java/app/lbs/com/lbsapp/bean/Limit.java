package app.lbs.com.lbsapp.bean;

import java.util.List;

/**
 * 尾号限行信息
 */
public class Limit {
    /**
     * 城市代号（城市名称拼音）
     */
    private String city;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 日期
     */
    private String date;

    /**
     * 星期
     */
    private String week;

    /**
     * 限行时间
     */
    private List<String> time;

    /**
     * 限行区域
     */
    private String area;

    /**
     * 限行摘要
     */
    private String summary;

    /**
     * 限行尾号
     */
    private String numberRule;

    /**
     * 尾号规则
     */
    private String number;


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
