package cs3500.pa04.model.json.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a setup argument that was sent by the server
 * JSON format of this record:
 * <p>
 *   <code>
 *     {
 *       "width": 7
 *       "height": 10
 *       "fleetSpec": fleetSpec
 *     }
 *   </code>
 * </p>
 *
 * @param width width of board
 * @param height height of board
 * @param fleetSpec specifications for the fleet on the board
 */
public record SetupArgumentsJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") FleetSpecJson fleetSpec
) {
}
