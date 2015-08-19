import java.util.Map;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalDateTime;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("tasks", request.session().attribute("tasks"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

post("/Tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      ArrayList<Task> tasks = request.session().attribute("tasks");

      if (tasks == null) {
        tasks = new ArrayList<Task>();
        request.session().attribute("tasks", tasks);
      }

      String description = request.queryParams("description");
      Task newTask = new Task(description);

      tasks.add(newTask);

      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     post("/completed", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();

       ArrayList<Task> tasks = request.session().attribute("tasks");

       for (Iterator<Task> iterator = tasks.iterator(); iterator.hasNext();) {
         Task task = iterator.next();
         boolean completed = Boolean.parseBoolean(request.queryParams("taskComplete-"+task));
         if (completed) {
           tasks.remove(task);
         }
       }

       model.put("template", "templates/completed.vtl");
       return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
  }
}
