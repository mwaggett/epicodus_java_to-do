import java.util.ArrayList;

public class Category {

  private static ArrayList<Category> instances = new ArrayList<Category>();

  private String mName;
  private int mId;
  private ArrayList<Task> mTaskList = new ArrayList<Task>();


  public Category(String name) {
    mName = name;
    instances.add(this);
    mId = instances.size();
  }

  public String getName() {
    return mName;
  }

  public int getId() {
    return mId;
  }

  public ArrayList<Task> getTasks() {
    return mTaskList;
  }

  public static ArrayList<Category> all() {
    return instances;
  }

  public static void clear() {
    instances.clear();
  }

  public static Category find(int id) {
    try{
      return instances.get(id-1);
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public void addTask(Task task){
    mTaskList.add(task);
  }
}
