package uoc.edu.raulsuarez.devsecop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DevSecOpApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void bodyTest() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).contains("POC");
    }



}
