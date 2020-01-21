package com.roccorichie.scheduler.controllers;

import com.roccorichie.scheduler.models.Session;
import com.roccorichie.scheduler.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    // This will returns all the sessions when called
    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //maps to 201
    public Session create(@RequestBody final Session session) {
        // Save and flush to db
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Also need to check for children records before delete (Cascade)
        // TODO: Delete children records to be allowed
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        // because this is a PUT, we expect all attributes to be passed in.
        // A PATCH would only need what changes need to be updated.
        //TODO: Add validation that all attributes have been passed.
        // If not return a 400 bad payload
        Session existingSession = sessionRepository.getOne(id);
        // BeanUtils takes the existing session and copies the incoming session data onto it
        // Ignore the primary key and we don't want to replace it
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
