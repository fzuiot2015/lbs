package app.lbs.com.lbsapp.bean;

/**
 * 用户登录反馈
 */
public class LoginResult {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * token令牌
     */
    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
