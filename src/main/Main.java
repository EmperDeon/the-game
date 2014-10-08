package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import mods.basemod.containers.ModsContainer;
import render.Render;
import utils.MTimer;
import utils.Options;

public class Main extends JFrame implements Runnable {

 public final static render.Render rend = new Render();
 public final static Main main = new Main();
 public final static Thread Tm = new Thread(main);
 public final static Thread Tr = new Thread(rend);

 public final static String mdir = "/usr/games/game/";

 public final static Logger LOG = new Logger();
 public final static Options OPTIONS = new Options(mdir + "options.db");
 public final static MTimer timer = new MTimer();
 public final static ModsContainer mods = new ModsContainer();

 public boolean running = true;

 private final JList coreMods = new JList();
 private final JComboBox jComboBox1 = new JComboBox();
 private final JLabel jLabel1 = new JLabel();
 private final JLabel jLabel2 = new JLabel();
 private final JPasswordField jPasswordField1 = new JPasswordField();
 private final JScrollPane jScrollPane1 = new JScrollPane();
 private final JScrollPane jScrollPane2 = new JScrollPane();
 private final JTextField jTextField1 = new JTextField();
 private final JList modsList = new JList();
 private final JButton start = new JButton();
 private final Pop cpop = new Pop(coreMods);
 private final Pop mpop = new Pop(modsList);
 public final Model cmod = new Model();
 public final Model mmod = new Model();

 public Main () {
  initComponents();

 }

 public void init () throws InterruptedException {
  try {
   for ( UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {
    if ( "Nimbus".equals(info.getName()) ) {
     UIManager.setLookAndFeel(info.getClassName());
     break;
    }
   }
  } catch ( ClassNotFoundException | InstantiationException |
            IllegalAccessException | UnsupportedLookAndFeelException ex ) {
  }
  main.setVisible(true);

  mods.load();

  //rend.initfinal();
 }

 public void destroy () {
  setVisible(true);

 }

 private void initComponents () {
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  addWindowListener(new WindowAdapter() {
   @Override
   public void windowClosing ( WindowEvent e ) {
    LOG.save();
    mods.fsave();
   }
  });

  setTitle("Launcher");
  this.setLocation(600 , 300);

  modsList.setModel(mmod);
  jScrollPane1.setViewportView(modsList);
  modsList.getAccessibleContext().setAccessibleName("");

  coreMods.setModel(cmod);
  jScrollPane2.setViewportView(coreMods);

  jComboBox1.setModel(
          new DefaultComboBoxModel(new String[]{"0.0.1"}));

  start.setText("Запуск ");
  start.addMouseListener(new java.awt.event.MouseAdapter() {
   public void mouseClicked ( java.awt.event.MouseEvent evt ) {
    startMouseClicked(evt);
   }
  });

  jLabel1.setText("Логин:");

  jTextField1.setText("test");

  jLabel2.setText("Пароль:");

  jPasswordField1.setText("test");

  GroupLayout layout = new GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(
          layout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jScrollPane1 ,
                                GroupLayout.PREFERRED_SIZE , 150 ,
                                GroupLayout.PREFERRED_SIZE)
                  .addGap(12 , 12 , 12)
                  .addComponent(jScrollPane2 ,
                                GroupLayout.PREFERRED_SIZE , 150 ,
                                GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(
                          LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(
                                  GroupLayout.Alignment.LEADING)
                          .addGroup(layout.createSequentialGroup()
                                  .addGroup(layout.createParallelGroup(
                                                  GroupLayout.Alignment.LEADING).
                                          addComponent(jLabel1)
                                          .addComponent(jLabel2))
                                  .addGap(0 , 0 , Short.MAX_VALUE))
                          .addGroup(layout.createSequentialGroup()
                                  .addGap(12 , 12 , 12)
                                  .addGroup(layout.createParallelGroup(
                                                  GroupLayout.Alignment.LEADING).
                                          addComponent(jTextField1)
                                          .addComponent(jPasswordField1)))
                          .addComponent(jComboBox1 , 0 , 117 , Short.MAX_VALUE)
                          .addComponent(start ,
                                        GroupLayout.DEFAULT_SIZE ,
                                        GroupLayout.DEFAULT_SIZE ,
                                        Short.MAX_VALUE))
                  .addContainerGap())
  );
  layout.setVerticalGroup(
          layout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(layout.createParallelGroup(
                                  GroupLayout.Alignment.LEADING ,
                                  false)
                          .addComponent(jScrollPane2 ,
                                        GroupLayout.PREFERRED_SIZE ,
                                        200 ,
                                        GroupLayout.PREFERRED_SIZE)
                          .addComponent(jScrollPane1 ,
                                        GroupLayout.PREFERRED_SIZE ,
                                        200 ,
                                        GroupLayout.PREFERRED_SIZE)
                          .addGroup(GroupLayout.Alignment.TRAILING ,
                                    layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(
                                            LayoutStyle.ComponentPlacement.RELATED).
                                    addComponent(jTextField1 ,
                                                 GroupLayout.PREFERRED_SIZE ,
                                                 GroupLayout.DEFAULT_SIZE ,
                                                 GroupLayout.PREFERRED_SIZE).
                                    addGap(18 , 18 , 18)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(
                                            LayoutStyle.ComponentPlacement.UNRELATED).
                                    addComponent(jPasswordField1 ,
                                                 GroupLayout.PREFERRED_SIZE ,
                                                 GroupLayout.DEFAULT_SIZE ,
                                                 GroupLayout.PREFERRED_SIZE).
                                    addPreferredGap(
                                            LayoutStyle.ComponentPlacement.RELATED ,
                                            GroupLayout.DEFAULT_SIZE ,
                                            Short.MAX_VALUE)
                                    .addComponent(jComboBox1 ,
                                                  GroupLayout.PREFERRED_SIZE ,
                                                  GroupLayout.DEFAULT_SIZE ,
                                                  GroupLayout.PREFERRED_SIZE).
                                    addPreferredGap(
                                            LayoutStyle.ComponentPlacement.RELATED).
                                    addComponent(start)))
                  .addContainerGap(GroupLayout.DEFAULT_SIZE ,
                                   Short.MAX_VALUE))
  );

  pack();
  this.modsList.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent e ) {
    if ( e.getButton() == MouseEvent.BUTTON3 ) {
     mpop.show(( JList ) e.getSource() , e.getX() , e.getY());
    }
   }
  });
  this.coreMods.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent e ) {
    if ( e.getButton() == MouseEvent.BUTTON3 ) {
     cpop.show(( JList ) e.getSource() , e.getX() , e.getY());
    }
   }
  });
 }

 private void startMouseClicked ( java.awt.event.MouseEvent evt ) {
  Tr.start();
  setVisible(false);
 }

 public static void main ( String args[] ) {
  try {
   for ( UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {
    if ( "Nimbus".equals(info.getName()) ) {
     UIManager.setLookAndFeel(info.getClassName());
     break;
    }
   }
  } catch ( ClassNotFoundException | InstantiationException |
            IllegalAccessException | UnsupportedLookAndFeelException ex ) {
  }

  java.awt.EventQueue.invokeLater(() -> {

  });

  Tm.start();

 }

 @Override
 public void run () {
  try {
   init();

  } catch ( InterruptedException ex ) {

  }
 }

 private class Pop extends JPopupMenu {

  private final JMenuItem enab;
  private final JMenuItem disb;
  private final JMenuItem delt;

  public Pop ( JList l ) {

   enab = new JMenuItem("Включить выбранный мод");
   enab.addActionListener(e -> {
    System.out.println(l.getSelectedIndex());
   });

   disb = new JMenuItem("Выключить выбранный мод");
   disb.addActionListener(e -> {

   });

   delt = new JMenuItem("Удалить выбранный мод");
   delt.addActionListener(e -> {

   });

   add(enab);
   add(disb);
   add(delt);
  }
 }

 public class Model extends AbstractListModel {

  private final ArrayList<Object> cont = new ArrayList<>();

  @Override
  public int getSize () {
   return cont.size();
  }

  @Override
  public Object getElementAt ( int i ) {
   return cont.get(i);
  }

  public void add ( Object obj ) {
   cont.add(obj);
  }
 }
}
