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

    public Player save(Player player){
        jdbcClient.sql(
                """
                        INSERT INTO players (name, email, codename, telephone, group_name)
                        VALUES (:name, :email, :codename, :telephone, :group_name)
                        """)
                .param("name", player.name())
                .param("email", player.email())
                .param("codename", player.codename())
                .param("telephone", player.telephone())
                .param("group_name", player.groupName())
                .update();
        return player;
    }

    public List<String> listCodenamesByGroup(Group groupName) {
      return jdbcClient
              .sql(
                """
                        SELECT distinct(codename) FROM players WHERE group_name=:group_name
                        """
        )
              .param("group_name", groupName.getValue())
              .query(String.class)
              .list();
    }
}
