package fr.back_end.spring.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User Not Found")
public class SalonNotFoundException extends Exception{
    public SalonNotFoundException(String user){

        super("SalonNotFoundException with salon="+user);
    }
}
