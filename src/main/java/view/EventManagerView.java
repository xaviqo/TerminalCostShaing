package view;

import models.Event;
import models.Expense;
import models.Persona;
import net.je2sh.asciitable.JTable;
import utils.UtilConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static view.CreateExpenseView.getExpenseTypeString;
import static view.EventMenuView.infoIntoTable;
import static view.MainMenuView.*;

public class EventManagerView {

    public static void managerMenu() {

        do {

            float total = 0f;

            UtilConsole.clearConsole();
            //calc total (antes de printar balances, importante!)
            //comprobar si con el getTotal es suficionete
            //ya que cada vez que se añade un expense se suma al total ya existente
            for (Expense exp : theEvent.getExpensesList()) {
                total += exp.getCost();
            }
            theEvent.setTotal(total);

            System.out.println();
            System.out.println("# EVENTO ################");
            //print event name
            infoIntoTable("EVENTO", theEvent.getName());

            System.out.println();
            System.out.println("# MIEMBROS ##############");
            //print members
            usersIntoTable(theEvent.getPersonas().keySet());

            System.out.println();
            System.out.println("# GASTOS ###############");
            //print expenses
            printExpenses(theEvent.getExpensesList())
                    .forEach(jTable -> jTable.render().forEach(System.out::println));

            System.out.println();
            System.out.println("# BALANCE ##############");
            //print balance
            BalanceView.calculate();

            System.out.println();
            System.out.println("# TOTAL ################");
            //print total
            infoIntoTable("TOTAL", String.format("%.02f", total) + "€");

            System.out.println();
            System.out.println("# MENÚ #################");
            //show menu
            eventOptions().forEach(System.out::println);

            switch ((textIO.newIntInputReader().withMinVal(0).withMaxVal(2)
                    .read("Selecciona una opción: "))) {

                case 1:
                    CreateExpenseView.create(new Expense());
                    break;
                case 2:
                    //TODO cerrar evento
                    break;
                case 0:
                    MainMenuView.menu();
                    break;
                default:
                    break;

            }

        } while (true);

    }

    private static List<String> eventOptions() {

        return JTable.of()
                .width(MAIN_W)
                .row()
                .col().width(COL_MENU_W).content("1").done()
                .col().content("Añadir nuevo gasto").done()
                .done()
                .row()
                .col().width(COL_MENU_W).content("2").done()
                .col().content("Cerrar evento").done()
                .done()
                .row()
                .col().width(COL_MENU_W).content("0").done()
                .col().content("volver").done()
                .done().render();
    }

    public static List<JTable> printExpenses(List<Expense> expenseList){

        List<JTable> jTableList = new ArrayList<>();

        for (Expense expense: expenseList){

            jTableList.add(JTable.of().width(MAIN_W)
                    .row()
                    .col().width(10).content(expense.getPersona().getName()).done()
                    .col().width(45).content(expense.getDescription()).done()
                    .done()
                    .row()
                    .col().content(String.valueOf(expense.getDate())).done()
                    .col().content(getExpenseTypeString(expense.getExpenseType())).done()
                    .col().content(String.format("%.02f",expense.getCost())+"€").done()
                    .done());

        }

        return jTableList;

    }

    public static void usersIntoTable(Set<Persona> setPersona) {

        StringBuilder members = new StringBuilder();
        int totalMembers = 0;

        for (Persona persona : setPersona) {
            totalMembers++;
            members.append(persona.getName());
            if (totalMembers < setPersona.size()) {
                members.append(", ");
            }
        }

        infoIntoTable("MIEMBROS", String.valueOf(members));
    }
}

/*

 # LISTA DE GASTOS [ GASTO - USUARIO - TIPO - DESC - FECHA ]
                    TOTAL GASTOS: ####

 # DEUDA DE CADA UNO

 1 - AÑADIR GASTO | 2 - CERRAR EVENTO
 */