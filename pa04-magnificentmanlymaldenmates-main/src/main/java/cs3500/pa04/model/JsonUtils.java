package cs3500.pa04.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * utils class to hold static methods that help with serializing and deserializing JSON
 */
public class JsonUtils {

  private JsonUtils() {}

  /**
   * Converts a given record object to a JsonNode
   *
   * @param givenRecord the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted
   */
  public static JsonNode serializeRecord(Record givenRecord) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(givenRecord, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("The given record cannot be serialized");
    }
  }
}
