package com.zel92.desafio_uol.repository;

import com.zel92.desafio_uol.enumeration.Group;
import com.zel92.desafio_uol.model.Player;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerRepository {
    private final JdbcClient jdbcClient;

    public PlayerRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void save(Player player){
        jdbcClient.sql(
                """
                        INSERT INTO players (name, email, codename, telephone, group_name)
                        VALUES (:name, :email, :codename, :telephone, :group_name)
                        """)
                .param("name", player.name())
                .param("email", player.email())
                .param("codename", player.codename())
                .param("telephone", player.telephone())
                .param("group_name", player.groupName().name())
                .update();
    }

    public List<String> listCodenamesByGroup(Group groupName) {
      return jdbcClient
              .sql("SELECT distinct(codename) FROM players WHERE group_name=:group_name")
              .param("group_name", groupName.name())
              .query(String.class)
              .list();
    }

    public List<Player> listAllPlayers() {
        return jdbcClient
                .sql("SELECT * FROM players ORDER BY LOWER(name), id")
                .query(Player.class)
                .list();
    }
}
