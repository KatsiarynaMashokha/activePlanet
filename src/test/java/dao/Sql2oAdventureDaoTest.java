package dao;

import dataModels.Adventure;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/16/17.
 */
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
    private void create() {
        new Adventure("America", "Pacific Crest Trail", "hiking trail", "6 months", "summer", 1);
    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void getAllAdventures() throws Exception {
    }

    @Test
    public void updateDescription() throws Exception {
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