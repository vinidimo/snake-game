package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class RenderPanel extends JPanel{
	
    public static Color corGrade = new Color(00000); // cor da linha da grade
    public static Color corFundo = new Color(00000); //cor do fundo
    public static int corBody = 8485495; // cor do corpo da cobra
    public static int corHead = 11785429; // cor da cabeça da cobra

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(corFundo);
        g.fillRect(0, 0, 810, 700);

        for ( int gridv = 600; gridv <= 16000; gridv += 600 ){
            g.setColor(corGrade);
            g.drawLine(gridv/20, 700, gridv/20, 0);
        }

        for ( int gridh = 600; gridh <= 15000; gridh += 600 ){
            g.setColor(corGrade);
            g.drawLine(810, gridh/20, 0, gridh/20);
        }

        Snake snake = Snake.snake;

        g.setColor(new Color(corBody));
        for (Point point : snake.snakeParts){			
            g.fillRect(point.x * Snake.ESCALA, point.y * Snake.ESCALA, Snake.ESCALA, Snake.ESCALA);
        }
        g.setColor(new Color(corHead));
        g.fillRect(snake.head.x * Snake.ESCALA, snake.head.y * Snake.ESCALA, Snake.ESCALA, Snake.ESCALA);

        g.setColor(Color.RED);
        g.fillRect(snake.cherry.x * Snake.ESCALA, snake.cherry.y * Snake.ESCALA, Snake.ESCALA, Snake.ESCALA);

        g.setColor(Color.BLACK);		
        g.fillRect(275, 545,250, 35);

        g.setColor(corGrade);		
        g.fillRect(277, 550,245, 35);

        g.setColor(Color.WHITE);
        g.drawString("Pontuação: " + snake.score, 310, 565);

        g.setColor(Color.WHITE);
        g.drawString("Highscore: " + snake.highscore, 410, 565);
        //g.drawString("Highscore: " + corBody, 410, 565);

        if (snake.over == true){		
            g.setColor(Color.WHITE);		
            g.drawString("APERTE ESPAÇO PARA RECOMEÇAR" , 570/2, 550/2);
        }

        if (snake.paused==true){
            g.setColor(Color.WHITE);		
            g.drawString("PAUSA" , 770/2, 550/2);
        }
}
}