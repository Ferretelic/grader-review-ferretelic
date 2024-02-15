import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testFilterNoMoon() {
    List<String> array = Arrays.asList("a", "b", "c", "d", "e", "f");
    List<String> expected = Arrays.asList();
    StringChecker sc = new IsMoon();
    List<String> filtered = ListExamples.filter(array, sc);
    assertEquals(expected, filtered);
  }

  @Test(timeout = 500)
  public void testFilterWithSingleMoon() {
    List<String> array = Arrays.asList("a", "moon", "c", "d", "e", "f");
    List<String> expected = Arrays.asList("moon");
    StringChecker sc = new IsMoon();
    List<String> filtered = ListExamples.filter(array, sc);
    assertEquals(expected, filtered);
  }

  @Test(timeout = 500)
  public void testFilterWithMultipleMoon() {
    List<String> array = Arrays.asList("a", "moon", "c", "moon", "e", "moon");
    List<String> expected = Arrays.asList("moon", "moon", "moon");
    StringChecker sc = new IsMoon();
    List<String> filtered = ListExamples.filter(array, sc);
    assertEquals(expected, filtered);
  }

  @Test(timeout = 500)
  public void testMergeBothSorted() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("d", "e");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "b", "c", "d", "e");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeSparatelySorted() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "b");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "b", "c");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeBothUnsorted() {
    List<String> left = Arrays.asList("c", "b", "a");
    List<String> right = Arrays.asList("e", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "b", "c", "d", "e");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeSeparatelyUnsorted() {
    List<String> left = Arrays.asList("c", "b", "a");
    List<String> right = Arrays.asList("b", "a");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "b", "c");
    assertEquals(expected, merged);
  }
}
