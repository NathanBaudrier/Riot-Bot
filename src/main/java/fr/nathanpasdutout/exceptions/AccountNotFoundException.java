package fr.nathanpasdutout.exceptions;

import net.dv8tion.jda.api.entities.User;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(User user) {
        super("No account found for user " + user.getName());
    }
}
