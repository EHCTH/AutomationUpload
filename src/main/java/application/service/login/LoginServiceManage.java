package application.service.login;


import domain.cookie.SeleniumCookie;

public interface LoginServiceManage {
    void login();
    SeleniumCookie extractCookies();
    void logout();

}
