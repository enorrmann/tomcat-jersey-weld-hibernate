package com.causy.persistence.api;

import com.causy.model.Employee;
import com.causy.model.Team;

import java.util.List;

public interface TeamDAO {
    Team create(Team newTeam);

    Team get(int teamId);

    Team update(Team team);

    void addMember(Team team, Employee employee);

    List<Team> list();

    long count();

    void delete(int existingTeam);
}
