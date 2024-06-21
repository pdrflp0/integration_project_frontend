package br.eletra.frontend.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import br.eletra.frontend.dto.LineDTO;
import org.glassfish.jersey.client.ClientConfig;

import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class LineServices {

    private static final String BASE_URL = "http://localhost:4455/api/lines";
    Client client = ClientBuilder.newClient(new ClientConfig());

    public List<LineDTO> getAllLines() {
        WebTarget myResource = this.client.target(BASE_URL);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        Gson gson = new Gson();
        Type lineListType = new TypeToken<List<LineDTO>>() {
        }.getType();
        List<LineDTO> list = gson.fromJson(response.readEntity(String.class), lineListType);
        return list;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}