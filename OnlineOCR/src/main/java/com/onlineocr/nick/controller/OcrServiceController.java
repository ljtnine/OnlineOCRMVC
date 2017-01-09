package com.onlineocr.nick.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class OcrServiceController {
    private static final Logger logger = Logger.getLogger(OcrServiceController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        logger.debug("Index is executed!");
        return new ModelAndView("/resources/pages/index.html");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test() {
        return new ModelAndView("/resources/pages/test.html");
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadPDFResource( HttpServletRequest request,
                                     HttpServletResponse response)
    {
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
    public ModelAndView jspPage() {
        logger.debug("Profile is executed!");
        ModelAndView model = new ModelAndView("/WEB-INF/jsp/profile.jsp");
        return model;
    }

}
