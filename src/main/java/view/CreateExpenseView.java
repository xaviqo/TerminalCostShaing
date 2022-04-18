package view;

import models.Expense;
import utils.UtilConsole;
import view.enums.ExpenseType;
import net.je2sh.asciitable.JTable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static view.EventMenuView.infoIntoTable;
import static view.MainMenuView.*;

public class CreateExpenseView {
    public static void create(Expense expense) {

        UtilConsole.clearConsole();

        if (expense.getPersona() == null) {
            expense.setPersona(theUser);
            expense.setDate(LocalDate.now());
        }

        showExpenseTable(expense).forEach(System.out::println);

        if (expense.getDescription() == null) {
            expense.setDescription(textIO.newStringInputReader().withMinLength(6).read("Nombre del gasto:"));
            create(expense);
        }

        if (expense.getExpenseType() == null) {

            Map<Integer, ExpenseType> expenseTypeMap = new HashMap<>();
            int optionCounter = 0;
            String optionsString = "";

            for (ExpenseType expenseType : ExpenseType.values()) {
                optionCounter++;
                optionsString += "[" + optionCounter + "] - " + getExpenseTypeString(expenseType) + System.lineSeparator();
                expenseTypeMap.put(optionCounter, expenseType);
            }

            infoIntoTable("TIPO", optionsString);
            expense.setExpenseType(expenseTypeMap.get(textIO.newIntInputReader()
                    .withMaxVal(optionCounter).read("Tipo de gasto:")));

            create(expense);

        }

        if (expense.getCost() == 0) {
            expense.setCost(textIO.newFloatInputReader().withMinVal(0.01f).withMaxVal(9999f).read("Coste del gasto:"));
            create(expense);
        } else {
            if (textIO.newBooleanInputReader().read(IS_INFO_OK)) {
                theEvent.addExpense(expense);

                //calculate contribution for each persona

                if (theEvent.getPersonas().containsKey(theUser)) {

                    if (theEvent.getPersonas().get(theUser) > 0f) {
                        theEvent.setPersona(theUser, theEvent.getPersonas().get(theUser) + expense.getCost());
                    } else {
                        theEvent.setPersona(theUser, expense.getCost());
                    }

                } else {
                    theEvent.setPersona(theUser, expense.getCost());
                }

                eventService.update(theEvent);
                System.out.println("Gasto añadido");
                EventManagerView.managerMenu();

            } else {
                System.out.println("No se ha añadido ningún gasto nuevo");
            }
        }

    }

    public static List<String> showExpenseTable(Expense expense) {

        final String description = (expense.getDescription() == null) ? "Descripción del gasto" : expense.getDescription();
        final String type = (expense.getExpenseType() == null) ? "Tipo de gasto" : getExpenseTypeString(expense.getExpenseType());
        final String cost = (expense.getCost() == 0f) ? "Coste total" : String.valueOf(expense.getCost());


        return JTable.of()
                .width(MAIN_W)
                .row()
                .col().width(COL_W).content("USUARIO").done()
                .col().content(expense.getPersona().getName()).done()
                .done()
                .row()
                .col().width(COL_W).content("FECHA").done()
                .col().content(expense.getDate()).done()
                .done()
                .row()
                .col().width(COL_W).content("DESCRP.").done()
                .col().content(description).done()
                .done()
                .row()
                .col().width(COL_W).content("TIPO").done()
                .col().content(type).done()
                .done()
                .row()
                .col().width(COL_W).content("COSTE").done()
                .col().content(cost).done()
                .done().render();

    }

    public static String getExpenseTypeString(ExpenseType type) {

        switch (type) {
            case FOOD:
                return "Comida";
            case TRIP:
                return "Viaje";
            case HOUSING:
                return "Estancia";
            default:
                return "Otros";
        }
    }
}
