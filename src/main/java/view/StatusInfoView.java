package view;

import view.enums.StatusInfo;

import java.util.logging.Level;

import static view.MainMenuView.logger;
import static view.MainMenuView.theUser;

public class StatusInfoView {

    public static String getUserStatus(StatusInfo status) {

        switch (status) {
            case BOOT_USER_MSG:
                return "Create un usuario o inicia sesión";
            case ERR_USER_PASS:
                return "Password incorrecta";
            case ERR_USER_NAME:
                return "El usuario no existe";
            case ERR_EVENT_ID:
                return "No hay ningún evento con ese ID";
            case ERR_EVENT_USR_DUP:
                return "Ya formas parte de este evento, no puedes unirte de nuevo";
            case ERR_EVENT_NO_AVAIL:
                return "No estás unido a ningún evento";
            case ERR_NO_VALID:
                return "Opción no válida";
            case OK_STANDARD_MSG:
                return "Hola " + theUser.getName() + "! ";
            default:
                logger.log(Level.WARNING, "Error en método User Status, ninguna condición se ha cumplido");
                return "Error en User Status";
        }

    }
}
