package site.nebulas.demo.entity;

/**
 * Created by Nebula on 2017/10/6.
 */

public class UserInfo {
    private String name;
    private String password;


    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
