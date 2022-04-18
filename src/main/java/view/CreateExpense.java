package view;

import models.Expense;
import view.enums.ExpenseType;
import net.je2sh.asciitable.JTable;

import java.util.HashMap;
import java.util.Map;

import static view.MainMenu.theUser;

public class CreateExpense {

    Map<Integer,ExpenseType> expenseTypeHashMap = new HashMap<>();
    public static Expense create() {

        Expense expense = new Expense();

        if (expense.getExpenseType() == null){
            for (ExpenseType e : ExpenseType.values()){
                System.out.println(getExpenseTypeString(e));
            }
        }

        return null;

    }

    private static void createUserTable(Expense expense) {

        final int MAIN_W = 45;
        final int COL_1_W = 10;
        final String description = (expense.getDescription() == null) ? "Descripción del gasto" : expense.getDescription();
        final String type = (expense.getExpenseType() == null) ? "Tipo de gasto" : getExpenseTypeString(expense.getExpenseType());
        final String cost = (expense.getCost() == 0f) ? "Coste total" : String.valueOf(expense.getCost());


        JTable.of()
                .width(MAIN_W)
                .row()
                .col().width(COL_1_W).content("ID").done()
                .col().content(theUser.getName()).done()
                .done()
                .row()
                .col().width(COL_1_W).content("TIPO").done()
                .col().content(type).done()
                .done()
                .row()
                .col().width(COL_1_W).content("DESCRIPCIÓN").done()
                .col().content(description).done()
                .done()
                .row()
                .col().width(COL_1_W).content("COSTE").done()
                .col().content(cost).done()
                .done()
                .row()
                .col().width(COL_1_W).content("TIPO").done()
                .col().content(type).done()
                .done();

    }

    public static String getExpenseTypeString(ExpenseType type) {

        switch (type) {
            case FOOD:
                return "Comida";
            case TRIP:
                return "Viaje";
            case HOUSING:
                return "Alojamiento";
            default:
                return "Otros";
        }
    }
}
