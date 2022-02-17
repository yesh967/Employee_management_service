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
import java.util.logging.Logger;

@Service
public class TeamService {

    static Logger logger = Logger.getLogger(TeamService.class.getName());

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TeamRepository teamRepository;

    // retrieves list of all teams
    public List<Team> getteams(long id) {
        logger.info(" In getAllEmployee function ");
            try {
                Optional<Employee> employeeOptional = employeeRepository.findById(id);

                if (!employeeOptional.isPresent()) {
                    throw new NullPointerException("employee absent");
                }
                else {

                    return employeeOptional.get().getTeam();
                }
            }
            catch (Exception e){
                logger.info("error while fetching Employees"+ e.getMessage());
        }
        return null;
    }

    //creating team data in database
    public ResponseEntity<Object> createteam(long id, Team teamvar) {
        logger.info(" In createteam function ");
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);

            if (!employeeOptional.isPresent()) {
                throw new NullPointerException("employee absent");
            }
            else {
                Employee employeecurrent = employeeOptional.get();

                teamvar.setEmployee(employeecurrent);

                teamRepository.save(teamvar);

                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teamvar.getTeamId())
                        .toUri();

                return ResponseEntity.created(location).build();
            }
        }
        catch (Exception e){
            logger.info("error while fetching Employees"+ e.getMessage());
        }
        return null;

    }
    //removing team entity data from database
    public void deleteteam(Long id1, Long id2) {
        logger.info(" In deleteteam function ");

            try {
                Optional<Employee> employeeOptional = employeeRepository.findById(id1);

                if (!employeeOptional.isPresent()) {
                    throw new NullPointerException("employee absent");
                }
                else {
                    teamRepository.deleteById(id2);
                }
            }
            catch (Exception e){
                logger.info("error while fetching Employees"+ e.getMessage());
            }

    }


    //updating team data in database
   public Team updateteam(Long id1, Long id2, Team team) {
       logger.info(" In updateteam function ");

       try {
           Optional<Employee> employeeOptional = employeeRepository.findById(id1);


           if (!employeeOptional.isPresent()) {
               throw new NullPointerException("employee absent");
           }
            else {
               Employee currentEmployee = employeeOptional.get();
               Optional<Team> teamOptional = teamRepository.findById(id2);

               if(teamOptional.isPresent()) {
                   Team currentteam=teamOptional.get();
                   currentteam.setTeamName(team.getTeamName());
                   currentteam.setProject(team.getProject());
                   currentteam.setLocation(team.getLocation());
                   currentteam.setEmployee(currentEmployee);

                   return teamRepository.save(currentteam);
               }
               else {
                   throw new NullPointerException("team absent");
               }
           }
       }
       catch (Exception e){
           logger.info("error while fetching Employees"+ e.getMessage());
       }
       return null;
   }


    // retrieves team if exists
    public Optional<Team> getoneteam(Long id1, Long id2) {
        logger.info(" In getoneteam function ");
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id1);
            if (!employeeOptional.isPresent()) {
                throw new IllegalStateException("employee absent");
            } else {
                Optional<Team> teamOptional = teamRepository.findById(id2);
                if(teamOptional.isPresent()) {
                    return teamRepository.findById(id2);
                }
                else
                    throw new NullPointerException("team not present");
            }
        }
        catch (Exception e){
            logger.info("error while fetching Employees"+ e.getMessage());
        }
        return null;
    }
}
