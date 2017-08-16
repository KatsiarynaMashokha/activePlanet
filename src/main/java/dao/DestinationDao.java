package dao;


import dataModels.*;
import java.util.List;

public interface DestinationDao {
    // create a new dest
    void add(Destination newDest);

    // read information
    List<Destination> getAllDestinations();

    Destination findById(int id);

    // update
    void updateLocation(int destId, String newLocation);

    // delete
    void removeById(int id);
}
