package dao;

import dataModels.Adventure;
import dataModels.Destination;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDestinationDao implements DestinationDao {
    private final Sql2o sql2o;

    public Sql2oDestinationDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public void add(Destination newDest) {
        String sql = "INSERT INTO destinations (location) VALUES (:location)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql)
                    .addParameter("location", newDest.getLocation())
                    .addColumnMapping("LOCATION", "location")
                    .executeUpdate()
                    .getKey();
            newDest.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public List<Destination> getAllDestinations() {
        String sql = "SELECT * FROM destinations";
         try(Connection con = sql2o.open()) {
           return con.createQuery(sql)
                    .executeAndFetch(Destination.class);
        }
    }

    public Destination locationAlreadyExists(String location) {
        String sql = "SELECT * FROM destinations WHERE location = :location";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("location", location)
                    .executeAndFetchFirst(Destination.class);
        }
    }

    public List<Adventure> getAllAdventuresByDestinations(int destinationID) {
        String sql = "SELECT * FROM adventures WHERE destinationPoint = :destinationPoint";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("destinationPoint", destinationID)
                    .executeAndFetch(Adventure.class);
        }
    }

    public Destination findById(int id) {
        String sql = "SELECT * FROM destinations WHERE id = :id";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Destination.class);
        }
    }

    public void updateLocation(int destId, String newLocation) {
        String sql = "UPDATE destinations SET location = :location WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", destId)
                    .addParameter("location", newLocation)
                    .executeUpdate();
        }  catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    
    public void removeById(int id) {
        String sql = "DELETE FROM destinations WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
