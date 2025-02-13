package surveyApp;
import java.util.*;

public class Candidate {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<String> answers;

    public Candidate(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.answers = new ArrayList<>();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void answerQuestion(Question question, int answerIndex) {
        String[] options = {"Agree", "Slightly Agree", "Slightly Disagree", "Disagree", "Skipped"};
        String answer = options[answerIndex];
        answers.add(answer);
        if (!answer.equals("Skipped")) {
            question.addResponse(answerIndex);
        }
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void printAnswers(List<Question> questions) {
        System.out.println("Answers by " + getFullName() + ":");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i).getText() + " -> " + answers.get(i));
        }
        System.out.println();
    }
}
