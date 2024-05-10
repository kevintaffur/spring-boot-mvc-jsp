package com.example.SpringBootMVCJSP.controllers;

import com.example.SpringBootMVCJSP.models.Alien;
import com.example.SpringBootMVCJSP.repositories.AlienRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Note that as the class is annotated with @RestController, @ResponseBody is not needed anymore
// @RestController for APIs (json or xml), @Controller for MVC
@RestController
@RequestMapping("/")
public class AlienController {
    @Autowired
    private AlienRepository alienRepository;
    // By default, SpringBoot responds with json, 'produces' allow us to specify the response
    // for xml extra dependency is needed
    // Order in produces={} is important if client doesn't specify first mentioned here is used
    @GetMapping(path="aliens", produces={"application/json", "application/xml"})
    // ResponseBody specifies that the method is sending data, so it ignores the properties configuration
    // like the suffix and prefix config, also send the data as a response body, not as a message.
    //@ResponseBody
    // Jackson convert java objects to json or xml in the response.
    public List<Alien> getAll() {
        return alienRepository.findAll();
    }

    @GetMapping("aliens/{id}")
    // Note that as the class is annotated with @RestController, @ResponseBody is not needed anymore
    //@ResponseBody
    public Optional<Alien> getAlien(@PathVariable("id") int id) {
        Optional<Alien> alien = alienRepository.findById(id);
        return alien;
    }

//    @PostMapping("aliens")
//    // If data is sent as params e.g. "localhost:8080/aliens?id=55&name=Kevin" I guess Jackson build the Alien object
//    // Doesn't happen if data is sent as body (json)
//    public Alien createAlien(Alien alien) {
//        alienRepository.save(alien);
//        return alien;
//    }

    // Same as produces={} but for incoming data
    // MediaType is used in this case
    @PostMapping(path="aliens", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    // This one works with request body (json)
    public Alien createAlien(@RequestBody Alien alien) {
        alienRepository.save(alien);
        return alien;
    }
}
