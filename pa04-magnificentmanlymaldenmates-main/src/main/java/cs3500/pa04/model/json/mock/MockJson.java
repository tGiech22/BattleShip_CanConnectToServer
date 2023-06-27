package cs3500.pa04.model.json.mock;

import cs3500.pa04.model.pieces.Board;

/**
 * Represents a mock Json class
 *
 * @param board board cannot be serialized
 */
public record MockJson(
    Board board
) {
}
