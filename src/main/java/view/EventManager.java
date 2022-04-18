package view;

import models.Event;
import models.Persona;
import net.je2sh.asciitable.JTable;

import java.util.Set;

public class EventManager {

    private static final int FIXED_LENGTH = 45;

    public static void managerMenu(Event event) {

        //print event name
        infoIntoTable(event.getName());

        //print members
        usersIntoTable(event.getPersonasSet());
    }

    public static void infoIntoTable(String info) {

        JTable.of()
                .width(FIXED_LENGTH)
                .row()
                .col().content(info).done()
                .done().render().forEach(System.out::println);

    }

    public static void usersIntoTable(Set<Persona> setPersona) {

        final int colWith = 10;
        StringBuilder members = new StringBuilder();
        int totalMembers = 0;

        for (Persona persona : setPersona){
            totalMembers++;
            members.append(persona.getName());
            if (totalMembers < setPersona.size()){
                members.append(", ");
            }
        }

        JTable.of()
                .width(FIXED_LENGTH)
                .row()
                .col().width(colWith).content("MEMBERS: ").done()
                .col().content(members).done()
                .done().render().forEach(System.out::println);

    }

}

/*

 # LISTA DE GASTOS [ GASTO - USUARIO - TIPO - DESC - FECHA ]
                    TOTAL GASTOS: ####

 # DEUDA DE CADA UNO

 1 - AÑADIR GASTO | 2 - CERRAR EVENTO
 */