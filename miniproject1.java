import java.util.*;
class QuizGame
{

    private static Map<String, Quiz> quizzes = new HashMap<>();
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
         System.out.println("select Level of Quiz-(basic,medium,advance)");
        while (true) 
	{
            String command1 = scanner.nextLine();
            if (command1.equals("basic")) 
	    {
               selectQuiz();
            } 
	    else if (command1.equals("medium")) 
	    {
               selectQuiz();
            } 
	    else if (command1.equals("advance")) 
	    {
                selectQuiz();
            } 
	    else 
	    {
                System.out.println("Invalid command.");
            }
            
        }
    }
    private static void selectQuiz()
    {
         Scanner scanner = new Scanner(System.in);
          
            while(true)
	    {
                System.out.println("Enter a command: (create, take, view, list, exit)");
                String command = scanner.nextLine();
            if (command.equals("create")) 
	    {
                createQuiz(scanner);
            } 
	    else if (command.equals("take")) 
	    {
                takeQuiz(scanner);
            } 
	    else if (command.equals("view")) 
	    {
                viewQuiz(scanner);
            } 
	    else if (command.equals("list")) 
	    {
                listQuizzes();
            } 
	    else if (command.equals("exit")) 
	    {
                break;
            } 
	    else 
	    {
                System.out.println("Invalid command.");
            }
            }
    }
    private static void createQuiz(Scanner scanner) 
    {
        System.out.println("Enter the name of the quiz:");
        String quizName = scanner.nextLine();
        Quiz quiz = new Quiz(quizName);
        System.out.println("Enter the number of questions:");
        int numQuestions = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numQuestions; i++) 
	{
            System.out.println("Enter the question:");
            String question = scanner.nextLine();
            System.out.println("Enter the number of choices:");
            int numChoices = Integer.parseInt(scanner.nextLine());
            List<String> choices = new ArrayList<>();
            for (int j = 0; j < numChoices; j++) 
	    {
                System.out.println("Enter choice " + (j+1) + ":");
                String choice = scanner.nextLine();
                choices.add(choice);
            }
            System.out.println("Enter the index of the correct choice:");
            int correctChoice = Integer.parseInt(scanner.nextLine()) - 1;
            quiz.addQuestion(new Question(question, choices, correctChoice));
        }
        quizzes.put(quizName, quiz);
        System.out.println("Quiz created.");
    }

    private static void takeQuiz(Scanner scanner) 
    {
        listQuizzes();
        System.out.println("Enter the name of the quiz:");
        String quizName = scanner.nextLine();
        Quiz quiz = quizzes.get(quizName);
        if (quiz == null) 
	{
            System.out.println("Quiz not found.");
            return;
        }
        int score = 0;
        int negativeScore=0;
        for (int i = 0; i < quiz.getNumQuestions(); i++) 
	{
            Question question = quiz.getQuestion(i);
            System.out.println("Question " + (i+1) + ": " + question.getQuestion());
            List<String> choices = question.getChoices();
            for (int j = 0; j < choices.size(); j++) 
	    {
                System.out.println((j+1) + ": " + choices.get(j));
            }
            System.out.println("Enter index of your answer:");
            int userAnswer = Integer.parseInt(scanner.nextLine()) - 1;
            if (userAnswer == question.getCorrectChoice()) 
	    {
                // System.out.println("Correct!");
                score++;
            }
            else if(userAnswer !=question.getCorrectChoice())
	    {
                // System.out.println("Wrong");
                negativeScore--;
            }
            else 
	    {
                System.out.println("Incorrect. The correct answer is " + (question.getCorrectChoice()+1) + ".");
            
            }
        }
        System.out.println("Your positive score is " + score + " out of " + quiz.getNumQuestions() + ".");
        System.out.println("Your negative score is " + negativeScore + " out of " + quiz.getNumQuestions() + ".");
    }

    private static void viewQuiz(Scanner scanner) 
    {
        listQuizzes();
        System.out.println("Enter the name of the quiz:");
        String quizName = scanner.nextLine();
        Quiz quiz = quizzes.get(quizName);
        if (quiz == null) 
	{
            System.out.println("Quiz not found.");
            return;
	}
                System.out.println("Quiz: " + quiz.getName());
    for (int i = 0; i < quiz.getNumQuestions(); i++) 
    {
        Question question = quiz.getQuestion(i);
        System.out.println("Question " + (i+1) + ": " + question.getQuestion());
        List<String> choices = question.getChoices();
        for (int j = 0; j < choices.size(); j++) 
	{
            System.out.println((j+1) + ": " + choices.get(j));
        }
        System.out.println("Answer: " + (question.getCorrectChoice()+1));
    }
   }

    private static void listQuizzes() 
    {
        System.out.println("Quizzes:");
        for (String quizName : quizzes.keySet()) 
	{
            System.out.println("- " + quizName);
        }
    }
    }

    class Quiz 
    {
        private String name;
    private List<Question> questions = new ArrayList<>();
    
    public Quiz(String name) 
    {
        this.name = name;
    }
    
    public String getName() 
    {
        return name;
    }
    
    public void addQuestion(Question question) 
    {
        questions.add(question);
    }
    
    public Question getQuestion(int index) 
    {
        return questions.get(index);
    }
    
    public int getNumQuestions() 
    {
        return questions.size();
    }
    }
    class Question 
    {
        private String question;
    private List<String> choices;
    private int correctChoice;
    
    public Question(String question, List<String> choices, int correctChoice) 
    {
        this.question = question;
        this.choices = choices;
        this.correctChoice = correctChoice;
    }
    
    public String getQuestion() 
    {
        return question;
    }
    
    public List<String> getChoices() 
    {
        return choices;
    }
    
    public int getCorrectChoice() 
    {
        return correctChoice;
    }
    
    }