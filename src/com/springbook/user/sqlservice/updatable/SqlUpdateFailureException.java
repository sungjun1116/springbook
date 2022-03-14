package com.springbook.user.sqlservice.updatable;

public class SqlUpdateFailureException extends RuntimeException {
    public SqlUpdateFailureException() {
        super();
    }

    public SqlUpdateFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlUpdateFailureException(String message) {
        super(message);
    }

    public SqlUpdateFailureException(Throwable cause) {
        super(cause);
    }
}
