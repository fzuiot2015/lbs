package app.lbs.com.lbsapp.bean;

public class BaseBean {

    /**
     * status : 0
     * message : 成功
     * result : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Mzg4MTczNDAsImlhdCI6MTUzODgxNzI4MCwidXNlcm5hbWUiOiIxMjMifQ.V9jlSvObNEImcS_6nrelNyTn56oSrZmVtPk3onaY5AY
     * pageNum : null
     * totalPages : null
     * total : null
     */

    private int status;
    private String message;
    private String result;
    private String pageNum;
    private String totalPages;
    private String total;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
}
