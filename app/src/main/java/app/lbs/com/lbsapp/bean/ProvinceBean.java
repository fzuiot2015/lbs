package app.lbs.com.lbsapp.bean;

import java.util.List;

/**
 * Created by Jessie on 2018/10/8.
 */

public class ProvinceBean {
    //{"status":0,"message":"成功","result":["上海","云南","内蒙古","北京","吉林","四川","天津","宁夏","安徽","山东","山西","广东","广西","新疆","江苏","江西","河北","河南","浙江","海南","湖北","湖南","甘肃","福建","西藏","贵州","辽宁","重庆","陕西","青海","黑龙江"],"pageNum":null,"totalPages":null,"total":null}
    private int status;
    private String message;
    private String pageNum;
    private String totalPages;
    private String total;
    private List<String> result;

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

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

}
