package models;

import utils.Generator;
import view.UserStatusInfo;

import java.io.Serializable;
import java.util.Locale;

public class Persona implements Serializable {

    private static final long serialVersionUID = -7454854208470095912L;

    private String id;
    private String name;
    private String userName;
    private String userPass;
    private float balance;
    private UserStatusInfo userStatusInfo;

    public Persona() {
        this.id = Generator.stringRandom(8).toUpperCase(Locale.ROOT);
        this.balance = 0f;
        this.userStatusInfo = UserStatusInfo.BOOT_USER_MSG;
    }

    public UserStatusInfo getUserStatusInfo() {
        return userStatusInfo;
    }

    public void setUserStatusInfo(UserStatusInfo userStatusInfo) {
        this.userStatusInfo = userStatusInfo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balances) {
        this.balance = balances;
    }


    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", balance=" + balance +
                '}';
    }
}
