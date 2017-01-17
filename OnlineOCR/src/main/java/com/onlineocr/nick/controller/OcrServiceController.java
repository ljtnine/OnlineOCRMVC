package com.onlineocr.nick.controller;


import com.onlineocr.nick.DAO.UserDAO;
import com.onlineocr.nick.DAO.UserService;
import com.onlineocr.nick.model.actions.ServiceClass;
import com.onlineocr.nick.model.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by GrIfOn on 07.01.2017.
 */

@Controller
@SessionAttributes(types = User.class)
public class OcrServiceController {

    private static final Logger logger = Logger.getLogger(OcrServiceController.class);
    @Autowired
    private UserDAO userService;
    @Autowired
    private ServiceClass serviceClass;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        logger.info("Index page is opened");
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(User user) {
        logger.info("Registration page is opened");
        if(user.getId() == null) {
            logger.info("User is null");
            return "registration";
        }
        else {
            logger.info("User is already saved in session");
            return "redirect:/profile";
        }
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response) {
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/");
        Path file = Paths.get(dataDirectory, "ml.pdf");
        if (Files.exists(file))
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+"ml.pdf");
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile() {
        logger.info("Profile page is opened");
        return "profile";
    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public ModelAndView newUser(HttpServletRequest request) {
        logger.info("Creating a new user . . .");
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPasswordHash(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setLogin(request.getParameter("login"));
        user.setId(new Long(2));
        logger.info("User created");

        //logger.info("Sending email");
        //serviceClass.sendEmail(user);
        //serviceClass.encryptPassword(user);

        ModelAndView modelAndView = new ModelAndView("registered");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
