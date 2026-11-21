import javax.swing.*; 

public class LaundryTracker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater( () -> {
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

            yesButton.addActionListener(e -> {
              questionFrame.setVisible(false);
              showStatisticsFrame();
            });
            
            noButton.addActionListener(e -> {
              questionFrame.setVisible(false);
              showStatisticsFrame();

            });
        });
    }

    private static void showStatisticsFrame() {
       JLabel daysSinceLastLaundryLabel = new JLabel("Days since last laundry: ");
       JLabel perdictedLaundryDayLabel = new JLabel("Predicted next laundry day: "); 
       JFrame statisticsFrame = new JFrame("Laundry Statistics");
       statisticsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       statisticsFrame.setSize(400, 400);
       JPanel statisticsPanel = new JPanel();
       statisticsPanel.add(daysSinceLastLaundryLabel);
       statisticsPanel.add(perdictedLaundryDayLabel);
       statisticsFrame.add(statisticsPanel); 
       statisticsFrame.setVisible(true);
    }
}