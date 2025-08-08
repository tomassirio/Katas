package com.tomassirio.practice.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserAnswersConverterTest {

    private UserAnswersConverter converter;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        converter = new UserAnswersConverter(objectMapper);
    }

    @Test
    void convertToDatabaseColumn_success() throws JsonProcessingException {
        Map<String, String> userAnswers = new HashMap<>();
        userAnswers.put("q1", "a1");
        userAnswers.put("q2", "a2");

        String jsonString = "{\"q1\":\"a1\",\"q2\":\"a2\"}";
        when(objectMapper.writeValueAsString(userAnswers)).thenReturn(jsonString);

        String result = converter.convertToDatabaseColumn(userAnswers);

        assertThat(result).isEqualTo(jsonString);
    }

    @Test
    void convertToDatabaseColumn_nullInput() {
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void convertToEntityAttribute_success() throws JsonProcessingException {
        String jsonString = "{\"q1\":\"a1\",\"q2\":\"a2\"}";
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("q1", "a1");
        expectedMap.put("q2", "a2");

        when(objectMapper.readValue(org.mockito.ArgumentMatchers.eq(jsonString), (TypeReference<Map<String, String>>) org.mockito.ArgumentMatchers.any(TypeReference.class))).thenReturn(expectedMap);

        Map<String, String> result = converter.convertToEntityAttribute(jsonString);

        assertThat(result).isEqualTo(expectedMap);
    }

    @Test
    void convertToEntityAttribute_nullInput() {
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void convertToDatabaseColumn_jsonProcessingException() throws JsonProcessingException {
        Map<String, String> userAnswers = new HashMap<>();
        userAnswers.put("q1", "a1");

        when(objectMapper.writeValueAsString(userAnswers)).thenThrow(new JsonProcessingException("Test Exception") {});

        assertThat(converter.convertToDatabaseColumn(userAnswers)).isNull();
    }

    @Test
    void convertToEntityAttribute_jsonProcessingException() throws JsonProcessingException {
        String jsonString = "invalid json";

        when(objectMapper.readValue(jsonString, Map.class)).thenThrow(new JsonProcessingException("Test Exception") {});

        assertThat(converter.convertToEntityAttribute(jsonString)).isNull();
    }
}
