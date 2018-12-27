package app.lbs.com.lbsapp.bean;

/**
 * 油价数据POJO
 */
public class OilPrice {
    private int status;
    private String message;
    private OilBean result;
    private Integer pageNum;
    private Integer totalPages;
    private Long total;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OilBean getResult() {
        return result;
    }

    public void setResult(OilBean result) {
        this.result = result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    public class OilBean {
        private Long id;
        private String province;    //省份
        private Float dieselOil0;        //0号柴油价格

        private Float gasoline90;       //90号汽油价格

        private Float gasoline93;       //93号汽油价格

        private Float gasoline97;       //97号汽油价格

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
}
