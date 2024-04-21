
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Quize extends JFrame{
    private JLabel question;
    private JRadioButton[] questionoptions;
    private JButton submit;
    JLabel tlabel; // Add timer label
    private int QuestionIndex;
    private int scorequestion;
    Timer timer; 
    private String[] questions = {
            "who is president of india?",
            "What is chemical formula of water?",
            "What is4+4=?",
            "which planet is known as water planet?",
            "how many colors in rainbow"
          
            
    };
    private String[][] choices = {
            {"Droupadi Murmu", "Narendra modi", "Amit Shah", "Pandit Govind Ballabh Pant"},
            {"HCL", "H2O", "NACL", "O"},
            {"2", "5", "8", "10"},
            {"Earth", "Mercury", "Moon", "Venus"},
            {"2","3","6","7"}
    };
    private int[] correctAnswers = {1,2,3,1,4};
    public Quize() {
        setTitle("Quiz Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 300);
        setLayout(new BorderLayout());
        question = new JLabel();
        add(question, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        questionoptions = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            questionoptions[i] = new JRadioButton();
            questionoptions[i].setText(choices[QuestionIndex][i]);
            buttonGroup.add(questionoptions[i]);
            optionsPanel.add(questionoptions[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        submit = new JButton("Submit");
        add(submit, BorderLayout.SOUTH);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showNextQuestion();
            }
        });
        tlabel = new JLabel();
        add(tlabel, BorderLayout.EAST); // Add the timer label

        QuestionIndex = 0;
        showQuestion();
        startTimer();
    }

    private void showQuestion() {
        question.setText(questions[QuestionIndex]);
        for (int i = 0; i < 4; i++) {
           questionoptions[i].setText(choices[QuestionIndex][i]);
            questionoptions[i].setSelected(false);
        }
    }

    void showNextQuestion() {
        QuestionIndex++;
        if (QuestionIndex < questions.length) {
            showQuestion();
            resetTimer();
        } else {
            showResult();
        }
    }

    void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (questionoptions[i].isSelected() && i == correctAnswers[QuestionIndex] - 1) {
 
                scorequestion++;
            }
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Completed!\nScore: " + scorequestion + "/" + questions.length);
        System.exit(0);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() { // Timer ticks every 1 second
            private int secondsLeft = 15; // Initial timer value

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft >= 0) {
                    tlabel.setText("Time left: " + secondsLeft + " seconds");
                    secondsLeft--;
                } else {
                    timer.stop();
                    checkAnswer();
                    showNextQuestion();
                }
            }
        });
        timer.start();
    }

    private void resetTimer() {
        timer.stop();
        startTimer();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Quize().setVisible(true);
            }
        });
    }
}


