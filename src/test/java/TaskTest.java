import org.junit.*;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class TaskTest {

  @Test
  public void Task_instantiatesCorrectly_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(true, myTask instanceof Task);
  }

  @Test
  public void task_instantiatesWithDescription_true() {
  Task myTask = new Task("Mow the lawn");
  assertEquals("Mow the lawn", myTask.getDescription());
  }
}
