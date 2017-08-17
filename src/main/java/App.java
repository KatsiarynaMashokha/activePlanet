import dao.Sql2oAdventureDao;
import dao.Sql2oDestinationDao;
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

        get("/", (request, response) -> {
            Map<String, Object> dataModels = new HashMap<>();

            return new ModelAndView(dataModels, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
