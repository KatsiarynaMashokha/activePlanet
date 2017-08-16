package dao;

import dataModels.Adventure;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oAdventureDao implements AdventureDao {
    private final Sql2o sql2o;

    public Sql2oAdventureDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public void add(Adventure newFunThing) {
        
    }

    
    public List<Adventure> getAllAdventures(int destinationId) {
        return null;
    }

    
    public void updateDescription(int adventureId, String newContent, int destinationPoint) {

    }

    
    public void updateTitle(int adventureId, String newContent, int destinationPoint) {

    }

    
    public void updateDuration(int adventureId, String newTime, int destinationPoint) {

    }

    
    public void deleteById(int adventureId) {

    }
}
