package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.json.data.CoordJson;
import cs3500.pa04.model.json.data.VolleyJson;
import org.junit.jupiter.api.Test;

/**
 * Test class for VolleyJson
 */
public class VolleyJsonTest {

  /**
   * testing VolleyJson
   */
  @Test
  public void testVolleyJson() {
    CoordJson coord1 = new CoordJson(0, 0);
    CoordJson coord2 = new CoordJson(1, 0);
    CoordJson coord3 = new CoordJson(0, 1);

    CoordJson[] test = new CoordJson[3];
    test[0] = coord1;
    test[1] = coord2;
    test[2] = coord3;

    VolleyJson volley = new VolleyJson(test);

    assertEquals(volley.volley().length, 3);
    assertEquals(volley.volley()[0].x(), 0);
    assertEquals(volley.volley()[1].x(), 1);
    assertEquals(volley.volley()[2].y(), 1);
  }
}
