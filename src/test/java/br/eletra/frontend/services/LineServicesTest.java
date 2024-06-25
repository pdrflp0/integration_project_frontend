package br.eletra.frontend.services;

import br.eletra.frontend.dto.LineDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LineServicesTest {

    private LineServices service;
    private Client client;
    private WebTarget webTarget;
    private Invocation.Builder builder;
    private Response response;

    @BeforeEach
    public void setUp() {
        client = mock(Client.class);
        webTarget = mock(WebTarget.class);
        builder = mock(Invocation.Builder.class);
        response = mock(Response.class);

        service = new LineServices();
        service.setClient(client);

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.get()).thenReturn(response);
    }

    @AfterEach
    public void tearDown() {
        service = null;
        client = null;
        webTarget = null;
        builder = null;
        response = null;
    }

    @Test
    public void testPullAllLinesAndConvertToString() {
        // Given
        String jsonResponse = "[{\"id\":1,\"name\":\"Cronos\"}, {\"id\":2,\"name\":\"Ares\"}]";
        Gson mockGson = new Gson();
        when(response.readEntity(String.class)).thenReturn(jsonResponse);
        Type mockLineListType = new TypeToken<List<LineDTO>>() {}.getType();
        List<LineDTO> mockList = mockGson.fromJson(response.readEntity(String.class), mockLineListType);

        // When
        List<LineDTO> result = service.getAllLines();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(result.toString(), mockList.toString());
        verify(client).target(eq("http://localhost:4455/api/lines"));
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get();
    }

}
