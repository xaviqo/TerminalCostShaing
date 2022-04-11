package service;

import dao.DataManager;
import models.Persona;
import view.UserStatusInfo;

import java.util.ArrayList;
import java.util.List;

public class PersonaService implements CRUD<Persona> {

    DataManager<Persona> personaDataManager = new DataManager<>("persona", new ArrayList<>());

    @Override
    public boolean save(Persona checkPersona) {
        return personaDataManager.save(checkPersona) != null;
    }

    @Override
    public boolean delete(Persona checkPersona) {
        return personaDataManager.delete(checkPersona) != null;
    }

    @Override
    public boolean update(Persona checkPersona) {
        return personaDataManager.update(checkPersona) != null;
    }

    @Override
    public List<Persona> getList() {
        return personaDataManager.getList();
    }


    /**
     * @param userAvailable
     * @return true if username exists
     */
    public boolean checkUserName(String userAvailable) {

        for (Persona p : getList()) {
            if (p.getUserName().equalsIgnoreCase(userAvailable.trim())) return true;
        }
        return false;

    }

    //si NO lo encuentra, entonces getId es null
    public Persona correctPassword(Persona checkPersona) {

        for (Persona p : getList()) {
            if (p.getUserName().equalsIgnoreCase(checkPersona.getUserName())) {
                if (p.getUserPass().equalsIgnoreCase(checkPersona.getUserPass())) {
                    p.setUserStatusInfo(UserStatusInfo.CONNECTED);
                    return p;
                }
            }
        }
        checkPersona.setUserStatusInfo(UserStatusInfo.BAD_USER_PASS);
        return checkPersona;
    }

}
