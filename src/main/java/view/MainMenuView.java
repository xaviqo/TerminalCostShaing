package view;

import models.Event;
import models.Expense;
import models.Persona;
import net.je2sh.asciitable.JTable;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import service.EventService;
import service.PersonaService;
import utils.UtilConsole;
import view.enums.StatusInfo;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static view.StatusInfoView.getUserStatus;

public class MainMenuView {

    public static TextIO textIO = TextIoFactory.getTextIO();
    public static Logger logger = Logger.getLogger("xavi.compartirpagos");
    public static boolean showStatusInfo = false;
    public static final int MAIN_W = 55;
    public static final int COL_MENU_W = 1;

    public static final int COL_W = 10;
    public static final String IS_INFO_OK = "Los datos son correctos?";
    public static Persona theUser = new Persona();
    public static Event theEvent = new Event();

    public static Expense theExpense = new Expense();

    public static EventService eventService = new EventService();
    public static PersonaService personaService = new PersonaService();

    public static void main(String[] args) {

        try {

            FileHandler fileHandler = new FileHandler("log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException ioe) {
            logger.log(Level.WARNING, "No se ha podido iniciar el logger! |" + ioe);
        }

        menu();

    }

    public static void menu() {

        do {

            //change USER STATUS INFO in next switch loop
            if (theUser.isConnected() && !showStatusInfo) {
                theUser.setUserStatusInfo(StatusInfo.OK_STANDARD_MSG);
            } else if (!theUser.isConnected() && !showStatusInfo) {
                theUser.setUserStatusInfo(StatusInfo.BOOT_USER_MSG);
            } else {
                showStatusInfo = false;
            }

            UtilConsole.clearConsole();

            //print info msg
            showUserInfo().forEach(System.out::println);

            //test
            System.out.println("_____________");
            for (Event e : eventService.getSet()) System.out.println(e.toString());
            for (Persona p : personaService.getSet()) System.out.println(p.toString());
            System.out.println("_____________");

            //print options menu
            mainMenuTable().forEach(System.out::println);

            //line space
            System.out.println("");

            //main switch
            switch (textIO.newIntInputReader().withMinVal(0).withMaxVal(4).read("Menú: ")) {
                case 1:
                    if (theUser.isConnected()) {
                        EventMenuView.createEvent(new Event());
                    } else {
                        UserMenuView.createUser(new Persona());
                    }
                    break;
                case 2:
                    if (theUser.isConnected()) {
                        EventMenuView.joinEvent();
                    } else {
                        theUser = UserMenuView.logIn(theUser);
                    }
                    break;
                case 3:
                    if (theUser.isConnected()) {
                        EventMenuView.myEvents();
                    } else {
                        theUser.setUserStatusInfo(StatusInfo.ERR_NO_VALID);
                        showStatusInfo = true;
                    }
                    break;
                case 0:
                    if (theUser.isConnected()) {
                        theUser.setConnected(false);
                        theUser.setUserStatusInfo(StatusInfo.BOOT_USER_MSG);
                    } else {
                        System.out.println("Hasta pronto!");
                        System.exit(0);
                    }
                    break;
                default:
                    theUser.setUserStatusInfo(StatusInfo.ERR_NO_VALID);
                    showStatusInfo = true;
                    break;
            }

        } while (true);

    }

    private static List<String> mainMenuTable() {

        if (theUser.isConnected()) {

            //IF USER IS LOGGED IN
            return JTable.of()
                    .width(MAIN_W)
                    .row()
                    .col().width(COL_MENU_W).content("1").done()
                    .col().content("CREAR EVENTO").done()
                    .done()
                    .row()
                    .col().width(COL_MENU_W).content("2").done()
                    .col().content("UNIARME A EVENTO").done()
                    .done()
                    .row()
                    .col().width(COL_MENU_W).content("3").done()
                    .col().content("GESTIONAR MIS EVENTOS").done()
                    .done()
                    .row()
                    .col().width(COL_MENU_W).content("0").done()
                    .col().content("DESCONECTAR").done()
                    .done().render();

        } else {

            return JTable.of()
                    .width(MAIN_W)
                    .row()
                    .col().width(COL_MENU_W).content("1").done()
                    .col().content("CREAR USUARIO").done()
                    .done()
                    .row()
                    .col().width(COL_MENU_W).content("2").done()
                    .col().content("INICIAR SESIÓN").done()
                    .done()
                    .row()
                    .col().width(COL_MENU_W).content("0").done()
                    .col().content("SALIR").done()
                    .done().render();

        }

    }

    public static List<String> showUserInfo() {

        //TODO: desarrollar con info de balance, eventos a medias...

        return JTable.of()
                .width(MAIN_W + 1)
                .row()
                .col().content(getUserStatus(theUser.getUserStatusInfo())).done()
                .done().render();

    }

}

