package cs3500.pa04.model.json.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.types.Direction;

/**
 * Represents a Json representation of a ship
 * JSON format of this record:
 * <p>
 *   <code>
 *     {
 *       "Coord": CoordJson
 *       "length": 6
 *       "direction": VERTICAL
 *     }
 *   </code>
 * </p>
 *
 * @param shipCoord the coordinate for the start point (bow) of this ship
 * @param length the int representing the size of this ship as determined by its type
 * @param direction a Direction to determine the orientation of this ship
 */
public record ShipJson(
    @JsonProperty("coord") CoordJson shipCoord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") Direction direction) {
}
