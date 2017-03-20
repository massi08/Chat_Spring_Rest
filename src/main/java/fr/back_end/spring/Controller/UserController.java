package fr.back_end.spring.Controller;

import com.Chat.Modele.Message;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private Map<String, ArrayList<Message>> salons;
    private Map<String, ArrayList<String>> usersAllowedToChat;
    private Map<String, ArrayList<String>> salonsPerUser;

    @PostMapping(value = "/back-office/users/add/{salon}/{user}", produces = {"application/json","application/xml", "text/html"})
    public ModelAndView receivePostSalon(@RequestParam(value = "user", required = true) String user,
                                         @PathVariable(value = "salon", required = true) String salon,
                                         @PathVariable(value = "user", required = true) String userVariable,
                                         HttpSession session) throws Exception{
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        usersAllowedToChat = (HashMap<String, ArrayList<String>>) session.getServletContext().getAttribute("salonusers");
        salonsPerUser = (HashMap<String, ArrayList<String>>) session.getServletContext().getAttribute("salons_per_user");
        ModelAndView model = new ModelAndView("users");
        if(salonsPerUser.get(userVariable) == null){
            throw new UserNotFoundException(user);
        }
        boolean hasUser = false;
        if (!usersAllowedToChat.get(salon).contains(user)) {
            usersAllowedToChat.get(salon).add(user);
        } else
            hasUser = true;
        if (salonsPerUser.get(user) == null) {
            salonsPerUser.put(user, new ArrayList<String>());
            salonsPerUser.get(user).add(salon);
        }
        else if (!salonsPerUser.get(user).contains(salon))
            salonsPerUser.get(user).add(salon);
        model.addObject("addedUser", user);
        model.addObject("usersList", usersAllowedToChat.get(salon));
        model.addObject("isUserListAlreadyPresent", hasUser);
        return model;
    }

    //@PostMapping(value = "/back-office/users/modify", produces = {"application/json","application/xml","text/html"})
    @PutMapping(value = "/back-office/users/modify/{salon}/{user}", produces = {"application/json","application/xml","text/html"})
    public ModelAndView receivePostModifyUsername(@RequestParam(value = "new_username", required = true) String user,
                                                  @PathVariable(value = "salon", required = true) String salon,
                                                  @PathVariable(value = "user", required = true) String oldUser,
                                                  HttpSession session) throws Exception{
        salons = (HashMap<String, ArrayList<Message>>) session.getServletContext().getAttribute("salon");
        usersAllowedToChat = (HashMap<String, ArrayList<String>>) session.getServletContext().getAttribute("salonusers");
        salonsPerUser = (HashMap<String, ArrayList<String>>) session.getServletContext().getAttribute("salons_per_user");
        if(salonsPerUser.get(oldUser) == null){
            throw new UserNotFoundException(user);
        }
        salonsPerUser.put(user, salonsPerUser.get(oldUser));
        salonsPerUser.remove(oldUser);
        //Remplace dans tous les salons
        for (ArrayList<String> usersAllowed : usersAllowedToChat.values()) {
            if (usersAllowed.contains(oldUser)) {
                usersAllowed.add(user);
                usersAllowed.remove(oldUser);
            }
        }

        //Remplace dans tous les messages
        for (ArrayList<Message> messages : salons.values()) {
            for (Message message : messages) {
                if (message.getUser() == oldUser)
                    message.setUser(user);
            }
        }

        ModelAndView model = new ModelAndView("modify_username");
        session.setAttribute("username", user);
        model.addObject("oldPseudo", oldUser);
        model.addObject("newPseudo", user);
        return model;
    }

    @GetMapping(value = "/back-office/users/salons-list/{salon}/{user}", produces = {"application/json","application/xml","text/html"})
    public ModelAndView receiveGetSalonPerUser(@PathVariable(value = "salon", required = true) String salon,
                                               @PathVariable(value = "user", required = true) String user,
                                               HttpSession session) throws Exception{
        salonsPerUser = (HashMap<String, ArrayList<String>>) session.getServletContext().getAttribute("salons_per_user");

        if(salonsPerUser == null){
            throw new SalonNotFoundException(salon);
        }

        if(salonsPerUser.get(user) == null){
            throw new UserNotFoundException(user);
        }

        ModelAndView model = new ModelAndView("salons_per_user");
        model.addObject("salonPerUser", salonsPerUser.get(user));
        return model;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleMessageNotFoundException(HttpServletRequest request, Exception ex){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("exception", ex);

        modelAndView.setViewName("error/genericView");
        return modelAndView;
    }
}
