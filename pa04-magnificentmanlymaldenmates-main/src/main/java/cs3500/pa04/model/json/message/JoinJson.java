package cs3500.pa04.model.json.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a Json representation of the join method
 * JSON format of this record:
 * <p>
 *   <code>
 *     {
 *       "method-name": "join"
 *       "arguments": {}
 *     }
 *   </code>
 * </p>
 *
 * @param methodName method name
 * @param arguments arguments of the method
 */
public record JoinJson(
    @JsonProperty("method-name") String methodName,
    @JsonProperty("arguments") JsonNode arguments
) {
}
