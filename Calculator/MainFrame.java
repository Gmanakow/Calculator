import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(int width, int height){
        this.setSize(width, height);
        this.setTitle("Calculator");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
