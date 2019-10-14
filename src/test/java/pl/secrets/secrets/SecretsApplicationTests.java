package pl.secrets.secrets;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecretsApplicationTests {

	@Test
	public void contextLoads() {
		log.info("");
	}

	@Test
	public void getSecretTest() {
		get("/secrets").then().statusCode(404);
	}
}