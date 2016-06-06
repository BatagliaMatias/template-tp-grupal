#SERVER
echo "--------------------- Generating SERVER jar --------------------- "
./gradlew server

#CLIENT
echo "--------------------- Generating CLIENT jar --------------------- "
./gradlew client

#GAMES
echo "--------------------- Generating GAMES jars --------------------- "
./gradlew OpenDoor2
./gradlew PickStick
./gradlew WolfSheepCol
./gradlew TreasureHunt
