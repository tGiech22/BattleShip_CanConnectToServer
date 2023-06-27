package cs3500.pa04.model.json.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.types.GameResult;

/**
 * Represents an End-game argument in a JSON message
 * JSON format of this record:
 * <p>
 *   <code>
 *     {
 *       "result": WIN
 *       "reason": "reason"
 *     }
 *   </code>
 * </p>
 *
 * @param result Either "WIN", "LOSE", or "DRAW"
 * @param reason A reasonable message for the result
 */
public record EndGameArgsJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason
) {
}
