package dao;

import dataModels.Adventure;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAdventureDao implements AdventureDao {
    private final Sql2o sql2o;

    public Sql2oAdventureDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public void add(Adventure newFunThing) {
        String sql = "INSERT INTO adventures (destinationPoint, category, title, description, duration, peak) VALUES (:destinationPoint, :category, :title, :description, :duration, :peak)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql)
                    .addParameter("destinationPoint", newFunThing.getDestinationPoint() )
                    .addParameter("category", newFunThing.getCategory() )
                    .addParameter("title", newFunThing.getTitle())
                    .addParameter("description", newFunThing.getDescription())
                    .addParameter("duration", newFunThing.getDuration())
                    .addParameter("peak", newFunThing.getPeak())
                    .addColumnMapping("DESTINATIONPOINT", "destinationPoint")
                    .addColumnMapping("CATEGORY", "category")
                    .addColumnMapping("TITLE", "title")
                    .addColumnMapping("DESCRIPTION", "description")
                    .addColumnMapping("DURATION", "duration")
                    .addColumnMapping("PEAK", "peak")
                    .executeUpdate()
                    .getKey();
            newFunThing.setAdventureId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    
    public List<Adventure> getAllAdventuresByDestinations(int destinationId) {
        String sql = "SELECT * FROM adventures WHERE destinationPoint = :destinationPoint";
         try (Connection conn = sql2o.open()) {
             return conn.createQuery(sql)
                     .addParameter("destinationPoint", destinationId)
                     .executeAndFetch(Adventure.class);
         }
    }

    public Adventure findById(int findIt) {
        String sql = "SELECT * FROM adventures WHERE adventureId = :adventureId";
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("adventureId", findIt)
                    .executeAndFetchFirst(Adventure.class);
        }
    }

    
    public void updateDescription(int adventureId, String newContent) {
        String sql = "UPDATE adventures SET description = :description WHERE adventureId = :adventureId";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("description", newContent)
                    .addParameter("adventureId", adventureId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    
    public void updateTitle(int adventureId, String newContent) {

    }

    
    public void updateDuration(int adventureId, String newTime) {

    }

    
    public void deleteById(int adventureId) {

    }
}
