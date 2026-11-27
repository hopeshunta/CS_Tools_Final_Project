import javax.swing.*; 
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.awt.Color;
import java.io.*; 
import java.awt.Component; 

public class LaundryTracker {
    public static void main(String[] args) {

        //creates the GUI for the laundry tracker so that the user can easily interact allowing them to get the data they need efficiently.
        SwingUtilities.invokeLater( () -> {
            //creates the question frame
           JFrame questionFrame = new JFrame("Laundry Traker"); 
            questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            questionFrame.setSize(400, 400);

            JLabel questionLabel = new JLabel("Did you do laundry today?"); 
            questionLabel.setFont(questionLabel.getFont().deriveFont(24f));
            questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPanel questionPanel = new JPanel();
            questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));

            JButton yesButton = new JButton("Yes");
            JButton noButton = new JButton("No");
            JPanel buttonPanel = new JPanel(); 

            JPanel setDayPanel = new JPanel(); 
            JPanel fieldsPanel = new JPanel();

            JLabel setDayLabel = new JLabel("Forgot to set your last laundry day?"); 
            setDayLabel.setFont(setDayLabel.getFont().deriveFont(24f));
            JTextField setYear = new JTextField(4);
            JLabel yearLabel = new  JLabel("Year: ");
            JTextField setMonth = new JTextField(2);
            JLabel monthLabel = new  JLabel("Month: ");
            JTextField setDay = new JTextField(2);
            JLabel dayLabel = new  JLabel("Day: "); 

            JButton setButton = new JButton("Set");
            JPanel setPanel = new JPanel();

            setPanel.add(setButton);

            setDayPanel.add(setDayLabel); 
            fieldsPanel.add(yearLabel);
            fieldsPanel.add(setYear);
            fieldsPanel.add(monthLabel);
            fieldsPanel.add(setMonth);
            fieldsPanel.add(dayLabel);
            fieldsPanel.add(setDay);
            
            questionPanel.add(questionLabel);
            buttonPanel.add(yesButton);
            buttonPanel.add(noButton); 

            questionPanel.add(buttonPanel); 
            questionPanel.add(setDayPanel); 
            questionPanel.add(fieldsPanel); 
            questionPanel.add(setPanel);       
            questionFrame.add(questionPanel);  
            questionFrame.setVisible(true);  

            //Listens for an answer to if the user did laundry today
            yesButton.addActionListener(e -> {
              questionFrame.setVisible(false);
              setLaundryDate(); 
              showStatisticsFrame();
            });
            
            noButton.addActionListener(e -> {
              questionFrame.setVisible(false);
              showStatisticsFrame();
            });
        });
    }

    
    //Runs once the user answered the question and shows the statistics frame 
    //Having a seperate screen for the statitists allows the user to feel less overwhelmed and allows for everything to be calculated and then shown.
    private static void showStatisticsFrame() {
    
      
      //Each field of information has a set size and the answer is in a different color to help draw the user's attention to it.
       JLabel lastLaundryDayLabel = new JLabel("Last day you did laundry: "); 
       lastLaundryDayLabel.setFont(lastLaundryDayLabel.getFont().deriveFont(24f));
       JLabel lastLaundryDayAnswer = new JLabel(getLastLaundryDate().toString());
       lastLaundryDayAnswer.setFont(lastLaundryDayAnswer.getFont().deriveFont(24f));
       lastLaundryDayAnswer.setForeground(Color.BLUE);

       JPanel lastDayPanel = new JPanel(); 
       lastDayPanel.setLayout(new BoxLayout(lastDayPanel, BoxLayout.Y_AXIS));
       lastDayPanel.add(lastLaundryDayLabel); 
       lastDayPanel.add(lastLaundryDayAnswer);

       JLabel daysSinceLastLaundryLabel = new JLabel("Days since last laundry: ");
       daysSinceLastLaundryLabel.setFont(daysSinceLastLaundryLabel.getFont().deriveFont(24f));
       JLabel daysSinceLastLaundryAnswer = new JLabel(daysSince());
       daysSinceLastLaundryAnswer.setFont(daysSinceLastLaundryAnswer.getFont().deriveFont(24f));
       daysSinceLastLaundryAnswer.setForeground(Color.BLUE);


       JPanel sinceLastPanel = new JPanel(); 
       sinceLastPanel.setLayout(new BoxLayout(sinceLastPanel, BoxLayout.Y_AXIS));
       sinceLastPanel.add(daysSinceLastLaundryLabel);
       sinceLastPanel.add(daysSinceLastLaundryAnswer); 

       JLabel predictedLaundryDayLabel = new JLabel("Predicted next laundry day: "); 
       predictedLaundryDayLabel.setFont(predictedLaundryDayLabel.getFont().deriveFont(24f));
       JLabel predictedLaundryDayAnswer = new JLabel(predict());
       predictedLaundryDayAnswer.setFont(predictedLaundryDayAnswer.getFont().deriveFont(24f));
       predictedLaundryDayAnswer.setForeground(Color.BLUE);
       JPanel predictionPanel = new JPanel(); 
       predictionPanel.setLayout(new BoxLayout(predictionPanel, BoxLayout.Y_AXIS));
       predictionPanel.add(predictedLaundryDayLabel);
       predictionPanel.add(predictedLaundryDayAnswer); 


       JPanel statisticsPanel = new JPanel();
       statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.Y_AXIS));
       statisticsPanel.add(lastDayPanel);
       statisticsPanel.add(sinceLastPanel);
       statisticsPanel.add(predictionPanel);
       

       JFrame statisticsFrame = new JFrame("Laundry Statistics");
       statisticsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       statisticsFrame.setSize(400, 400);
       statisticsFrame.add(statisticsPanel);
       statisticsFrame.setVisible(true);
    }

    //Reads the Laundry.txt and returns the last time laundry was done and null if there is no recorded laundry session.  
    //Having a method to do this allows the frame to be created without the code being too complicated to  easily read. 
    public static LocalDate getLastLaundryDate() {

      try (BufferedReader br = new BufferedReader(new FileReader("Laundry.txt"))) {
        try {
          LocalDate line = LocalDate.parse(br.readLine()); 
          if (line != null) {
           LocalDate lastLaundryDate = line; 
            return lastLaundryDate;
          } else {
            return null;
          }
        }  catch (NullPointerException e) {
          return null;
        }
      }  catch (IOException e) { 
          return null;
      }
    } 

    //sets the last laundry date so that the user can update the app when they have done laundry.
    public static void setLaundryDate() {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("Laundry.txt"))) {

          bw.write(String.valueOf(LocalDate.now())); 

      } catch (IOException e) {
      
      }
    }  

    //Calculates how many days it has been since the user did laundry so that they know how long it has been since they last did laundry at a quick glance.
    //This is in its own method to reduce the complexity of the staticts frame logic.
    public static String daysSince() { 
      if(getLastLaundryDate() != null) {
        return String.valueOf(getLastLaundryDate().until(LocalDate.now(), ChronoUnit.DAYS)); 
      } else {
        return "Can not calculate, please do laundry first";
      }
    }

    //Calculates when the user should do laundry next so that they can plan for it.
    public static String predict() {
      LocalDate lastDate = getLastLaundryDate(); 
      if(lastDate != null) {
        LocalDate nextDate = lastDate.plusDays(10); 
        return nextDate.toString();
      } else {
        return "Can not calculate, please do laundry first.";
      }
    } 
}