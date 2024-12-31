package com.zel92.desafio_uol;

import com.zel92.desafio_uol.enumeration.Group;
import com.zel92.desafio_uol.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UolPlayerRegistryIT {
	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("Should register and display all players successfully")
	void test1() throws Exception {
		var player = new Player("test", null, "test@gmail.com", Group.AVENGERS, "430" );

		mvc
				.perform(post("/register-player")
						.param("name", player.name())
						.param("email", player.email())
						.param("groupName", player.groupName().name())
						.param("telephone", player.telephone()))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/players-list"));

		mvc.perform(get("/players-list"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("players_list"))
				.andExpect(model().attribute("players", hasSize(1)))
				.andExpect(model().attribute("players", contains(allOf(
						hasToString(containsString(player.name())),
						hasToString(containsString(player.email())),
						hasToString(containsString(player.groupName().name())),
						hasToString(containsString(player.telephone()))
				))));
	}
}
