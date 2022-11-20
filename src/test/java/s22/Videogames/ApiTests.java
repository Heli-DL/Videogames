package s22.Videogames;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
class ApiTests {

	@Autowired
	private WebApplicationContext webAppContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	public void checkStatus() throws Exception {
		this.mockMvc.perform(get("/games")).andExpect(status().isOk());
		this.mockMvc.perform(get("/developers")).andExpect(status().isOk());
		this.mockMvc.perform(get("/genres")).andExpect(status().isOk());
		this.mockMvc.perform(get("/users")).andExpect(status().isOk());
	}
	
	@Test
	public void checkResponseTypeJSON() throws Exception {
		this.mockMvc.perform(get("/games")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		this.mockMvc.perform(get("/developers")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		this.mockMvc.perform(get("/genres")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		this.mockMvc.perform(get("/users")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void apiStatusIsOk() throws Exception {
		this.mockMvc.perform(get("/api/games")).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/developers")).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/genres")).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/appUsers")).andExpect(status().isOk());
	}
	
}
