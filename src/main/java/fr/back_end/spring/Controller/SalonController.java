 package fr.back_end.spring.Controller;

import com.Chat.Modele.GestionMessages;
import com.Chat.Modele.Message;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SalonController {
    private Map<String, ArrayList<Message>> salons;
    private Map<String, ArrayList<String>> usersAllowedToChat;

    @GetMapping(value = "/back-office/salon/{salon}/{user}/message-number", produces = {"application/json","application/xml","text/html"})
    public ModelAndView receiveGetSalonPerUser(@PathVariable(value = "salon", required = true) String salon,
                                               @PathVariable(value = "user", required = true) String user,
                                               HttpSession session) throws Exception{
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        ModelAndView model = new ModelAndView("messages_number");
        if(salons.get(salon) == null){
            throw new SalonNotFoundException(salon);
        }
        else
            model.addObject("numberOfMessages", salons.get(salon).size());
        return model;
    }

    @DeleteMapping(value = "/back-office/salon/{salon}/{user}/delete", produces = {"application/json","application/xml","text/html"})
    public void deleteSalon(@RequestParam(value = "salon", required = false) String salonSession,
                            @PathVariable(value = "salon", required = true) String salon,
                            @PathVariable(value = "user", required = true) String user,
                            HttpSession session, HttpServletResponse response) throws Exception {
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        if (salon != null && !salon.equals("")) {
            salons.remove(salon);
            session.setAttribute("salon", null);
            response.sendRedirect("/");
        }
        else {
            throw new SalonNotFoundException(salon);
        }
    }

    @GetMapping(value = "/back-office/salon/{salon}/{user}", produces = {"application/json","application/xml","text/html"})
    public ModelAndView receiveGetSalon(@PathVariable(value = "salon", required = true) String salon,
                                        @PathVariable(value = "user", required = true) String user,
                                        HttpSession session) throws Exception{
        return new ModelAndView("interface_spring");
    }

    @GetMapping(value = "/back-office/salon/{salon}/{user}/affichage", produces = {"application/json","application/xml","text/html"})
    public ModelAndView receiveGetAffichageSalon(@PathVariable(value = "salon", required = true) String salon,
                                                 @PathVariable(value = "user", required = true) String user,
                                                 HttpSession session) throws Exception{
        ModelAndView model = new ModelAndView("affichage_spring");

        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        ArrayList<Message> messages = new ArrayList<Message>();
        if (salons.get(salon) != null) {
            messages = salons.get(salon);
        }
        else {
            throw new SalonNotFoundException(salon);
        }
        model.addObject("messagesList", messages);
        model.addObject("user", user);
        return model;
    }

    @PostMapping(value = "/back-office/salon/login", produces = {"application/json","application/xml","text/html"})
    public ModelAndView loginSalon(@RequestParam(value = "salon", required = false) String salon,
                                   @RequestParam(value = "user", required = false) String user,
                                   HttpSession session) throws Exception{
        ModelAndView model = new ModelAndView("ajax/interface_ajax.html");
        if(session.getServletContext().getAttribute("salon") == null)
            session.getServletContext().setAttribute("salon", GestionMessages.salons);
        if(session.getServletContext().getAttribute("salonusers") == null)
            session.getServletContext().setAttribute("salonusers", GestionMessages.usersAllowedToChat);
        if(session.getServletContext().getAttribute("salons_per_user") == null)
            session.getServletContext().setAttribute("salons_per_user", GestionMessages.salonsPerUser);

        if (GestionMessages.usersAllowedToChat.get(salon) == null) {
            session.setAttribute("username", user);
            session.setAttribute("salon", salon);
            GestionMessages.usersAllowedToChat.put(salon, new ArrayList<String>());
            GestionMessages.usersAllowedToChat.get(salon).add(user);

            GestionMessages.salonsPerUser.put(user, new ArrayList<String>());
            GestionMessages.salonsPerUser.get(user).add(salon);
        }
        else if(GestionMessages.salonsPerUser.get(user) != null){
            session.setAttribute("salon", salon);
        }
        else {
            throw new SalonNotFoundException(salon);
        }
        model.addObject("status", "success");
        return model;
    }

    @ExceptionHandler(SalonNotFoundException.class)
    public ModelAndView handleSalonNotFoundException(HttpServletRequest request, Exception ex){

        ModelAndView modelAndView = new ModelAndView("error/genericView");
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("exception", ex);

        return modelAndView;
    }
}
