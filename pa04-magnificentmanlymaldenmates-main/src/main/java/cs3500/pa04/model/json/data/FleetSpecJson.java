package cs3500.pa04.model.json.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a fleet specification in a JSON message
 * JSON format of this record:
 * <p>
 *   <code>
 *     {
 *       "Carrier": 2
 *       "Battleship": 1
 *       "Destroyer": 1
 *       "Submarine": 3
 *     }
 *   </code>
 * </p>
 *
 * @param carrier amount of carriers to be created
 * @param battleship amount of battleships to be created
 * @param destroyer amount of destroyers to be created
 * @param submarine amount of submarines to be created
 */
public record FleetSpecJson(
    @JsonProperty("CARRIER") int carrier,
    @JsonProperty("BATTLESHIP") int battleship,
    @JsonProperty("DESTROYER") int destroyer,
    @JsonProperty("SUBMARINE") int submarine
) {
}
