package com.Chat.Controlleur;

import com.Chat.Modele.GestionMessages;
import com.Chat.Modele.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Init")
public class Init extends HttpServlet {

    public void init() throws ServletException {
        super.init();
        if(getServletContext().getAttribute("salon") == null)
            getServletContext().setAttribute("salon", GestionMessages.salons);
        if(getServletContext().getAttribute("salonusers") == null)
            getServletContext().setAttribute("salonusers", GestionMessages.usersAllowedToChat);
        if(getServletContext().getAttribute("salons_per_user") == null)
            getServletContext().setAttribute("salons_per_user", GestionMessages.salonsPerUser);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, ArrayList<String>> salonusers = (HashMap<String, ArrayList<String>>) getServletContext().getAttribute("salonusers");
        HashMap<String, ArrayList<String>> salonsPerUser = (HashMap<String, ArrayList<String>>) getServletContext().getAttribute("salons_per_user");
        HttpSession session = request.getSession(true);
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());
        Integer visitCount = new Integer(0);
        String visitCountKey = new String("visitCount");
        String userID = request.getParameter("user_id");
        String salon = request.getParameter("salon");
        session.setAttribute("username", userID);
        session.setAttribute("salon", salon);
        if (salonusers != null) {
            if (salonusers.get(salon) == null) {
                session.setAttribute(visitCountKey, visitCount);
                salonusers.put(salon, new ArrayList<String>());
                salonusers.get(salon).add(userID);

                if(salonsPerUser.get(userID) == null)
                    salonsPerUser.put(userID, new ArrayList<String>());
                salonsPerUser.get(userID).add(salon);
                response.sendRedirect(request.getContextPath() + "/interface.html");
                return;
            } else if (salonusers.get(salon).contains(userID)) {
                response.sendRedirect(request.getContextPath() + "/interface.html");
                return;
            } else {
                doGet(request, response);
            }
        }
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.html").forward(request, response);
    }
}
