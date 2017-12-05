package database;

import javax.persistence.*;
@Entity
@Table(name = "LOGIN_LOG")
public class LoginLog extends ObjectsDAO
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LOGIN_ID")
    private Long loginId;
    @Column(name = "LOGIN_TIME")
    private Long loginTime;
    @Column(name = "LOGOUT_TIME")
    private Long logoutTime;
    @Column(name = "IP")
    private String ip;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public LoginLog() {}
    /*
    public LoginLog(User user, Long loginTime, Long logoutTime, String ip) {
        this.user = user;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.ip = ip;
    }
    */
    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Long logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}