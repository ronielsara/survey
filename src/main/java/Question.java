import java.util.*;

public class Question {
    private String text;
    private int[] responses;

    public Question(String text) {
        this.text = text;
        this.responses = new int[4];
    }

    public String getText() {
        return text;
    }

    public void addResponse(int answerIndex) {
        if (answerIndex >= 0 && answerIndex < 4) {
            responses[answerIndex]++;
        }
    }

    public int[] getResponses() {
        return responses;
    }

    public int getTotalResponses() {
        int total = 0;
        for (int response : responses) {
            total += response;
        }
        return total;
    }


    public void printResults() {
        String[] options = {"Agree", "Slightly Agree", "Slightly Disagree", "Disagree"};
        System.out.println("Question: " + text);
        for (int i = 0; i < responses.length; i++) {
            System.out.println(options[i] + ": " + responses[i]);
        }
        System.out.println();
    }
}
