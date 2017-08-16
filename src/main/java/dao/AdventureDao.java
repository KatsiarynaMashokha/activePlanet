package dao;

import dataModels.Adventure;

import java.util.List;

public interface AdventureDao {

    //create
    void add(Adventure newFunThing);

    //read
    List<Adventure> getAllAdventuresByDestinations (int destinationID);

    //update
    void updateDescription(int adventureId, String newContent, int destinationPoint);
    void updateTitle(int adventureId, String newContent, int destinationPoint);
    void updateDuration(int adventureId, String newTime, int destinationPoint);

    //delete
    void deleteById(int adventureId);
}
