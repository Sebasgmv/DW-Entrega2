package com.prueba.transmi;

import com.prueba.transmi.model.Conductor;
import com.prueba.transmi.repository.ConductorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("systemtest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ConductorSystemTest {
    private String baseUrl;
    private ChromeDriver browser;
    private WebDriverWait wait;


    @Autowired
    ConductorRepository conductorRepository;

    @BeforeEach
    void init() {
        conductorRepository.save(new Conductor("Sebas", "1111", "300", "Calle 1"));
        conductorRepository.save(new Conductor("Bito", "2222", "400", "Calle 2"));

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
         options.addArguments("--headless"); // To hide Chrome window
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-extensions"); // disabling extensions
        // options.setExperimentalOption("useAutomationExtension", false);
        // options.addArguments("start-maximized"); // open Browser in maximized mode
        // options.addArguments("disable-infobars"); // disabling infobars
        // options.addArguments("--disable-dev-shm-usage"); // overcome limited resource
        // problems
//        options.merge(DesiredCapabilities);

        this.browser = new ChromeDriver(options);

//        this.wait = new WebDriverWait(browser, 5);

        this.baseUrl = "http://localhost:4200";
    }
}
