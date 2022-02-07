package com.baeldung.springbootreact.controller;



import com.baeldung.springbootreact.domain.Team;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TeamRepository;
import com.baeldung.springbootreact.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//controller class for Endpoints of crud operations on team entity

@RestController
@RequestMapping("/employees")
public class TeamController {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TeamRepository teamRepository;

    @Autowired
    public TeamService teamService;

    // teams



    @GetMapping("/{id}/teams")
    public List<Team> retrieveAllEmployees(@PathVariable long id) {

        return teamService.getteams(id);
    }

    @GetMapping("/{id1}/teams/{id2}")
    public Optional<Team> getEmployeeteam(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        return teamService.getoneteam(id1,id2);
    }

    @PostMapping("/{id}/teams")
    public ResponseEntity<Object> createTask(@PathVariable long id, @RequestBody Team teamvar) {


        return teamService.createteam(id,teamvar);

    }

    @DeleteMapping("/{id1}/teams/{id2}")
    public ResponseEntity deleteEmployeeteam(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {

        teamService.deleteteam(id1,id2);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id1}/teams/{id2}")
    public ResponseEntity updateEmployeeteams(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestBody Team team)
    {

        Team currentteam=teamService.updateteam(id1, id2, team);
        return ResponseEntity.ok(currentteam);
    }


}

