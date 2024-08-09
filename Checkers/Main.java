import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        //we start the game
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Frame().setVisible(true);
            }
            
        });

    }
}
