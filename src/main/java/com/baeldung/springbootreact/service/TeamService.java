package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.domain.Team;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {



    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TeamRepository teamRepository;

    // retrieves list of all teams
    public List<Team> getteams(long id) {


        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(!employeeOptional.isPresent()) {
            throw new IllegalStateException("employee absent");
        }

        return employeeOptional.get().getTeam();
    }

    //creating team data in database
    public ResponseEntity<Object> createteam(long id, Team teamvar) {

        //
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(!employeeOptional.isPresent()) {
            throw new IllegalStateException("employee absent");
        }

        Employee employeecurrent = employeeOptional.get();

        teamvar.setEmployee(employeecurrent);

        teamRepository.save(teamvar);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teamvar.getTeamId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
    //removing team entity data from database
    public void deleteteam(Long id1, Long id2) {


        teamRepository.deleteById(id2);


    }
    //updating team data in database
   public Team updateteam(Long id1, Long id2, Team team) {


       Optional<Employee> employeeOptional = employeeRepository.findById(id1);



       if(!employeeOptional.isPresent()) {
           throw new IllegalStateException("employee absent");
       }

       Employee currentEmployee = employeeOptional.get();



       Team currentteam=teamRepository.findById(id2).get();
       currentteam.setTeamName(team.getTeamName());
       currentteam.setProject(team.getProject());
       currentteam.setLocation(team.getLocation());
       currentteam.setEmployee(currentEmployee);

        return teamRepository.save(currentteam);

   }
    // retrieves team if exists
    public Optional<Team> getoneteam(Long id1, Long id2) {

        Optional<Employee> employeeOptional = employeeRepository.findById(id1);



        if(!employeeOptional.isPresent()) {
            throw new IllegalStateException("employee absent");
        }
        else
        {
            return teamRepository.findById(id2);
        }

    }
}
