import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {

  private static ArrayList<Task> instances = new ArrayList<Task>();

  private String mDescription;
  private boolean mCompleted = false;
  private LocalDateTime mCreatedTime;
  private int mId;

  public Task(String description) {
    mDescription = description;
    mCreatedTime = LocalDateTime.now();
    mId = instances.size()+1;
    instances.add(this);
  }

  public String getDescription() {
    return mDescription;
  }

  public boolean isCompleted(){
    return mCompleted;
  }

  public LocalDateTime getCreatedTime(){
    return mCreatedTime;
  }

  public Task complete() {
    mCompleted = true;
    return this;
  }

  public int getId(){
  return mId;
  }

  public static Task find(int id) {
    for (Task task : instances) {
      if (id == task.getId()) {
        return task;
      }
    }
    return null;
  }

  public static void clear() {
    instances = new ArrayList<Task>();
  }

  public static ArrayList<Task> all() {
    return instances;
  }
}
