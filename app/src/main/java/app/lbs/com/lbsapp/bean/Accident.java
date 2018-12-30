package app.lbs.com.lbsapp.bean;

public class Accident {
    /**
     * 事故ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 时间
     */
    private String time;

    /**
     * 纬度值
     */
    private Float lat;

    /**
     * 经度值
     */
    private Float lng;

    /**
     * 地点
     */
    private String address;

    /**
     * 详情描述
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
