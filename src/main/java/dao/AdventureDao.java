package dao;

import dataModels.Adventure;

import java.util.List;

public interface AdventureDao {

    //create
    void add(Adventure newFunThing);

    //read
    List<Adventure> getAllAdventuresByDestinations (int destinationID);
    Adventure findById(int id);

    //update
    void updateDescription(int adventureId, String newContentdestination);
    void updateTitle(int adventureId, String newContentdestination);
    void updateDuration(int adventureId, String newTimedestination);

    //delete
    void deleteById(int adventureId);
}
