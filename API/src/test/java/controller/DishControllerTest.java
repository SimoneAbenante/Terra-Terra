package controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import main.API_Main;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = API_Main.class)
public class DishControllerTest {

	@Autowired
	private DishController dishController;

	@Test
	public void testGetAllDish() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(dishController).build();
		mockMvc.perform(get("/dishes/"))

				.andExpect(status().isOk())

				.andExpect(content().string(containsString("penne al sugo")));  
	}

}
