package fr.back_end.spring.Controller;


public class HandlerException {

    private String message;

    public HandlerException(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "AuthException [ message=" + message + "]";
    }
}
