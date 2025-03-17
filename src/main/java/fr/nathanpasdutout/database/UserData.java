package fr.nathanpasdutout.database;

import fr.nathanpasdutout.Main;
import net.dv8tion.jda.api.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserData {

    private final User user;
    private final Database database;

    public UserData(User user) {
        this.user = user;
        this.database = Main.getDatabase();
    }

    public boolean hasData() {
        ResultSet result = this.database.sendQuery("SELECT * FROM user WHERE discord_id = " + user.getId());

        try {
            return result.next();
        } catch(SQLException e) {
            System.err.println("Error while trying to get data: " + e.getMessage());
        }

        try {
            result.close();
        } catch(SQLException e) {
            System.err.println("Error while closing the SQL result: " + e.getMessage());
        }

        return false;
    }

    public void createData(String PUUID) {
        this.database.sendQuery("INSERT INTO user VALUES (" + this.user.getId() + "," + PUUID + ");");
    }

    public String getPUUID() {
        ResultSet result = this.database.sendQuery("SELECT puuid FROM user WHERE discord_id = " + this.user.getId());

        try {
            if(result.next()) {
                return result.getString("puuid");
            }
        } catch(SQLException e) {
            System.err.println("Error while trying to get PUUID: " + e.getMessage());
        }

        try {
            result.close();
        } catch(SQLException e) {
            System.err.println("Error while closing the SQL result: " + e.getMessage());
        }

        return null;
    }
}
