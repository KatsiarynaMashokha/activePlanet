package dao;

import dataModels.Adventure;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oAdventureDaoTest {
    private Sql2oAdventureDao adventureDao;
    private Connection con;


    @Before
    public void setUp() throws Exception {
        final String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        adventureDao = new Sql2oAdventureDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    // helper
    private Adventure create() {
        return new Adventure("America", "Pacific Crest Trail", "hiking trail", "6 months", "summer", 1);
    }

    @Test
    public void addAdventureSetsId() throws Exception {
        Adventure test = create();
        Adventure test2 = create();
        adventureDao.add(test);
        adventureDao.add(test2);
        assertEquals(2, test2.getAdventureId());
    }

    @Test
    public void getAllAdventuresByDestination() throws Exception {
        Adventure test = create();
        Adventure test2 = create();
        adventureDao.add(test);
        adventureDao.add(test2);
        int destinationID = test2.getDestinationPoint();
        assertEquals(2, adventureDao.getAllAdventuresByDestinations(destinationID).size());
    }

    @Test
    public void updateDescription() throws Exception {
        Adventure test = create();
        adventureDao.add(test);
        adventureDao.updateDescription(test.getAdventureId(), "birthday shots");
        Adventure updated = adventureDao.findById(test.getAdventureId());
        assertEquals("birthday shots", updated.getDescription());
    }

    @Test
    public void updateTitle() throws Exception {
    }

    @Test
    public void updateDuration() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

}