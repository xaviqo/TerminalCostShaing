package models;

import utils.Generator;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class Events implements Serializable {

    private static final long serialVersionUID = -3033776188345756312L;

    private String shareCode;
    private String name;
    private float total;
    private List<Expense> expenseList;
    private List<Persona> personasList;


    public Events() {
        this.shareCode = Generator.stringRandom(5).toUpperCase(Locale.ROOT);
    }

    public String getShareCode(){
        return shareCode;
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

    public List<Expense> getSpendingsList() {
        return expenseList;
    }

    public void addSpending(Expense expense) {

        expenseList.add(expense);
        total = 0f;

        //recalculate total spent
        for (Expense exp: expenseList) {
            total+= exp.getCost();
        }

    }

    public List<Persona> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(Persona persona) {
        personasList.add(persona);
    }
}
