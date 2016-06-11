package br.com.dedoduro;

import br.com.dedoduro.model.Denuncia;
import br.com.dedoduro.model.TipoDenuncia;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DedoDuroApplication.class)
@WebAppConfiguration
public class DedoDuroApplicationTests {

	@Autowired private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}


	@Test
	public void validRequestReturns200OK() throws Exception {
		Denuncia denuncia = new Denuncia();
		denuncia.setLatitude("1");
		denuncia.setLongitude("1");
		denuncia.setFoto("1");
		denuncia.setNumeroTelefone("1234567890");
		denuncia.setTipoDenuncia(TipoDenuncia.ESTACIONAMENTO);
		denuncia.setId("1");
		denuncia.setConcluida(true);
		denuncia.setObservacao("321");
		String jsonDenuncia = getJson(denuncia);

		this.mockMvc.perform(post("/denuncia")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonDenuncia))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(jsonDenuncia));
	}

	@Test
	public void invalidNameError() throws Exception {
		Denuncia denuncia = new Denuncia();
		String jsonDenuncia = getJson(denuncia);

		this.mockMvc.perform(post("/denuncia")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonDenuncia))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	public String getJson(Denuncia denuncia) {
		Gson gson = new Gson();
		return gson.toJson(denuncia);
	}

}
