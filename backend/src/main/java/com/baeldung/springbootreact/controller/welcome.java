package com.baeldung.springbootreact.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class welcome {

    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
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
                "<br> Go to postman and use below commands to retrieve data\n" +
                "<br> 1 /clients after current url to get all clients\n" +
                "<br> 2 /clients/2/tasks after current url to get all tasks\n" +
                "<br> 3 /clients/2/contacts after current url to get all contacts\n" +
                "</p>\n" +
                "</body>\n" +
                "</html>";


    }


}
