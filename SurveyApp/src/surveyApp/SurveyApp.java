package surveyApp;
import java.util.*;

public class SurveyApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Survey survey = new Survey("Customer Satisfaction ", "Customer Feedback", "A survey to understand customer experience and satisfaction.");


        survey.addQuestion("How satisfied are you with our customer service?");
        survey.addQuestion("How likely are you to recommend our company to others?");
        survey.addQuestion("How do you rate the quality of our products?");
        survey.addQuestion("How responsive have we been to your questions or concerns?");
        survey.addQuestion("Do you feel valued as a customer?");
        survey.addQuestion("How easy is it to navigate our website?");
        survey.addQuestion("Was the checkout process smooth and hassle-free?");
        survey.addQuestion("Would you like to see any improvements in our services?");
        survey.addQuestion("How often do you use our products or services?");
        survey.addQuestion("What is one thing we could do to improve your experience?");

        while (true) {
            System.out.println("\n1. Take Survey");
            System.out.println("2. View Survey Results");
            System.out.println("3. Find Most Given Answer");
            System.out.println("4. Find Answers by Candidate");
            System.out.println("5. Find Most Active Candidate");
            System.out.println("6. Add Question");
            System.out.println("7. Remove Question");
            System.out.println("8. Remove Questions Answered by Less than 50% of Candidates");
            System.out.println("9. Validate Survey");
            System.out.println("10. List Questions");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();

                    Candidate candidate = new Candidate(firstName, lastName, email, phone);
                    survey.takeSurvey(candidate, scanner);
                }
                case 2 -> survey.printSurveyResults();
                case 3 -> survey.findMostGivenAnswer();
                case 4 -> {
                    System.out.print("Enter candidate name: ");
                    String candidateName = scanner.nextLine();
                    survey.findCandidateAnswers(candidateName);
                }
                case 5 -> survey.findMostActiveCandidate();
                case 6 -> {
                    System.out.print("Enter new question: ");
                    String newQuestion = scanner.nextLine();
                    survey.addQuestion(newQuestion);
                }
                case 7 -> {
                    System.out.print("Enter question to remove: ");
                    int removeQuestion = scanner.nextInt();
                    survey.removeQuestion(removeQuestion);
                }
                case 8 -> survey.removeLowResponseQuestions();
                case 9 -> survey.validateSurvey();
                case 10 -> survey.listQuestions();
                case 11 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
