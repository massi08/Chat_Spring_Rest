package fr.back_end.spring.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User Not Found")
public class MessageNotFoundException extends Exception{

    public MessageNotFoundException(int numero){
        super("MessageNotFoundException with numero=" + numero);
    }

    public MessageNotFoundException(String message){
        super("MessageNotFoundException with " + message);
    }
}
