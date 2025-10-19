package fr.nathanpasdutout.exceptions;

public class RequestFailedException extends Exception {

    private final int errorCode;

    public RequestFailedException(int errorCode) {
        super("Request has failed (error " + errorCode + ").");

        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
