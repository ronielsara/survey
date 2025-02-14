import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Survey {
    private String title;
    private String topic;
    private String description;
    private List<Question> questions;
    private List<Candidate> candidates;

    public Survey(String title, String topic, String description) {
        this.title = title;
        this.topic = topic;
        this.description = description;
        this.questions = new ArrayList<>();
        this.candidates = new ArrayList<>();
    }

    public void addQuestion(String questionText) {
        if (questions.size() >= 40) {
            System.out.println("Cannot add new question because survey cannot have more than 40 questions.");
            return;
        }

        for (Question q : questions) {
            if (q.getText().equalsIgnoreCase(questionText)) {
                System.out.println("Duplicate question not allowed.");
                return;
            }
        }

        questions.add(new Question(questionText));
    }

    public void removeQuestion(int questionIndex) {
        if (questionIndex < 1 || questionIndex > questions.size()) {
            System.out.println("Question not found. Please enter a valid number.");
            return;
        }
        if (questions.size() > 10 ) {
            Question removedQuestion = questions.remove(questionIndex - 1);
            System.out.println("Question removed successfully: " + "\"" + removedQuestion.getText() + "\"" );
        } else {
            System.out.println("Cannot remove question because survey must have at least 10 questions.");
        }

    }

    public void takeSurvey(Candidate candidate, Scanner scanner) {
        candidates.add(candidate);

        for (Question question : questions) {
            System.out.println("Q: " + question.getText());
            System.out.println("[1] Agree  [2] Slightly Agree  [3] Slightly Disagree  [4] Disagree  [5] Skip");

            int choice = getValidIntegerInput(scanner, "Your choice: ", 1, 5) - 1; // Ensures valid input

            candidate.answerQuestion(question, choice);
        }

    }

    public void printSurveyResults() {
        System.out.println("Survey: " + title + " - " + topic);
        for (Question question : questions) {
            question.printResults();
        }
    }

    public void findMostGivenAnswer() {
        int[] totalResponses = new int[4];
        for (Question question : questions) {
            int[] responses = question.getResponses();
            for (int i = 0; i < responses.length; i++) {
                totalResponses[i] += responses[i];
            }
        }

        String[] options = {"Agree", "Slightly Agree", "Slightly Disagree", "Disagree"};
        int maxIndex = 0;
        for (int i = 1; i < totalResponses.length; i++) {
            if (totalResponses[i] > totalResponses[maxIndex]) {
                maxIndex = i;
            }
        }

        System.out.println("Most given answer in the survey: " + options[maxIndex] + " (" + totalResponses[maxIndex] + " times)");
    }

    public void findCandidateAnswers(String candidateName) {
        for (Candidate candidate : candidates) {
            if (candidate.getFullName().equalsIgnoreCase(candidateName)) {
                candidate.printAnswers(questions);
                return;
            }
        }
        System.out.println("Candidate not found.");
    }

    public void findMostActiveCandidate() {
        if (candidates.isEmpty()) {
            System.out.println("No candidates have taken the survey yet.");
            return;
        }

        Candidate mostActive = candidates.get(0);
        for (Candidate candidate : candidates) {
            if (candidate.getAnswers().size() > mostActive.getAnswers().size()) {
                mostActive = candidate;
            }
        }

        System.out.println("Most active candidate: " + mostActive.getFullName() + " (Answered " + mostActive.getAnswers().size() + " questions)");
    }

    public void removeLowResponseQuestions() {
        int totalCandidates = candidates.size();
        List<Question> questionsToRemove = new ArrayList<>();

        for (Question q : questions) {
            int answeredCount = q.getTotalResponses();
            if (answeredCount < (totalCandidates / 2.0)) {
                questionsToRemove.add(q);
                System.out.println("Marked for removal due to low response: " + q.getText());
            }
        }

        if (questions.size() < 10 + questionsToRemove.size()) {
            questions.removeAll(questionsToRemove);
        } else {
            System.out.println("Cannot remove question because survey must have at least 10 questions.");
        }
    }

    public void validateSurvey() {
        if (questions.size() < 10) {
            System.out.println("Survey must have at least 10 questions.");
        } else if (questions.size() > 40) {
            System.out.println("Survey cannot have more than 40 questions.");
        } else {
            System.out.println("Survey is valid.");
        }
    }

    public void listQuestions() {
        if (questions.isEmpty()) {
            System.out.println("No questions available in the survey.");
        } else {
            System.out.println("Survey Questions:");
            for (int i = 0; i < questions.size(); i++) {
                System.out.println((i + 1) + ". " + questions.get(i).getText());
            }
        }
    }
    private static String getValidInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
        } while (StringUtils.isBlank(input)); // Using `StringUtils.isBlank()` for validation
        return input;
    }

    private static int getValidIntegerInput(Scanner scanner, String prompt, int min, int max) {
        String input;
        int number;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (StringUtils.isNumeric(input)) {
                number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                }
            }
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
        }
    }
    public int getQuestionsSize() {
        return questions.size();
    }

}