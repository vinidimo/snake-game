package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Snake implements ActionListener,  KeyListener {
	
	public static Snake snake;
	public JFrame jframe;
	public RenderPanel renderPanel;	
	public int ticks = 0, direcao = BAIXO, score, highscore, taillength=4;
	public Timer timer = new Timer(50, this);
	public ArrayList<Point> snakeParts = new ArrayList<Point>();	
	public static final int CIMA = 0, BAIXO = 1, ESQ = 2, DIR = 3, ESCALA = 30;	
	public Point head, cherry;
	public Random random;
	public Dimension dim;
	public boolean over = false, paused;
	
	public Snake() {
            dim = Toolkit.getDefaultToolkit().getScreenSize();	
            jframe = new JFrame("Snake");
            jframe.setVisible(true);
            jframe.setSize(815, 600);
            jframe.setResizable(false);
            jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
            jframe.add(renderPanel= new RenderPanel());
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.addKeyListener(this);
            startGame();
        }
	
	public void startGame(){
            over = false;
            paused = false;		
            score=0;
            taillength=4;
            direcao=DIR;		
            head = new Point(0,8);
            random = new Random();
            snakeParts.clear();
            cherry = new Point(random.nextInt(26), random.nextInt(17));	

            for(int i = 0; i < taillength; i++){
                snakeParts.add(new Point(head.x,head.y));
            }
            timer.start();
	}
	
    @Override
	public void actionPerformed(ActionEvent arg0) {
		renderPanel.repaint();
		ticks++;
		
				
		if(ticks % 2 == 0 && head !=null && !over && !paused){
                    snakeParts.add(new Point(head.x,head.y));
                    
                    if (direcao== CIMA){
                        if(head.y-1>=0 && noTailAt(head.x, head.y-1)) {
                            head = new Point(head.x, head.y - 1);
                        }
                        else {
                            over=true;
                        }
                    }
                    
                    if (direcao== BAIXO) {
                        if(head.y-1 < 17 && noTailAt(head.x, head.y+1)) {
                        head = new Point(head.x, head.y + 1);
                        }
                        else {
                            over=true;
                        }
                    }

                    if (direcao== ESQ) {
                        if(head.x-1>=0 && noTailAt(head.x-1, head.y))
                            head = new Point(head.x -1, head.y);
                        else
                            over=true;
                    }
                    
                    if (direcao== DIR) {
                        if(head.x-1 < 25 && noTailAt(head.x+1, head.y)) {
                            head = new Point(head.x + 1, head.y);
                        }
                        else {
                            over=true;
                        }
                    }

                    if (snakeParts.size() > taillength) {
                            snakeParts.remove(0);
                    }

                    if (cherry !=null){
                        if (head.equals(cherry)){
                            score+=10;
                        if(score>highscore){
                            highscore = score;
                            for(int cont = 1; cont < 99999; cont++){
                                //RenderPanel.corBody = RenderPanel.corBody+1; //mudar de cor com highscore
                            }
                        }
                            taillength++;
                            cherry.setLocation(random.nextInt(26), random.nextInt(17));
                        }					
                    }
                    
                }
				
        }	
	
	public boolean noTailAt(int x, int y) {
            for(Point point : snakeParts){
                if(point.equals(new Point(x, y)))
                    return false;
            }
            return true;
        }

	public static void main(String[] args) {		
            snake = new Snake();		
	}
        
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
                
		if ((i== KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direcao != DIR) {
			direcao= ESQ;
                }
		
                if((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direcao != ESQ) {
			direcao= DIR;
                }
		
                if((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direcao != BAIXO) {
			direcao= CIMA;
                }
		
                if((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direcao != CIMA) {
			direcao= BAIXO;
                }
		
                if(i== KeyEvent.VK_SPACE ) {
                    if(over) {
                        startGame();
                    }
                    else {
                        paused = !paused;
                    }
                }
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}