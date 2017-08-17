import dao.Sql2oAdventureDao;
import dao.Sql2oDestinationDao;
import dataModels.Destination;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

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


    }
}
