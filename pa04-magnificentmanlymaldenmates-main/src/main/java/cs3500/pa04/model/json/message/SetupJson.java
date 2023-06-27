package cs3500.pa04.model.json.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a Json representation of the setup method
 * JSON format of this record:
 * <p>
 *   <code>
 *     {
 *       "method-name": "setup"
 *       "arguments": {}
 *     }
 *   </code>
 * </p>
 *
 * @param methodName method name
 * @param arguments arguments of the method
 */
public record SetupJson(
    @JsonProperty("method-name") String methodName,
    @JsonProperty("arguments") JsonNode arguments
) {
}
