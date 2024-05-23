package br.eletra.frontend.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import br.eletra.frontend.dto.CategoryDTO;
import br.eletra.frontend.dto.LineDTO;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;

public class CategoryServices {

    private static final String BASE_URL = "http://localhost:4455/api/categories";

    public static List<CategoryDTO> getAllCategories(LineDTO selectedLine) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget myResource = client.target(BASE_URL + "/" + selectedLine.getLineName());
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        Gson gsonCat = new Gson();
        Type categoryListType = new TypeToken<List<CategoryDTO>>() {}.getType();
        List<CategoryDTO> catList = gsonCat.fromJson(response.readEntity(String.class), categoryListType);
        return catList;
    }
}
