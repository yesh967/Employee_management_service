package com.baeldung.springbootreact.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class welcome {

    @GetMapping(value = "/welcome", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String welcomeAsHTML() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<header><title>Employee-task-manager</title></header>\n" +
                "<body style=\"background-color: black;\">\n" +
                "<h1 style=\"color: green;\n" +
                "  font-family: verdana;\n" +
                "  font-size: 300%;text-align: center;\">Welcome</h1><p style=\"color: white;\n" +
                "  font-family: courier;\n" +
                "  font-size: 160%;text-align: center;\">\n" +
                "<br> This is landing page for Employee Task Management Project\n" +
                "<br> Go to postman to create new employees, their tasks and contact details or use below commands to retrieve data\n" +
                "<br> <a href=\"https://employee-task-management.herokuapp.com/swagger-ui.html\"> Endpoints for utilising Employee task management service </a>\n" +


                "</p>\n" +
                "</body>\n" +
                "</html>";


    }


}
