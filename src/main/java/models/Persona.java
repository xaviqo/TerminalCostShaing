package models;

import utils.Generator;
import view.enums.StatusInfo;

import java.io.Serializable;
import java.util.*;

public class Persona implements Serializable {

    private static final long serialVersionUID = -7454854208470095912L;

    private String id;
    private String name;
    private String userName;
    private String userPass;
    private float balance;
    private boolean connected;
    private StatusInfo statusInfo;
    private Set<String> eventsIdSet;

    public Persona() {
        this.id = Generator.stringRandom(8).toUpperCase(Locale.ROOT);
        this.balance = 0f;
        this.statusInfo = StatusInfo.BOOT_USER_MSG;
        this.eventsIdSet = new HashSet<>();
    }

    public StatusInfo getUserStatusInfo() {
        return statusInfo;
    }

    public void setUserStatusInfo(StatusInfo statusInfo) {
        this.statusInfo = statusInfo;
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

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
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

    public Set<String> getEvents() {
        return eventsIdSet;
    }

    public void setEvents(String eventId) {
        this.eventsIdSet.add(eventId);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        return id.equals(persona.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", balance=" + balance +
                ", connected=" + connected +
                ", statusInfo=" + statusInfo +
                ", eventsIdSet=" + eventsIdSet +
                '}';
    }
}
