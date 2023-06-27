package cs3500.pa04.model.json.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Json representation of a volley (consists of an array of coord)
 * JSON format of this record:
 * <p>
 *   <code>
 *     {
 *       "volley": CoordJson[]
 *     }
 *   </code>
 * </p>
 *
 * @param volley the volley of shots represented by an array of coords
 */
public record VolleyJson(
    @JsonProperty("coordinates") CoordJson[] volley) {
}
