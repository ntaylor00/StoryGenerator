package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class APIClientTest {

    @Test
    void genImage_emptyPrompt_returnsEmpty()
    {
        APIClient client = new APIClient();
        String result = client.genImage("", "fantasy", 100, 100);
        assertEquals("", result, "Empty prompt should return empty result");
    }

    @Test
    void genImage_nullPrompt_returnsEmpty()
    {
        APIClient client = new APIClient();
        String result = client.genImage(null, "fantasy", 100, 100);
        assertEquals("", result, "Null prompt should return empty result");
    }

    @Test
    void genImage_nullGenre_returnsEmpty()
    {
        APIClient client = new APIClient();
        String result = client.genImage("A dragon in a field", null, 200, 200);
        assertEquals("", result, "Null genre should return empty result");
    }

    @Test
    void genImage_tooLongPrompt_throwsRuntimeException()
    {
        APIClient client = new APIClient();
        String longPrompt = "a".repeat(4001); // > 4000 chars
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                client.genImage(longPrompt, "fantasy", 100, 100)
        );
        assertTrue(ex.getMessage().toLowerCase().contains("too many"), "Expected message about too many characters");
    }

    @Test
    void genImage_bothNull_returnsEmpty() {
        APIClient client = new APIClient();
        String result = client.genImage(null, null, 100, 100);
        assertEquals("", result, "Both prompt and genre null should return empty result");
    }

    @Test
    void genImage_nullPrompt_genreEmpty_returnsEmpty() {
        APIClient client = new APIClient();
        String result = client.genImage(null, "", 100, 100);
        assertEquals("", result, "Null prompt with empty genre should return empty result");
    }

    @Test
    void genImage_emptyPrompt_emptyGenre_returnsEmpty() {
        APIClient client = new APIClient();
        String result = client.genImage("", "", 100, 100);
        assertEquals("", result, "Empty prompt and empty genre should return empty result");
    }

    @Test
    void genImage_longPrompt_nullGenre_returnsEmpty() {
        APIClient client = new APIClient();
        String longPrompt = "a".repeat(5000); // > 4000 chars
        String result = client.genImage(longPrompt, null, 100, 100);
        assertEquals("", result, "Long prompt with null genre should return empty result and not throw");
    }

    @Test
    void genImage_promptLength4000_nullGenre_returnsEmpty() {
        APIClient client = new APIClient();
        String p4000 = "a".repeat(4000); // exactly 4000 chars
        String result = client.genImage(p4000, null, 100, 100);
        assertEquals("", result, "4000-char prompt with null genre should return empty result");
    }

    @Test
    void genImage_whitespacePrompt_nullGenre_returnsEmpty() {
        APIClient client = new APIClient();
        String result = client.genImage("   ", null, 100, 100);
        assertEquals("", result, "Whitespace prompt with null genre should return empty result");
    }
}
