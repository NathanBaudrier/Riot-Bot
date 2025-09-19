#!/bin/bash

SESSION_NAME="riot-bot"

JAR_PATH="target/RiotBot-1.0-SNAPSHOT.jar"

if screen -list | grep -q "$SESSION_NAME"; then
    echo "The screen session '$SESSION_NAME' is already in progress."
    echo "Stopping the current session to restart..."
    screen -S "$SESSION_NAME" -X quit
    echo "The session '$SESSION_NAME' has been stopped."
fi

mvn clean package
screen -dmS "$SESSION_NAME" java -jar "$JAR_PATH"