package app.lbs.com.lbsapp.bean;

/**
 * 车辆信息
 */
public class Car {
    /**
     * 车辆id
     */
    private Long id;

    /**
     * 车主用户ID
     */
    private Long userId;

    /**
     * VIN码
     */
    private String vin;

    /**
     * 车牌号
     */
    private String plate;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 发动机号
     */
    private String engine;

    /**
     * 车型
     */
    private String model;

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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
