import javax.swing.*;

/**
 * Created by Administrator on 2017/5/1.
 */
public class Snake {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel=new SnakePanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
