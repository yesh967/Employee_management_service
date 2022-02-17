package com.baeldung.springbootreact.controller;



import com.baeldung.springbootreact.domain.Team;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TeamRepository;
import com.baeldung.springbootreact.service.TaskService;
import com.baeldung.springbootreact.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

//controller class for Endpoints of crud operations on team entity

@RestController
@RequestMapping("/employees")
public class TeamController {

    static Logger logger = Logger.getLogger(TeamController.class.getName());
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TeamRepository teamRepository;

    @Autowired
    public TeamService teamService;

    // teams



    @GetMapping("/{id}/teams")
    public List<Team> retrieveAllTeams(@PathVariable long id) {
        logger.info(" In retrieveAllTeams controller function ");
        try {
            return teamService.getteams(id);
        }
        catch(Exception e)
        {
            logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
        }
        return null;

    }

    @GetMapping("/{id1}/teams/{id2}")
    public Optional<Team> getEmployeeteam(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        logger.info(" In getEmployeeteam controller function ");
        try {
            return teamService.getoneteam(id1,id2);
        }
        catch(Exception e)
        {
            logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
        }
        return null;



    }

    @PostMapping("/{id}/teams")
    public ResponseEntity<Object> createTeam(@PathVariable long id, @RequestBody Team teamvar) {
        logger.info(" In createTeam controller function ");
        try {
            return teamService.createteam(id,teamvar);
        }
        catch(Exception e)
        {
            logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
        }
        return null;

    }

    @DeleteMapping("/{id1}/teams/{id2}")
    public ResponseEntity deleteEmployeeteam(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {

        logger.info(" In deleteEmployeeteam controller function ");
        try {
            teamService.deleteteam(id1,id2);
            return ResponseEntity.ok().build();
        }
        catch(Exception e)
        {
            logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
        }
        return null;

    }


    @PutMapping("/{id1}/teams/{id2}")
    public ResponseEntity updateEmployeeteams(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestBody Team team)
    {
        logger.info(" In updateEmployeeteams controller function ");
        try {
            return ResponseEntity.ok(teamService.updateteam(id1, id2, team));
        }
        catch(Exception e)
        {
            logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
        }
        return null;

    }


}

