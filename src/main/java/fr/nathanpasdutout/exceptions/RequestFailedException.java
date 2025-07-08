package fr.nathanpasdutout.exceptions;

public class RequestFailedException extends Exception {

    private final int errorCode;

    public RequestFailedException(int errorCode) {
        super("The request as failed (error " + errorCode + ").");

        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
