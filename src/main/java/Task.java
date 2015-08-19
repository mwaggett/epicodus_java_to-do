public class Task {
  private String mDescription;
  private boolean mCompleted = false;

  public Task(String description) {
    mDescription = description;
  }

  public String getDescription() {
    return mDescription;
  }

  public boolean isCompleted(){
    return mCompleted;
  }

  public Task complete() {
    mCompleted = true;
    return this;
  }
}
