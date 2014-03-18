package main;
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
 
class myFrame extends JFrame
{
    private Robot robot;
    public myFrame()
    {
        setTitle("iRobot"); // имя заголовка нашего окна
        JPanel panel = new JPanel(); // создаем контейнер
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screen=env.getDefaultScreenDevice(); // GraphicsDevice класс описывает графические устройства, которые могут быть доступны в определенной среде графики.
        try {
            robot=new Robot(screen);
        } catch (AWTException ex) { }
        JButton bt1 = new JButton("Start"); // создаем кнопку
        panel.add(bt1); // добавляем нашу кнопку на панель
        bt1.addActionListener(new ActionListener() { // добавляем обработчик на нашу кнопку
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<10; i++) // здесь и происходят все действие с нашим роботом
                {
                    robot.mouseMove(500, 500 + 15 * i); // двигаем мышку на заданную координату x,y
                    robot.mousePress(InputEvent.BUTTON1_MASK); // нажимаем левую кнопку мыши
                    robot.delay(300); // 300 миллисекундная пауза
                    robot.mouseRelease(InputEvent.BUTTON1_MASK); // отжимаем левую кнопку мыши
                    robot.delay(300);
                    robot.keyPress('O'); // нажимаем кнопу 'O' с клавиатуры
                    robot.keyRelease('O'); // отжимаем кнопу 'O' с клавиатуры
                    robot.delay(300);
                    robot.keyPress(KeyEvent.VK_DOWN); // нажимаем кнопу 'DOWN' с клавиатуры
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyPress(KeyEvent.VK_DOWN);
                }
            }
        });
        Container pane = getContentPane(); // создаем контейнер
        pane.add(panel); // добавляем нашу панель в контейнер
        pack(); // задает размер нашему фрейму исходя из размеров нашей кнопки
    }
}
 
public class MyRobot {
    public static void main(String[] args) {
        myFrame frame = new myFrame(); // создаем объект класса myFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // задаем действие обработчику на кнопку "крестик"
        frame.show(); // выводим на экран нашу форму
    }
}
