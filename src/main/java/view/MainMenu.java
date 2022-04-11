package view;

import models.Persona;
import net.je2sh.asciitable.JTable;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import service.PersonaService;
import utils.UtilConsole;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainMenu {

    public static TextIO textIO = TextIoFactory.getTextIO();
    public static Logger logger = Logger.getLogger("xavi.compartirpagos");
    public static boolean isConnected = false;
    private static final int MAIN_MENU_W = 20;
    private static final int COL_MENU_W = 1;
    public static Persona theUser = new Persona();

    public static PersonaService personaService = new PersonaService();

    public static void main(String[] args) {

        for (Persona p : personaService.getList()) System.out.println(p.toString());

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

        boolean loop = true;

        do {


            UtilConsole.clearConsole();
            showUserInfo(theUser).forEach(System.out::println);

            mainMenuTable().forEach(System.out::println);
            System.out.println("");

            switch (textIO.newIntInputReader().withMaxVal(5).read("Menú: ")) {
                case 1:
                    UserView.createUser(theUser);
                    break;
                case 2:
                    theUser = UserView.logIn(theUser);
                    break;
                case 3:
                    if (isConnected(theUser)){

                    } else {
                        theUser.setUserStatusInfo(UserStatusInfo.NO_VALID);
                    }
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("Hasta pronto!");
                    loop = false;
                    break;
                default:
                    break;
            }
        } while (loop);

    }

    private static List<String> mainMenuTable() {

        JTable mainMenuTable;

        if (isConnected(theUser)){
            mainMenuTable = JTable.of()
                    .width(MAIN_MENU_W)
                    .row()
                    .col().width(COL_MENU_W).content("1").done()
                    .col().content("CREAR USUARIO").done()
                    .done()
                    .row()
                    .col().width(COL_MENU_W).content("2").done()
                    .col().content("INICIAR SESIÓN").done()
                    .done()
                    .row()
                    .col().width(COL_MENU_W).content("3").done()
                    .col().content("CREAR EVENTO").done()
                    .done()
                    .row()
                    .col().width(COL_MENU_W).content("0").done()
                    .col().content("SALIR").done()
                    .done();
        } else {
            mainMenuTable = JTable.of()
                    .width(MAIN_MENU_W)
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
                    .done();
        }

        return mainMenuTable.render();

    }

    public static List<String> showUserInfo(Persona persona) {

        //TODO: desarrollar con info de balance, eventos a medias...

        String userNameInfo = "";

        if (persona.getUserStatusInfo().equals(UserStatusInfo.BOOT_USER_MSG)) {
            userNameInfo = "Create un usuario o inicia sesión";
        } else if (persona.getUserStatusInfo().equals(UserStatusInfo.BAD_USER_PASS)){
            userNameInfo = "Password incorrecta";
        } else if (persona.getUserStatusInfo().equals(UserStatusInfo.BAD_USER_NAME)){
            userNameInfo = "El usuario no existe";
        } else if (persona.getUserStatusInfo().equals(UserStatusInfo.NO_VALID)){
            userNameInfo = "Opción no válida";
        } else if (persona.getUserStatusInfo().equals(UserStatusInfo.CONNECTED)){
            userNameInfo = "Hola de nuevo "+persona.getName()+"!";
        }

        JTable mainMenuTable = JTable.of()
                .width(MAIN_MENU_W+1)
                .row()
                .col().content(userNameInfo).done()
                .done();

        return mainMenuTable.render();

    }

    public static boolean isConnected(Persona persona){
        return persona.getUserStatusInfo().equals(UserStatusInfo.CONNECTED);
    }

//            System.out.println("[1] - REGISTRAR PERSONA");
//            System.out.println("[2] - CREAR NUEVO EVENTO");
//            System.out.println("[3] - AÑADIR PERSONAS A EVENTO");
//            System.out.println("[4] - AÑADIR PAGOS A EVENTO");
//            System.out.println("[5] - MOSTRAR INFO DE EVENTO");
//            System.out.println("[6] - CERRAR EVENTO");
//            System.out.println("[0] - SALIR");
}

