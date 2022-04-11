package view;

import models.Persona;
import net.je2sh.asciitable.JTable;
import service.PersonaService;
import utils.UtilConsole;

import java.util.List;

import static view.MainMenu.*;

public class UserView {

    public static void createUser(Persona persona) {

        String userName = "";

        //Print table
        UtilConsole.clearConsole();
        createUserTable(persona).forEach(System.out::println);
        System.out.println();

        if (persona.getName() == null) {
            persona.setName(textIO.newStringInputReader().read("Como te llamas?"));
            createUser(persona);
        } else if (persona.getUserName() == null) {
            userName = textIO.newStringInputReader().withMinLength(4).read("Usuario:");
            //To check is username is available (if true, username already exists)
            if (personaService.checkUserName(userName)) {
                return;
            }
            persona.setUserName(userName);
            createUser(persona);
        } else if (persona.getUserPass() == null) {
            persona.setUserPass(textIO.newStringInputReader().withMinLength(4).withInputMasking(true).read("Password: "));
            createUser(persona);
        } else {
            if (textIO.newBooleanInputReader().read("Son los datos correctos?")) {
                personaService.save(persona);
                MainMenu.menu();
            } else {
                //nullize info to keep the same id
                persona.setName(null);
                persona.setUserName(null);
                persona.setUserPass(null);
                createUser(persona);
            }
        }
    }

    private static List<String> createUserTable(Persona persona) {

        final int MAIN_W = 45;
        final int COL_1_W = 10;
        final String name = (persona.getName() == null) ? "Tu nombre de pila" : persona.getName();
        final String user = (persona.getUserName() == null) ? "Tu nombre de usuario" : persona.getUserName();
        final String pass = (persona.getUserPass() == null) ? "Tu contraseña" : "********";

        JTable createUserTable = JTable.of()
                .width(MAIN_W)
                .row()
                .col().width(COL_1_W).content("ID").done()
                .col().content(persona.getId()).done()
                .done()
                .row()
                .col().width(COL_1_W).content("NOMBRE").done()
                .col().content(name).done()
                .done()
                .row()
                .col().width(COL_1_W).content("USUARIO").done()
                .col().content(user).done()
                .done()
                .row()
                .col().width(COL_1_W).content("PASSWORD").done()
                .col().content(pass).done()
                .done();

        return createUserTable.render();

    }

    public static Persona logIn(Persona persona) {

        String userName = "";

        //Print table
        UtilConsole.clearConsole();

        userName = textIO.newStringInputReader().read("Usuario:");
        if (personaService.checkUserName(userName)) {
            persona.setUserName(userName);
            persona.setUserPass(textIO.newStringInputReader()
                    .withMinLength(4).withInputMasking(true).read("Password: "));
            return personaService.correctPassword(persona);
        } else {
            persona.setUserStatusInfo(UserStatusInfo.BAD_USER_NAME);
        }

        return persona;
    }

}
