# Log of Changes
- Added a ProxyController
  - used to parse and delegate messages from the server
  and parse our responses to send to the server


- Added Direction enum
  - used to determine the orientation (vertical/horizontal)
  of the ships


- Added GameMode enum
  - used to determine whether it will be Human vs. AI
  or AI vs. AI
  

- Added JsonUtils class
  - used to serialize the records in order to talk to
  the server


- Added the six different JSON message formats that
ProxyController needed to be able to handle
  - join, setup, take-shots, report-damage, successful-hits
  end-game are the message formats that the server will use to
  give information to us


- Added different JSON data definitions
  - helps convert the data in our classes into JSON and allows
  it to be serialized and sent to the server


- Added validGameMode(String[]) in Validator
utility class
  - to help ensure that only valid GameModes are used


- added setup(int, int, HashMap<ShipType, Integer>): List<Ship>
in AbstPlayerController
  - used to help setup the server AI side of the game


- added int totalCoordinates as a field in
ComputerPlayerController
  - keeps track of the total coordinates for the ships of
  the computerPlayer


- Changed Constructor for AbstPlayer, ComputerPlayer and
ManualPlayer to include a View as a parameter
  - the view is used to display the end game state to the user


- Added View view as a field in AbstPlayer
  - used to help display the end game state to the player


- Added getShipJson(): ShipJson in the Ship class
  - used to convert the data of a Ship to JSON to be used
  in other JSON data definitions