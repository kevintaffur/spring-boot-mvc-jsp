package com.example.SpringBootMVCJSP.controllers;

import com.example.SpringBootMVCJSP.models.Alien;
import com.example.SpringBootMVCJSP.repositories.AlienRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

// Not a controller anymore. As I want to use AlienController to send data, not jsp
//@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private AlienRepository alienRepository;

    // @ModelAttribute methods are executed first (before the mapping methods)
    @ModelAttribute
    public void addNameToModel(Model model) {
        model.addAttribute("name", "Kevin");
    }

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("aliens")
    public String getAll(Model model) {
        model.addAttribute("aliens", alienRepository.findAll());
        return "aliens";
    }

    @GetMapping("aliens/{id}")
    public String getAlien(@PathVariable("id") int id, Model model) {
        model.addAttribute("alien", alienRepository.findById(id));
        return "show";
    }

    @GetMapping("aliens-by-name")
    public String getAlienByName(@PathParam("name") String name, Model model) {
        model.addAttribute("alien", alienRepository.find(name));
        return "show";
    }

//    @PostMapping("sum")
//    public String sum(HttpServletRequest request) {
//        // getParameter returns the value as String or null
//        // For HTTP servlets, parameters are contained in the query string or posted form data
//        // This is posted form data
//        int num1 = Integer.parseInt(request.getParameter("num1"));
//        int num2 = Integer.parseInt(request.getParameter("num2"));
//        int total = num1 + num2;
//
//        HttpSession session = request.getSession();
//        session.setAttribute("total", total);
//        return "show.jsp";
//    }

//    @PostMapping("sum")
//    public String sum(@RequestParam("num1") int num1, @RequestParam("num2") int num2, HttpSession session) {
//        // apparently @RequestParam also consider the post body fields
//        int total = num1 + num2;
//
//        session.setAttribute("total", total);
//        return "show.jsp";
//    }

    // Directory src/webapp is public. We can search for localhost:8080/index.jsp and it will show the file
//    @PostMapping("sum")
//    public ModelAndView sum(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
//        ModelAndView mv = new ModelAndView();
//        int total = num1 + num2;
//
//        mv.setViewName("result");
//        mv.addObject("total", total);
//        return mv;
//    }

    @PostMapping("sum") // Model could also be ModelMap, it depends on what we need (object or map)
    public String sum(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model) {
        int total = num1 + num2;
        model.addAttribute("total", total);
        return "result";
    }

//    @PostMapping("add")
//    public String add(@RequestParam("id") int id, @RequestParam("name") String name, Model model) {
//        Alien alien = new Alien();
//        alien.setId(id);
//        alien.setName(name);
//        model.addAttribute("alien", alien);
//        return "result";
//    }

    @PostMapping("aliens")
    // If @ModelAttribute("name") is set "name" is how it can be accessed in the view
    // If not, the attribute can be accessed with the class name
    public String createAlien(@ModelAttribute Alien alien) {
        // @ModelAttribute adds the attribute (alien) automatically to the model
        alienRepository.save(alien);
        return "show";
    }
}
