package ru.javaops.topjava.util.exception;

public class ExpiredVoteException extends RuntimeException {
    public ExpiredVoteException(String message) {
        super(message);
    }
}
