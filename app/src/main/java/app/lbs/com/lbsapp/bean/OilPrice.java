package app.lbs.com.lbsapp.bean;

/**
 * 油价信息
 */
public class OilPrice {
    /**
     * 油价信息id
     */
    private Long id;

    /**
     * 省份
     */
    private String province;

    /**
     * 0号柴油价格
     */
    private Float dieselOil0;

    /**
     * 90号汽油价格
     */
    private Float gasoline90;

    /**
     * 93号汽油价格
     */
    private Float gasoline93;

    /**
     * 97号汽油价格
     */
    private Float gasoline97;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Float getDieselOil0() {
        return dieselOil0;
    }

    public void setDieselOil0(Float dieselOil0) {
        this.dieselOil0 = dieselOil0;
    }

    public Float getGasoline90() {
        return gasoline90;
    }

    public void setGasoline90(Float gasoline90) {
        this.gasoline90 = gasoline90;
    }

    public Float getGasoline93() {
        return gasoline93;
    }

    public void setGasoline93(Float gasoline93) {
        this.gasoline93 = gasoline93;
    }

    public Float getGasoline97() {
        return gasoline97;
    }

    public void setGasoline97(Float gasoline97) {
        this.gasoline97 = gasoline97;
    }
}
