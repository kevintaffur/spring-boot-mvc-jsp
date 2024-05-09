package com.example.SpringBootMVCJSP.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "index.jsp";
    }

    @PostMapping("sum")
    public String sum(HttpServletRequest request) {
        // getParameter returns the value as String or null
        // For HTTP servlets, parameters are contained in the query string or posted form data
        // This is posted form data
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        int total = num1 + num2;

        HttpSession session = request.getSession();
        session.setAttribute("total", total);
        return "result.jsp";
    }
}
