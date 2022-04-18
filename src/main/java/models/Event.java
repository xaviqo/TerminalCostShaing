package models;

import utils.Generator;

import java.io.Serializable;
import java.util.*;

public class Event implements Serializable {

    private static final long serialVersionUID = -3033776188345756312L;

    private String shareCode;
    private String ownerCode;
    private String name;
    private float total;
    private List<Expense> expenseList;
    private Map<Persona,Float> personaContribution;

    public Event() {
        this.shareCode = Generator.stringRandom(6).toUpperCase(Locale.ROOT);
        this.expenseList = new ArrayList<>();
        this.personaContribution = new HashMap<>();
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public String getShareCode(){
        return shareCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotal() {
        return total;
    }

    public List<Expense> getExpensesList() {
        return expenseList;
    }

    public void addExpense(Expense expense) {

        expenseList.add(expense);
        total = 0f;

        //recalculate total spent
        for (Expense exp: expenseList) {
            total+= exp.getCost();
        }

    }

    public Map<Persona,Float> getPersonas() {
        return personaContribution;
    }

    public boolean containsPersona(Persona persona) {
        return personaContribution.containsKey(persona);
    }

    public void setPersona(Persona persona,Float contribution) {
            personaContribution.put(persona,contribution);
    }

    @Override
    public String toString() {
        return "Event{" +
                "shareCode='" + shareCode + '\'' +
                ", ownerCode='" + ownerCode + '\'' +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", expenseList=" + expenseList +
                ", personasSet=" + personaContribution +
                '}';
    }
}
