package br.eletra.frontend.services;

import br.eletra.frontend.dto.CategoryDTO;
import br.eletra.frontend.dto.LineDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServicesTest {

    private CategoryServices service;

    @Mock
    private Client client;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;

    @BeforeEach
    public void setUp() {
        service = new CategoryServices();
        service.setClient(client);

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.get()).thenReturn(response);
    }

    @AfterEach
    public void tearDown() {
        service = null;
    }

    @Test
    public void testPullAllCategoriesAndConvertToString() {
        // Given
        String jsonResponse = "[{\"id\":2,\"categoryName\":\"Cronos L\",\"line\":\"Cronos\"},{\"id\":3,\"categoryName\":\"Cronos NG\",\"line\": \"Cronos\"},{\"id\": 4,\"categoryName\":\"Ares TB\",\"line\": \"Ares\"},{\"id\":5,\"categoryName\":\"Ares THS\",\"line\":\"Ares\"},{\"id\":1,\"categoryName\":\"Cronos Old\",\"line\": \"Cronos\"}]";
        LineDTO mockLine = new LineDTO((short) 1, "Ares");

        when(response.readEntity(String.class)).thenReturn(jsonResponse);

        // When
        List<CategoryDTO> result = service.getAllCategories(mockLine);

        // Then
        assertNotNull(result);
        assertEquals("Ares TB", result.get(0).getCategoryName());
        assertEquals("Ares THS", result.get(1).getCategoryName());

        verify(client).target("http://localhost:4455/api/categories");
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get();
    }
}
