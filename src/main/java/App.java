import dao.Sql2oAdventureDao;
import dao.Sql2oDestinationDao;
import dataModels.Adventure;
import dataModels.Destination;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import sun.security.krb5.internal.crypto.Des;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/activeplanet.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");

        //instantiate Data Access Objects for adventures and destinations
        Sql2oAdventureDao adventureDao = new Sql2oAdventureDao(sql2o);
        Sql2oDestinationDao destinationDao = new Sql2oDestinationDao(sql2o);



        // homepage
        get("/", (request, response) -> {
            Map<String, Object> dataModels = new HashMap<>();
            dataModels.put("destinations", destinationDao.getAllDestinations());
            return new ModelAndView(dataModels, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //link to new destination form
        get("/destinations/new", (request, response) -> {
            Map<String, Object> dataModels = new HashMap<>();
            return new ModelAndView(dataModels, "addInfo.hbs");
        }, new HandlebarsTemplateEngine());

        //process new destination form
        post("/" ,(request, response) -> {
            Map<String, Object> dataModels = new HashMap<>();
            String newDest = request.queryParams("newSpot");
            destinationDao.add(new Destination(newDest));
            dataModels.put("destinations", destinationDao.getAllDestinations());
            return new ModelAndView(dataModels, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //display adventures for each destination
        get("/destinations/:destID/adventures", (request, response) -> {
            Map<String, Object> dataModels = new HashMap<>();
            int destID = Integer.parseInt(request.params("destID") );
            Destination foundDest = destinationDao.findById(destID);
            dataModels.put("destination", foundDest);

            dataModels.put("adventures", adventureDao.getAllAdventuresByDestinations(destID) );

            return new ModelAndView(dataModels, "adventures.hbs");
        }, new HandlebarsTemplateEngine());

//        process new adventure
        post("/destinations/:destID/adventures", (request, response) -> {
            Map<String, Object> dataModels = new HashMap<>();
            int destID = Integer.parseInt(request.params("destID"));
            String newTitle = request.queryParams("title");
            String category = request.queryParams("category");
            String description = request.queryParams("description");
            String duration = request.queryParams("duration");
            String peak = request.queryParams("peak");
            adventureDao.add(new Adventure(category, newTitle, description, duration, peak, destID));
            response.redirect("/destinations/" + destID + "/adventures");
            return null;
        });

        //show details of specific adventure
        get("/destinations/:destID/adventures/:adventureID", (request, response) -> {
            Map<String, Object> dataModels = new HashMap<>();
            int adID = Integer.parseInt(request.params("adventureID"));
            Adventure thisAdventure = adventureDao.findById(adID);
            dataModels.put("currentAdventure", thisAdventure);
            return new ModelAndView(dataModels, "details.hbs");
        }, new HandlebarsTemplateEngine());

        // show a form to update an adventure
        get("/destinations/:destID/adventures/:adventureID/edit", (request, response) -> {
            Map<String, Object> dataModels = new HashMap<>();
            int adID = Integer.parseInt(request.params("adventureID"));
            Adventure thisAdventure = adventureDao.findById(adID);
            dataModels.put("currentAdventure", thisAdventure);
            return new ModelAndView(dataModels, "update-adventure.hbs");
        }, new HandlebarsTemplateEngine());


        //update a new adventure
        post("/destinations/:destId/adventures/:adventureID/update", (request, response) -> {

//            int destID = Integer.parseInt(request.params("destId"));
            int adID = Integer.parseInt(request.params("adventureID"));
            String newTitle = request.queryParams("title");
            String description = request.queryParams("description");
            String duration = request.queryParams("duration");

            adventureDao.updateTitle(adID, newTitle);
            adventureDao.updateDescription(adID, description);
            adventureDao.updateDuration(adID, duration);
            int destID = adventureDao.findById(adID).getDestinationPoint();

            response.redirect("/destinations/" + destID + "/adventures/" + adID);
            return null;
        });
    }
}
