import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/3.
 */
public class SnakePanel extends JPanel implements KeyListener,ActionListener{
    ImageIcon up   =new ImageIcon("up.png");
    ImageIcon down =new ImageIcon("down.png");
    ImageIcon left =new ImageIcon("left.png");
    ImageIcon right=new ImageIcon("right.png");
    ImageIcon body =new ImageIcon("body.png");
    ImageIcon title=new ImageIcon("title.jpg");
    ImageIcon food =new ImageIcon("food.png");

    int[] snakex=new int[750];
    int[] snakey=new int[750];
    Random rand=new Random();
    int foodx=rand.nextInt(34)*25+25;
    int foody=rand.nextInt(24)*25+75;
    int len=3;
    int score=0;
    String direction="R";
    boolean isStarted=false;
    boolean isFailed=false;
    Timer timer=new Timer(100,this);

    public SnakePanel(){
        this.setFocusable(true);
        this.addKeyListener(this);
        setup();
        timer.start();
    }

    public void paint(Graphics g){
        this.setBackground(Color.WHITE);
        title.paintIcon(this,g,25,11);
        g.fillRect(25,75,850,600);
        switch (direction){
            case "R":
                right.paintIcon(this,g,snakex[0],snakey[0]);
                break;
            case "L":
                left.paintIcon(this,g,snakex[0],snakey[0]);
                break;
            case "U":
                up.paintIcon(this,g,snakex[0],snakey[0]);
                break;
            case "D":
                down.paintIcon(this,g,snakex[0],snakey[0]);
                break;
        }
        for(int i=1;i<len;i++)
            body.paintIcon(this,g,snakex[i],snakey[i]);

        if(!isStarted){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD,30));
            g.drawString("Press Space to Pause/Restart",200,300);

        }
        if(isFailed){
            g.setColor(Color.ORANGE);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("Game Over",300,300);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,30));
        g.drawString("Score: "+score,700,50);
        food.paintIcon(this,g,foodx,foody);
    }
    public void setup(){
        isStarted=false;
        isFailed=false;
        len=3;
        score=0;
        direction="R";
        snakex[0]=100;
        snakey[0]=100;
        snakex[1]=75;
        snakey[1]=100;
        snakex[2]=50;
        snakey[2]=100;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode=e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_SPACE:
                if(isFailed){
                    setup();
                }
                else{
                    isStarted=!isStarted;
                }
                repaint();
                break;
            case KeyEvent.VK_UP:
                if(!direction.equals("D"))
                    direction="U";
                break;
            case KeyEvent.VK_DOWN:
                if(!direction.equals("U"))
                    direction="D";
                break;
            case KeyEvent.VK_RIGHT:
                if(!direction.equals("L"))
                    direction="R";
                break;
            case KeyEvent.VK_LEFT:
                if(!direction.equals("R"))
                    direction="L";
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(isStarted&&!isFailed){
            for(int i=len;i>0;i--){
                snakex[i]=snakex[i-1];
                snakey[i]=snakey[i-1];
            }
            switch(direction){
                case "R":
                    snakex[0]+=25;
                    if(snakex[0]>850)
                        snakex[0]=25;
                    break;
                case "L":
                    snakex[0]-=25;
                    if(snakex[0]<25)
                        snakex[0]=850;
                    break;
                case "U":
                    snakey[0]-=25;
                    if(snakey[0]<75)
                        snakey[0]=650;
                    break;
                case "D":
                    snakey[0]+=25;
                    if(snakey[0]>650)
                        snakey[0]=75;
                    break;
            }
            if(snakex[0]==foodx&&snakey[0]==foody){
                len++;
                score++;
                foodx=rand.nextInt(34)*25+25;
                foody=rand.nextInt(24)*25+75;
            }
            for(int i=1;i<len;i++){
                if(snakex[0]==snakex[i]&&snakey[0]==snakey[i])
                    isFailed=true;
            }
            repaint();
        }
    }
}

