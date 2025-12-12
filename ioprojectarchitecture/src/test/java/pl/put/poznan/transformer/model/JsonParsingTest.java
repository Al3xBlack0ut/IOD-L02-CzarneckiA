package pl.put.poznan.transformer.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy do sprawdzenia czy jsony się dobrze parsują
 */
class JsonParsingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testTransformRequestParsing() throws Exception {
        // JSON który przychodzi z requesta
        String json = "{\"text\":\"test tekstu\",\"transforms\":[\"upper\",\"lower\",\"capitalize\"]}";
        
        // Parsujemy jsona na obiekt
        TransformRequest request = objectMapper.readValue(json, TransformRequest.class);
        
        // Sprawdzamy czy dobrze sparsowało
        assertNotNull(request);
        assertEquals("test tekstu", request.getText());
        assertNotNull(request.getTransforms());
        assertEquals(3, request.getTransforms().size());
        assertEquals("upper", request.getTransforms().get(0));
        assertEquals("lower", request.getTransforms().get(1));
        assertEquals("capitalize", request.getTransforms().get(2));
    }

    @Test
    void testTransformRequestSerialization() throws Exception {
        // Tworzymy obiekt
        List<String> transforms = Arrays.asList("upper", "escape");
        TransformRequest request = new TransformRequest("jakiś text", transforms);
        
        // Zamieniamy obiekt na jsona
        String json = objectMapper.writeValueAsString(request);
        
        // Sprawdzamy czy json się zgadza
        assertNotNull(json);
        assertTrue(json.contains("\"text\":\"jakiś text\""));
        assertTrue(json.contains("\"transforms\":[\"upper\",\"escape\"]"));
    }

    @Test
    void testTransformResponseParsing() throws Exception {
        // JSON z odpowiedzią
        String json = "{\"transformedText\":\"PRZEKSZTAŁCONY TEKST\"}";
        
        // Parsujemy
        TransformResponse response = objectMapper.readValue(json, TransformResponse.class);
        
        // Sprawdzamy
        assertNotNull(response);
        assertEquals("PRZEKSZTAŁCONY TEKST", response.getTransformedText());
    }

    @Test
    void testTransformResponseSerialization() throws Exception {
        // Tworzymy odpowiedź
        TransformResponse response = new TransformResponse("wynik transformacji");
        
        // Zamieniamy na jsona
        String json = objectMapper.writeValueAsString(response);
        
        // Sprawdzamy
        assertNotNull(json);
        assertTrue(json.contains("\"transformedText\":\"wynik transformacji\""));
    }

    @Test
    void testEmptyTransformsList() throws Exception {
        // Test z pustą listą transformacji
        String json = "{\"text\":\"test\",\"transforms\":[]}";
        
        TransformRequest request = objectMapper.readValue(json, TransformRequest.class);
        
        assertNotNull(request);
        assertEquals("test", request.getText());
        assertNotNull(request.getTransforms());
        assertEquals(0, request.getTransforms().size());
    }

    @Test
    void testNullHandling() throws Exception {
        // Test gdy brakuje pól w jsonie
        String json = "{}";
        
        TransformRequest request = objectMapper.readValue(json, TransformRequest.class);
        
        assertNotNull(request);
        assertNull(request.getText());
        assertNull(request.getTransforms());
    }
}
