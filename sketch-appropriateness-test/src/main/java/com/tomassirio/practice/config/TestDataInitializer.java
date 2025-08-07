package com.tomassirio.practice.config;

import com.tomassirio.practice.model.dto.AnswerOptionData;
import com.tomassirio.practice.model.testDefinitions.AnswerOption;
import com.tomassirio.practice.model.testDefinitions.QuestionDefinition;
import com.tomassirio.practice.model.testDefinitions.TestCategoryDefinition;
import com.tomassirio.practice.repository.AnswerOptionRepository;
import com.tomassirio.practice.repository.QuestionDefinitionRepository;
import com.tomassirio.practice.repository.TestCategoryDefinitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class TestDataInitializer {

    private final TestCategoryDefinitionRepository categoryRepository;
    private final QuestionDefinitionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            initializeETCCategory();
            initializeETPCategory();
        };
    }

    private void initializeETCCategory() {
        TestCategoryDefinition etcCategory = createCategory("ETC", "Education, Training, and Certifications");

        QuestionDefinition etcQuestion1 = createQuestion("What is the capital of France?", "option1", etcCategory);
        createAnswerOptions(etcQuestion1, List.of(
                new AnswerOptionData("option1", "Paris"),
                new AnswerOptionData("option2", "London"),
                new AnswerOptionData("option3", "Rome")
        ));

        QuestionDefinition etcQuestion2 = createQuestion("Which planet is known as the Red Planet?", "option3", etcCategory);
        createAnswerOptions(etcQuestion2, List.of(
                new AnswerOptionData("option1", "Earth"),
                new AnswerOptionData("option2", "Mars"),
                new AnswerOptionData("option3", "Jupiter")
        ));
    }

    private void initializeETPCategory() {
        TestCategoryDefinition etpCategory = createCategory("ETP", "Experience, Talent, and Performance");

        QuestionDefinition etpQuestion1 = createQuestion("What is the primary goal of Agile methodology?", "option2", etpCategory);
        createAnswerOptions(etpQuestion1, List.of(
                new AnswerOptionData("option1", "Maximize documentation"),
                new AnswerOptionData("option2", "Deliver working software frequently"),
                new AnswerOptionData("option3", "Avoid change")
        ));

        QuestionDefinition etpQuestion2 = createQuestion("Which of the following is a common performance metric?", "option1", etpCategory);
        createAnswerOptions(etpQuestion2, List.of(
                new AnswerOptionData("option1", "Throughput"),
                new AnswerOptionData("option2", "Code comments"),
                new AnswerOptionData("option3", "Meeting duration")
        ));
    }

    private TestCategoryDefinition createCategory(String code, String name) {
        TestCategoryDefinition category = new TestCategoryDefinition(code, name);
        categoryRepository.save(category);
        return category;
    }

    private QuestionDefinition createQuestion(String text, String correctAnswer, TestCategoryDefinition category) {
        QuestionDefinition question = new QuestionDefinition(text, correctAnswer, category);
        questionRepository.save(question);
        return question;
    }

    private void createAnswerOptions(QuestionDefinition question, List<AnswerOptionData> optionsData) {
        List<AnswerOption> answerOptions = optionsData.stream()
                .map(data -> new AnswerOption(data.optionCode(), data.optionText(), question))
                .toList();
        answerOptionRepository.saveAll(answerOptions);
    }
}
