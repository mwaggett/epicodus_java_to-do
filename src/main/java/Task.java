import static org.fluentlenium.core.filter.FilterConstructor.*;

public class Task {
  private String mDescription;

  public Task(String description) {
    mDescription = description;
  }

  public String getDescription() {
    return mDescription;
  }
}