package dao;

import dataModels.Adventure;
import dataModels.Destination;

import java.util.List;

public class Sql2oDestinationDao implements DestinationDao {
    
    public void add(Destination newDest) {
        
    }

    public List<Destination> getAllDestinations() {
        return null;
    }

    
    public List<Adventure> getAllAdventuresByDestinations(int destinationID) {
        return null;
    }

    
    public Destination findById(int id) {
        return null;
    }

    
    public void updateLocation(int destId, String newLocation) {

    }

    
    public void removeById(int id) {

    }
}
