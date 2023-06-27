package cs3500.pa04.model.types;

/**
 * Represents an enumeration type for the mode the game is in
 * (Single (human v ai)/Multi (ai v ai))
 */
public enum GameMode {

  /**
   * represents the game mode where a human player plays against our AI
   */
  SINGLE,

  /**
   * represents the game mode where our AI plays against another AI
   */
  MULTI,
}
