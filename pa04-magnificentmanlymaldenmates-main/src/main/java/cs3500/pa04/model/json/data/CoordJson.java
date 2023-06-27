package cs3500.pa04.model.json.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Json representation of a Coord
 * JSON format of this record:
 * <p>
 *   <code>
 *     {
 *       "x": 2
 *       "y": 3
 *     }
 *   </code>
 * </p>
 *
 * @param x the x coordinate
 * @param y the y coordinate
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y) {
}
