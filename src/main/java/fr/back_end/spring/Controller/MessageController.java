package fr.back_end.spring.Controller;

import com.Chat.Modele.Message;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageController {
    private Map<String, ArrayList<Message>> salons;
    private Map<String, ArrayList<String>> usersAllowedToChat;

    @GetMapping(value = "/back-office/messages/{salon}/{user}/all", produces = {"application/json",
            "application/xml", "text/html"})
    public ModelAndView receiveGetSalon(@PathVariable(value = "salon", required = true) String salon,
                                        @PathVariable(value = "user", required = true) String user,
                                        HttpSession session) throws Exception{

        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        ModelAndView model = new ModelAndView();
        if (salons.get(salon) != null) {
            ArrayList<Message> messages = salons.get(salon);
            model.setViewName("messages_all");
            if (messages == null) {
                throw new MessageNotFoundException("messages not found");
            }
            model.addObject("messagesList", messages);
            model.addObject("user", user);
            return model;
        } else {
            throw new SalonNotFoundException(salon);
        }
    }

    @GetMapping(value = "/back-office/message/{numero}", produces = {"application/json", "application/xml", "text/html"})
    public ModelAndView receiveGetSalonNumero(@PathVariable(value = "numero", required = true) String numero,
                                              HttpSession session)  throws Exception{
        String salon = (String)session.getAttribute("salon");
        String user = (String)session.getAttribute("username");
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        int numeroInt = Integer.valueOf(numero) - 1;
        if (numeroInt <= 0) {
            numeroInt = 0;
        }
        ModelAndView model = new ModelAndView("messages_nbr");
        boolean hasMessage = false;
        model.addObject("numero", numero);
        if (salons.get(salon) != null) {
            if (salons.get(salon).size() > numeroInt) {
                hasMessage = true;
                Message message = salons.get(salon).get(numeroInt);
                if (message != null) {
                    model.addObject("hasMessage", hasMessage);
                    model.addObject("message", salons.get(salon).get(numeroInt));
                    model.addObject("user", user);
                }
            }
            else {
                throw new MessageNotFoundException(numeroInt);
            }
        } else {
            throw new SalonNotFoundException(salon);
        }
        return model;
    }

    @PostMapping(value = "/back-office/messages/{salon}/{user}/add", produces = {"application/json", "application/xml", "text/html"})
    public ModelAndView addMessage(@RequestParam(value = "usermessage", required = true) String message,
                                   @PathVariable(value = "salon", required = true) String salon,
                                   @PathVariable(value = "user", required = true) String user,
                                   HttpSession session)throws Exception{
        ModelAndView model = new ModelAndView("affichage_spring");

        Message mes = new Message(user, message);
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        if (salons.get(salon) != null) {
            salons.get(salon).add(mes);
        } else {
            salons.put(salon, new ArrayList<Message>());
            salons.get(salon).add(mes);
        }
        model.addObject("messagesList", salons.get(salon));
        model.addObject("user", user);
        return model;
    }

    @GetMapping(value = "/back-office/messages/{salon}/{user}/last", produces = {"application/json", "application/xml", "text/html"})
    @ResponseBody
    public ModelAndView getLastMessage(@PathVariable(value = "salon", required = true) String salon,
                                       @PathVariable(value = "user", required = true) String user,
                                       HttpSession session)throws Exception{
        ModelAndView model = new ModelAndView("messages_nbr");
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        int numeroInt = Integer.valueOf(salons.get(salon).size()) - 1;
        if (numeroInt <= 0) {
            numeroInt = 0;
        }
        boolean isMessage = false;
        if (salons.get(salon) != null) {
            if (salons.get(salon).size() > numeroInt) {
                isMessage = true;
                Message message = salons.get(salon).get(numeroInt);
                if (message != null) {
                    model.addObject("message", salons.get(salon).get(numeroInt));
                    model.addObject("user", user);
                    model.addObject("hasMessage", isMessage);
                }
            }
        } else {
            return new ModelAndView("interface_spring");
        }
        return model;
    }

    //@PostMapping(value = "/back-office/message/{salon}/{user}/delete", produces = {"application/json","application/xml","text/html"})
    @DeleteMapping(value = "/back-office/messages/{salon}/{user}/delete")
    public ModelAndView deleteLastMessage(@RequestParam(value = "salon", required = false) String salond,
                                          @PathVariable(value = "salon", required = true) String salon,
                                          @PathVariable(value = "user", required = true) String user,
                                          HttpSession session) throws Exception {
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");

        int numeroInt = Integer.valueOf(salons.get(salon).size()) - 1;
        if (numeroInt <= 0) {
            numeroInt = 0;
        }
        if (salons.get(salon) != null) {
            if (salons.get(salon).size() > numeroInt) {
                if(user.equals(salons.get(salon).get(numeroInt).getUser()))
                    salons.get(salon).remove(numeroInt);
            }
        }
        else {
            throw new SalonNotFoundException(salon);
        }
        ModelAndView model = new ModelAndView("messages_all");
        ArrayList<Message> messages = salons.get(salon);
        model.addObject("messagesList", messages);
        model.addObject("user", user);
        return model;
    }

    @PutMapping(value = "/back-office/messages/{salon}/{user}/modify")
    public ModelAndView updateLastMessage(@RequestParam(value = "message", required = false) String message,
                                          @PathVariable(value = "salon", required = true) String salon,
                                          @PathVariable(value = "user", required = true) String user,
                                          HttpSession session) throws Exception {
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");

        int numeroInt = Integer.valueOf(salons.get(salon).size()) - 1;
        if (numeroInt <= 0) {
            numeroInt = 0;
        }
        if (salons.get(salon) != null) {
            if (salons.get(salon).size() > numeroInt) {
                if(user.equals(salons.get(salon).get(numeroInt).getUser()))
                    salons.get(salon).get(numeroInt).setMessage(message);
            }
        }
        else {
            throw new SalonNotFoundException(salon);
        }
        ModelAndView model = new ModelAndView("messages_all");
        ArrayList<Message> messages = salons.get(salon);
        model.addObject("messagesList", messages);
        model.addObject("user", user);
        return model;
    }

    // Pour tester les exceptions, il faudrait comme paramètre un nombre supérieur au nombre de messages
    @GetMapping(value = "/back-office/messages/{salon}/{user}/get-from-{messageNbr}", produces = {"application/json","application/xml","text/html"})
    public ModelAndView receiveGetSalonMessageFromNumero(@PathVariable(value = "messageNbr", required = true) String messageNbr,
                                                         @PathVariable(value = "salon", required = true) String salon,
                                                         @PathVariable(value = "user", required = true) String user,
                                                         HttpSession session) throws Exception {
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        int numeroInt = Integer.valueOf(messageNbr) - 1;
        ModelAndView model = new ModelAndView("get_message_from");
        model.addObject("numero", messageNbr);
        ArrayList<Message> messagesTemp = new ArrayList<Message>();
        ArrayList<Message> messages = new ArrayList<Message>();
        if (salons.get(salon) != null) {
            messagesTemp = salons.get(salon);
            if (salons.get(salon).size() > numeroInt) {
                for (int i = numeroInt; i < salons.get(salon).size(); i++) {
                    Message message = messagesTemp.get(numeroInt);
                    messages.add(message);
                }
                model.addObject("messagesList", messages);
                model.addObject("user", user);
            }
            else {
                throw new MessageNotFoundException(numeroInt);
            }
        }
        else{
            throw new SalonNotFoundException(salon);
        }
        return model;
    }

    @GetMapping(value = "/back-office/messages/{salon}/{user}/message-number", produces = {"application/json", "application/xml", "text/html"})
    public ModelAndView receiveGetSalonMessageNumber(@PathVariable(value = "salon", required = true) String salon,
                                                     @PathVariable(value = "user", required = true) String user,
                                                     HttpSession session) {
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        ModelAndView model = new ModelAndView("get_message_from");
        ArrayList<Message> messagesTemp = new ArrayList<Message>();
        ArrayList<Message> messages = new ArrayList<Message>();
        if (salons.get(salon) != null) {
            messagesTemp = salons.get(salon);
            if (salons.get(salon).size() >= 0 ) {
                model.addObject("number", salons.get(salon).size());
            }
        }
        return model;
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ModelAndView handleMessageNotFoundException(HttpServletRequest request, Exception ex){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("error/genericView");
        return modelAndView;
    }


}
