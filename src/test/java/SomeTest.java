import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class SomeTest {
    String url = "https://jsonplaceholder.typicode.com/users";

    public String getJson() {

        RestAssured.baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET);
        String responseBody = response.getBody().asString();

        return responseBody;
    }


    @Test()
    public void deserializeUserSimple() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        UserObject[] targetArray = gsonBuilder.create().fromJson(getJson(), UserObject[].class);
        for (int i = 0; i < targetArray.length; i++) {
            System.out.print(targetArray[i].name + "   email:");
            System.out.println(targetArray[i].email);
        }
    }
}

