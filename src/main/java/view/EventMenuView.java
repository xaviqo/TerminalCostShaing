package view;

import models.Event;
import net.je2sh.asciitable.JTable;
import utils.UtilConsole;
import view.enums.StatusInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static view.MainMenuView.*;

public class EventMenuView {

    public static void createEvent(Event event) {

        //Print table
        UtilConsole.clearConsole();
        createEventTable(event).forEach(System.out::println);
        System.out.println("Comparte el CÓDIGO del evento para que otros usuarios se unan");
        System.out.println();

        if (event.getName() == null) {
            event.setName(textIO.newStringInputReader().withMinLength(6).read("Nombre del evento:"));
            createEvent(event);
        } else {
            if (textIO.newBooleanInputReader().read(IS_INFO_OK)) {
                event.setOwnerCode(theUser.getId());
                event.setPersona(theUser,0f);
                theUser.setEvents(event.getShareCode());
                personaService.update(theUser);
                //al guardar theUser se reinicia y hay que volver a setear;
                theUser.setConnected(true);
                theUser.setUserStatusInfo(StatusInfo.OK_STANDARD_MSG);
                eventService.save(event);
                MainMenuView.menu();
            } else {
                event.setName(null);
                createEvent(event);
            }
        }


    }

    private static List<String> createEventTable(Event event) {

        final String name = (event.getName() == null) ? "Sin asignar" : event.getName();

        JTable createUserTable = JTable.of()
                .width(MAIN_W)
                .row()
                .col().width(COL_W).content("CÓDIGO").done()
                .col().content(event.getShareCode()).done()
                .done()
                .row()
                .col().width(COL_W).content("EVENTO").done()
                .col().content(name).done()
                .done();

        return createUserTable.render();

    }

    //asigna evento al usuario conectado y se lo guarda en su lista de ventos que participa
    //guarda el evento con el nuevo participante
    public static void joinEvent() {

        Event event = new Event();
        //Print table
        UtilConsole.clearConsole();
        System.out.println("Introduce el ID del evento al que te vas a unir");
        System.out.println();

        event = eventService.getEventById(textIO.newStringInputReader().read("ID:"));

        if (event != null) {
            if (!event.containsPersona(theUser)) {
                event.setPersona(theUser,0f);
                theUser.setEvents(event.getShareCode());
                personaService.update(theUser);
                eventService.update(event);
                theUser.setConnected(true);
                theUser.setUserStatusInfo(StatusInfo.OK_STANDARD_MSG);
            } else {
                theUser.setUserStatusInfo(StatusInfo.ERR_EVENT_USR_DUP);
                showStatusInfo = true;
            }
        } else {
            theUser.setUserStatusInfo(StatusInfo.ERR_EVENT_ID);
            showStatusInfo = true;
        }

    }

    public static void myEvents() {

        UtilConsole.clearConsole();

        if (theUser.getEvents().size() > 0) {

            theEvent = showMyEvents().get(textIO.newIntInputReader().withMaxVal(theUser.getEvents().size())
                    .read("Que evento quieres gestionar?"));

        } else {
            theUser.setUserStatusInfo(StatusInfo.ERR_EVENT_NO_AVAIL);
            showStatusInfo = true;
        }

        if (theEvent.getName() != null){
            EventManagerView.managerMenu();
        }

    }

    private static Map<Integer,Event> showMyEvents() {

        Event event;
        int optionCounter = 0;
        Map<Integer,Event> eventsIds = new HashMap<>();

        for (String eventIdStr : theUser.getEvents()) {
            if ((event = eventService.getEventById(eventIdStr)) != null) {
                optionCounter++;
                infoIntoTable(String.valueOf(optionCounter),event.getName());
                eventsIds.put(optionCounter,event);
            }
        }

        return eventsIds;
    }

    public static void infoIntoTable(String colText, String info) {

        JTable.of()
                .width(MAIN_W)
                .row()
                .col().width(COL_W).content(colText).done()
                .col().content(info).done()
                .done().render().forEach(System.out::println);

    }

}
