package com.imooc.excepation;

public class UserNotExistException extends RuntimeException{
    private String id;



    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public UserNotExistException(String message, String id) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
