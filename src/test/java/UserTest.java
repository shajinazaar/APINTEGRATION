import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserTest extends BaseTest {

    @Test
    public void testGetUser() {
        given()
                .when()
                .get("/users/8")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", equalTo(8))
                .body("data.email", containsString("lindsay.ferguson"))
                .body("data.last_name", containsString("Ferguson"))
                .body("data.avatar",containsString("img/faces/8-image.jpg"));

       Response response = given()
               .when().get("/users/8")
                       .then().extract().response();

       String firstName = response.jsonPath().getString("data.first_name");
       assert firstName.contains("Lindsay");
    }
}
