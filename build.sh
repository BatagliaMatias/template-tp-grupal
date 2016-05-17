#SERVER
echo "--------------------- Generating SERVER jar --------------------- "
./gradlew server

#CLIENT
echo "--------------------- Generating CLIENT jar --------------------- "
./gradlew client

#GAMES
echo "--------------------- Generating GAMES jars --------------------- "
./gradlew OpenDoor2
