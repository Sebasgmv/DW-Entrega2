package com.prueba.transmi;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.transmi.model.Conductor;
import com.prueba.transmi.repository.ConductorRepository;
import com.prueba.transmi.service.CoordiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.RequestEntity.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("integrationtest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConductorControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    ConductorRepository conductorRepository;

    Logger log = LoggerFactory.getLogger(getClass());

    @BeforeEach
    void init() {
        conductorRepository.save(new Conductor("Sebas", "1111", "300", "Calle 1"));
//        cuentaRepository.save(new Cuenta("cuenta3", "1000"));
    }

    @Autowired
    private TestRestTemplate rest;

    @Test
    void nombre() throws Exception {
        String nombre = rest.getForObject("http://localhost:" + port + "/conductor/view/1/nombre", String.class);
        System.out.println(nombre);
        assertEquals(new String("Sebas"), nombre);
    }

    @Test
    void recuperarConductor() throws Exception {
        String conductor = rest.getForObject("http://localhost:" + port + "/conductor/view/1/test", String.class);
        System.out.println("Recibido: " + conductor);
        Conductor conductor1 = new Conductor(1L,"Sebas", "1111", "300", "Calle 1");
        ObjectMapper mapper = new ObjectMapper();
        Conductor conductorrecibido = mapper.readValue(conductor, Conductor.class);
        System.out.println("Recibido a Conductor: " + conductorrecibido);
//        String jsonStr = mapper.writeValueAsString(conductor1);
//        System.out.println("Conductor 1: " + jsonStr);
        assertEquals(conductorrecibido, conductor1);
    }

    @Test
    void cambiarNombre() {
        String nombre = rest.postForObject("http://localhost:" + port + "/conductor/1/cambiarNombre",
                new String("Vale"), String.class);
        assertEquals(new String("Vale"), nombre);
    }

    @Test
    void crearConductor() {
        Conductor conductor = rest.postForObject("http://localhost:" + port + "/conductor/crear-conductor",
                new Conductor("Otis", "2222", "302", "Calle 2"), Conductor.class);
//        assertEquals(new String("Vale"), nombre);
        String nombre = rest.getForObject("http://localhost:" + port + "/conductor/view/2/nombre", String.class);
        System.out.println(nombre);
        assertEquals(new String("Otis"), nombre);
    }

    @Test
    void editarConductor() throws JsonProcessingException {
//        Crear
        Conductor conductor = rest.postForObject("http://localhost:" + port + "/conductor/crear-conductor",
                new Conductor("Otis", "2222", "302", "Calle 2"), Conductor.class);
        String conductorBD = rest.getForObject("http://localhost:" + port + "/conductor/view/2/test", String.class);
        System.out.println("Conductor Creado:" + conductor);
        ObjectMapper mapper = new ObjectMapper();
        Conductor conductorrecibido0 = mapper.readValue(conductorBD, Conductor.class);
        System.out.println("Recibido: Conductor Creado: " + conductorrecibido0);
        assertEquals(conductor, conductorrecibido0);
//        Recuperar
        String conductorr = rest.getForObject("http://localhost:" + port + "/conductor/view/2/test", String.class);
        System.out.println("Recibido: " + conductorr);
        Conductor conductor1 = new Conductor(2L,"Otis", "2222", "302", "Calle 2");
        Conductor conductorrecibido = mapper.readValue(conductorr, Conductor.class);
        System.out.println("Recibido a Conductor: " + conductorrecibido);
        assertEquals(conductorrecibido, conductor1);
//        Modificar
        conductorrecibido.setNombre("Carlos");
        conductorrecibido.setCedula("3333");
        conductorrecibido.setTelefono("303");
        conductorrecibido.setDireccion("Calle 3");
        rest.put("http://localhost:" + port + "/conductor/editar-conductor",
                conductorrecibido);
//        Recuperar Modicado
        String conductorr2 = rest.getForObject("http://localhost:" + port + "/conductor/view/2/test", String.class);
        System.out.println("Recibido Modificado: " + conductorr2);
        Conductor conductor2 = new Conductor(2L,"Carlos", "3333", "303", "Calle 3");
        Conductor conductorrecibido2 = mapper.readValue(conductorr2, Conductor.class);
        System.out.println("Recibido a Conductor Modificado: " + conductorrecibido2);
        assertEquals(conductorrecibido2, conductor2);
    }

    @Test
    void eliminar() {
        rest.delete("http://localhost:" + port + "/conductor/delete/1");
        String conductorr2 = rest.getForObject("http://localhost:" + port + "/conductor/view/1/test", String.class);
        System.out.println("Recibido Modificado: " + conductorr2);
    }

    @Autowired
    CoordiService coordiService;
    @Test
    public void delete() {
        String url = "http://localhost:" + port + "/conductor/delete/test/1";
        ResponseEntity<String> resp = rest.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        log.warn("Respuesta: " +(String) resp.getBody());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

}
