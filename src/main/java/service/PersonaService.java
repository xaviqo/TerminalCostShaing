package service;

import dao.DataManager;
import models.Persona;

import java.util.*;

public class PersonaService implements CRUD<Persona> {

    DataManager<Persona> personaDataManager = new DataManager<>("persona", new HashSet<>());

    @Override
    public boolean save(Persona persona) {
        persona.setConnected(false);
        return personaDataManager.save(persona) != null;
    }

    @Override
    public boolean delete(Persona persona) {
        return personaDataManager.delete(persona) != null;
    }

    @Override
    public boolean update(Persona persona) {
        persona.setConnected(false);
        return personaDataManager.update(persona) != null;
    }

    @Override
    public Set<Persona> getSet() {
        return personaDataManager.getSet();
    }


    /**
     * @param nameToCheck
     * @return true if username exists
     */
    public boolean checkUserName(String nameToCheck) {

        for (Persona p : getSet()) {
            if (p.getUserName().equalsIgnoreCase(nameToCheck.trim())) return true;
        }
        return false;

    }

    //si NO lo encuentra, entonces getId es null
    public Persona correctPassword(Persona check) {

        for (Persona p : getSet()) {
            if (p.getUserName().equalsIgnoreCase(check.getUserName())) {
                if (p.getUserPass().equalsIgnoreCase(check.getUserPass())) {
                    p.setConnected(true);
                    return p;
                }
            }
        }
        return check;
    }

}
