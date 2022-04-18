package service;

import dao.DataManager;
import models.Event;

import java.util.HashSet;
import java.util.Set;

public class EventService implements CRUD<Event>{

    DataManager<Event> eventDataManager = new DataManager<>("event", new HashSet<>());

    @Override
    public boolean save(Event event) {
        //TODO recalcular total
        return eventDataManager.save(event) != null;
    }

    @Override
    public boolean delete(Event event) {
        return eventDataManager.delete(event) != null;
    }

    @Override
    public boolean update(Event event) {
        return eventDataManager.update(event) != null;
    }

    @Override
    public Set<Event> getSet() {
        return eventDataManager.getSet();
    }

    public Event getEventById(String id){

        for (Event event: getSet()) {
            if (event.getShareCode().equalsIgnoreCase(id)) return event;
        }

        return null;
    }
}
