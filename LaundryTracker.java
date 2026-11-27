import javax.swing.*; 
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.io.*; 
import java.awt.BorderLayout;

public class LaundryTracker {
    public static void main(String[] args) {

        //creates the GUI for the laundry tracker
        SwingUtilities.invokeLater( () -> {
            //creates the question frame

           JFrame questionFrame = new JFrame("Laundry Traker"); 
            questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            questionFrame.setSize(400, 400);

            

            JLabel questionLabel = new JLabel("Did you do laundry today?"); 
            JPanel questionPanel = new JPanel();
            JButton yesButton = new JButton("Yes");
            JButton noButton = new JButton("No");

            questionPanel.add(questionLabel);
            questionPanel.add(yesButton);
            questionPanel.add(noButton);
            questionFrame.add(questionPanel);
            questionFrame.setVisible(true);  

            //Listens for an answer to if the user did laundry today
            yesButton.addActionListener(e -> {
              questionFrame.setVisible(false);
              setLaundryDate(); 
              // calculate();
              showStatisticsFrame();
            });
            
            noButton.addActionListener(e -> {
              questionFrame.setVisible(false);
              showStatisticsFrame();
            });
        });
    }

    

    //Runs once the user answered the question and shows the statistics frame
    private static void showStatisticsFrame() {
       JLabel daysSinceLastLaundryLabel = new JLabel("Days since last laundry: " + daysSince());
       JLabel perdictedLaundryDayLabel = new JLabel("Predicted next laundry day: " + predict()); 
       JFrame statisticsFrame = new JFrame("Laundry Statistics");
       statisticsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       statisticsFrame.setSize(400, 400);
       JPanel statisticsPanel = new JPanel();
       statisticsPanel.setLayout(new BorderLayout());
       statisticsPanel.add(daysSinceLastLaundryLabel, BorderLayout.NORTH);
       statisticsPanel.add(perdictedLaundryDayLabel, BorderLayout.CENTER);
       statisticsFrame.add(statisticsPanel); 
       statisticsFrame.setVisible(true);
    }

    public static LocalDate getLastLaundryDate() {

      try (BufferedReader br = new BufferedReader(new FileReader("Laundry.txt"))) {
          LocalDate line = LocalDate.parse(br.readLine()); 
          if (line != null) {
           LocalDate lastLaundryDate = line; 
            return lastLaundryDate;
          } else {
            return null;
          }
      }  catch (IOException e) { 
          return null;
      }
    } 

    public static void setLaundryDate() {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("Laundry.txt"))) {

          bw.write(String.valueOf(LocalDate.now())); 

      } catch (IOException e) {
      
      }
    }  

    public static String daysSince() { 
      if(getLastLaundryDate() != null) {
        return String.valueOf(getLastLaundryDate().until(LocalDate.now(), ChronoUnit.DAYS)); 

      } else {
        return "Can not calculate, please do laundry first";
      }
    }

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