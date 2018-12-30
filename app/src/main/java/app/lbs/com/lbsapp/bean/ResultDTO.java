package app.lbs.com.lbsapp.bean;

/**
 * 反馈结果类，用于用于封装HTTP请求的反馈结果信息
 */
public class ResultDTO<T> {
    /**
     * 状态码
     */
    private int status;

    /**
     * 状态信息
     */
    private String message;

    /**
     * 返回内容
     */
    private T result;

    /**
     * 当前分页页码(首页页码为0)
     */
    private Integer pageNum;

    /**
     * 分页总页数
     */
    private Integer totalPages;

    /**
     * 项目总数
     */
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
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
}
