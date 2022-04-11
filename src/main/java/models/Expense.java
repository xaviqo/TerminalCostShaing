package models;

public class Expense {

    private String description;
    private float cost;
    private Persona persona;
    private ExpenseType expenseType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ExpenseType getSpendingType() {
        return expenseType;
    }

    public void setSpendingType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
