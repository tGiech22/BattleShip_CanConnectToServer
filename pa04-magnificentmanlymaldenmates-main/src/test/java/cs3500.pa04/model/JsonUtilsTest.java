package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.json.message.JoinJson;
import cs3500.pa04.model.json.mock.MockJson;
import cs3500.pa04.model.pieces.Board;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Test class for JsonUtilsTest
 */
public class JsonUtilsTest {

  /**
   * Testing when JsonUtils.serializedRecord fails
   */
  @Test
  public void testSerializeRecord() {
    ObjectMapper mapper = new ObjectMapper();
    JoinJson testJson = new JoinJson("join", mapper.createObjectNode());
    JsonNode testJsonResult = JsonUtils.serializeRecord(testJson);

    String testJsonResultStr = testJsonResult.toString();
    assertEquals(testJsonResultStr, "{\"method-name\":\"join\",\"arguments\":{}}");
  }

  /**
   * Testing when JsonUtils.serializeRecord fails
   */
  @Test
  public void testSerializeRecordFail() {
    Board board = new Board(5, 5, new ArrayList<>());
    assertThrows(IllegalArgumentException.class,
        () -> JsonUtils.serializeRecord(new MockJson(board)));
  }
}