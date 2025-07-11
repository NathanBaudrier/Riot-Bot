# RiotBot

RiotBot is a Java-based Discord bot that uses the JDA library for interaction with Discord and the Riot REST API to fetch League of Legends data. It provides various commands to enhance the user experience on Discord servers. The bot allows users to check their League of Legends rank, register their Riot accounts, monitor server statuses, and more.

## Features and Commands

| **Command**        | **Description**                                                                                     | **Example**                |
|---------------------|-----------------------------------------------------------------------------------------------------|----------------------------|
| `/ping`            | Retrieves the bot's ping to check responsiveness.                                                  | `/ping`                    |
| `/register`        | Registers a Riot account into the bot's database using game name and tagline.                       | `/register SummonerName EUW` |
| `/rank`            | Fetches the rank of a League of Legends player in Solo Queue or Flex Queue.                        | `/rank Solo @UserName`     |
| `/status`          | Displays the current status of EUW servers, including maintenance or incidents.                     | `/status`                  |

## Technologies Used

- [**Java**](https://docs.oracle.com/en/java/): Core programming language for the bot.
- [**JDA (Java Discord API)**](https://jda.wiki): Library for interacting with Discord.
- [**Riot REST API**](https://developer.riotgames.com): Fetch data related to League of Legends accounts, ranks, and server statuses.

## Invite RiotBot to Your Server

Use the following link to invite RiotBot to your Discord server:  
[Invite RiotBot](https://discord.com/oauth2/authorize?client_id=1338465299267325982&permissions=8&integration_type=0&scope=bot+applications.commands)

## Contact

For any questions or suggestions, feel free to reach out:
- [Discord](https://discord.gg/2QpKsvGxqQ)