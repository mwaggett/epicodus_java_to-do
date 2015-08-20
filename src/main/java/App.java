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
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/categories", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      ArrayList<Category> categories = request.session().attribute("categories");
      if (categories == null) {
        categories = new ArrayList<Category>();
        request.session().attribute("categories", categories);
      }
      model.put("categories", categories);
      model.put("template", "templates/categories.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/categories/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/category-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/categories", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      ArrayList<Category> categories = request.session().attribute("categories");
      if (categories == null) {
        categories = new ArrayList<Category>();
        request.session().attribute("categories", categories);
      }
      Category newCategory = new Category(request.queryParams("name"));
      categories.add(newCategory);

      model.put("categories", categories);
      model.put("template", "templates/categories.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/categories/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String,Object>();
      Category category = Category.find(Integer.parseInt(request.params(":id")));
      ArrayList<Task> tasks = request.session().attribute("tasks");
      if (tasks == null) {
        tasks = new ArrayList<Task>();
        request.session().attribute("tasks", tasks);
      }
      tasks = category.getTasks();
      model.put("category", category);
      model.put("tasks",tasks);
      model.put("template", "templates/category.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/categories/:id/tasks/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Category category = Category.find(Integer.parseInt(request.params(":id")));
      
      model.put("category", category);
      model.put("template", "templates/category-tasks-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/categories/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Category category = Category.find(Integer.parseInt(request.params(":id")));
      ArrayList<Task> tasks = request.session().attribute("tasks");
      if (tasks == null) {
        tasks = new ArrayList<Task>();
        request.session().attribute("tasks", tasks);
      }
      Task newTask = new Task(request.queryParams("description"));
      category.addTask(newTask);
      tasks = category.getTasks();
      model.put("category", category);
      model.put("tasks",tasks);
      model.put("template", "templates/category.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/Tasks", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //
    //   ArrayList<Task> tasks = request.session().attribute("tasks");
    //
    //   if (tasks == null) {
    //     tasks = new ArrayList<Task>();
    //     request.session().attribute("tasks", tasks);
    //   }
    //
    //   String description = request.queryParams("description");
    //   Task newTask = new Task(description);
    //
    //   tasks.add(newTask);
    //
    //   model.put("template", "templates/success.vtl");
    //   return new ModelAndView(model, layout);
    //  }, new VelocityTemplateEngine());
    //
    //  post("/completed", (request, response) -> {
    //    HashMap<String, Object> model = new HashMap<String, Object>();
    //
    //    ArrayList<Task> tasks = request.session().attribute("tasks");
    //
    //    for (Iterator<Task> iterator = tasks.iterator(); iterator.hasNext();) {
    //      Task task = iterator.next();
    //      boolean completed = Boolean.parseBoolean(request.queryParams("taskComplete-"+task));
    //      if (completed) {
    //        tasks.remove(task);
    //      }
    //    }
    //
    //    model.put("template", "templates/completed.vtl");
    //    return new ModelAndView(model, layout);
    //   }, new VelocityTemplateEngine());
    //
    //   post("/find", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //
    //     ArrayList<Task> tasks = request.session().attribute("tasks");
    //
    //     Integer findId = Integer.parseInt(request.queryParams("findTask"));
    //
    //     Task foundTask = Task.find(findId);
    //     model.put("foundTask", foundTask);
    //
    //     model.put("template", "templates/find.vtl");
    //     return new ModelAndView(model, layout);
    //    }, new VelocityTemplateEngine());
  }
}
