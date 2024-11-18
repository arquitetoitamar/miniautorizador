package br.com.vr.beneficios.autorizador.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAuthenticatedRequestWithBasicAuth() throws Exception {
        // Configurando manualmente o header de autorização Basic Authentication
        String username = "testuser";
        String password = "testpassword";
        String basicAuthHeader = "Basic " + Base64Utils.encodeToString((username + ":" + password).getBytes());

        mockMvc.perform(get("/cartoes/1234")
                        .header("Authorization", basicAuthHeader))
                .andExpect(status().isNotFound());//permitiu a autenticacao mas nao encontrou o cartao
    }

    @Test
    void testUnauthenticatedRequest() throws Exception {
        mockMvc.perform(get("/cartoes"))
                .andExpect(status().isUnauthorized());
    }
}
