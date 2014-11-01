package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import mods.basemod.containers.ModsContainer;
import render.Render;
import utils.Logger;
import utils.MActionListener;
import utils.Options;

public final class Main extends JFrame {

 public final static render.Render rend = new Render();
 public static Main main;
 public static Thread Tm;
 public final static Thread Tr = new Thread(rend);

 public final static String mdir = "/usr/games/game/";

 public final static Logger LOG = new Logger();
 public final static Options OPTIONS = new Options(mdir + "options.db");
 public final static MActionListener TIMER = new MActionListener();
 public final static ModsContainer mods = new ModsContainer();

 public boolean running = true;
 
 public Main () {
  main = this;
  java.awt.EventQueue.invokeLater(() -> {
   setVisible(true);
   init();
  });

  initComponents();

  Tm = Thread.currentThread();
 }

 public static void init () {
  main.setVisible(true);

  mods.load();

  //rend.initfinal();
 }

 public void destroy () {
  setVisible(true);
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
  main = new Main();
 }

 public void initComponents () {
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

  coreList.setModel(cmod);
  jScrollPane2.setViewportView(coreList);

  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

  jScrollPane1.setViewportView(modsList);

  jScrollPane2.setViewportView(coreList);

  start.setText("Start");
  start.addMouseListener(new java.awt.event.MouseAdapter() {
   public void mouseClicked ( java.awt.event.MouseEvent evt ) {
    Tr.start();
    setVisible(false);
   }
  });

  options.setActionCommand("Options");
  options.setText("Options");
  options.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {

   }
  });

  modelEdit.setText("Models");
  modelEdit.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {

   }
  });

  modsEdit.setText("Mods");
  modsEdit.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {

   }
  });

  logEdit.setText("Logs");
  logEdit.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    new LogManager().setVisible(true);
   }
  });

  levelEdit.setText("Level");
  levelEdit.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {

   }
  });

  loginLabel.setText("Логин:");

  loginField.setText("test");

  passField.setText("test");

  passLabel.setText("Пароль:");

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jScrollPane1 ,
                                javax.swing.GroupLayout.PREFERRED_SIZE , 150 ,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(
                          javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(jScrollPane2 ,
                                javax.swing.GroupLayout.PREFERRED_SIZE , 150 ,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(
                          javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(
                                  javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING ,
                                    layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(
                                                    javax.swing.GroupLayout.Alignment.LEADING).
                                            addComponent(loginField ,
                                                         javax.swing.GroupLayout.PREFERRED_SIZE ,
                                                         102 ,
                                                         javax.swing.GroupLayout.PREFERRED_SIZE).
                                            addComponent(loginLabel))
                                    .addPreferredGap(
                                            javax.swing.LayoutStyle.ComponentPlacement.RELATED).
                                    addGroup(layout.createParallelGroup(
                                                    javax.swing.GroupLayout.Alignment.LEADING).
                                            addGroup(layout.
                                                    createSequentialGroup()
                                                    .addComponent(passLabel)
                                                    .addGap(0 , 0 ,
                                                            Short.MAX_VALUE))
                                            .addComponent(passField)))
                          .addGroup(layout.createSequentialGroup()
                                  .addComponent(start ,
                                                javax.swing.GroupLayout.PREFERRED_SIZE ,
                                                100 ,
                                                javax.swing.GroupLayout.PREFERRED_SIZE).
                                  addPreferredGap(
                                          javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                  addComponent(options ,
                                               javax.swing.GroupLayout.PREFERRED_SIZE ,
                                               100 ,
                                               javax.swing.GroupLayout.PREFERRED_SIZE)).
                          addGroup(layout.createSequentialGroup()
                                  .addComponent(logEdit ,
                                                javax.swing.GroupLayout.PREFERRED_SIZE ,
                                                100 ,
                                                javax.swing.GroupLayout.PREFERRED_SIZE).
                                  addPreferredGap(
                                          javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                  addComponent(modsEdit ,
                                               javax.swing.GroupLayout.PREFERRED_SIZE ,
                                               100 ,
                                               javax.swing.GroupLayout.PREFERRED_SIZE)).
                          addGroup(layout.createSequentialGroup()
                                  .addComponent(levelEdit ,
                                                javax.swing.GroupLayout.PREFERRED_SIZE ,
                                                100 ,
                                                javax.swing.GroupLayout.PREFERRED_SIZE).
                                  addPreferredGap(
                                          javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                  addComponent(modelEdit ,
                                               javax.swing.GroupLayout.PREFERRED_SIZE ,
                                               100 ,
                                               javax.swing.GroupLayout.PREFERRED_SIZE))).
                  addContainerGap())
  );
  layout.setVerticalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(layout.createParallelGroup(
                                  javax.swing.GroupLayout.Alignment.LEADING)
                          .addComponent(jScrollPane1 ,
                                        javax.swing.GroupLayout.PREFERRED_SIZE ,
                                        200 ,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addGroup(layout.createParallelGroup(
                                          javax.swing.GroupLayout.Alignment.TRAILING).
                                  addComponent(jScrollPane2 ,
                                               javax.swing.GroupLayout.PREFERRED_SIZE ,
                                               200 ,
                                               javax.swing.GroupLayout.PREFERRED_SIZE).
                                  addGroup(layout.createSequentialGroup()
                                          .addGroup(layout.createParallelGroup(
                                                          javax.swing.GroupLayout.Alignment.BASELINE).
                                                  addComponent(start)
                                                  .addComponent(options))
                                          .addPreferredGap(
                                                  javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                          addGroup(layout.createParallelGroup(
                                                          javax.swing.GroupLayout.Alignment.BASELINE).
                                                  addComponent(logEdit)
                                                  .addComponent(modsEdit))
                                          .addPreferredGap(
                                                  javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                          addGroup(layout.createParallelGroup(
                                                          javax.swing.GroupLayout.Alignment.BASELINE).
                                                  addComponent(levelEdit)
                                                  .addComponent(modelEdit))
                                          .addGap(47 , 47 , 47)
                                          .addGroup(layout.createParallelGroup(
                                                          javax.swing.GroupLayout.Alignment.BASELINE).
                                                  addComponent(passLabel)
                                                  .addComponent(loginLabel))
                                          .addPreferredGap(
                                                  javax.swing.LayoutStyle.ComponentPlacement.RELATED).
                                          addGroup(layout.createParallelGroup(
                                                          javax.swing.GroupLayout.Alignment.BASELINE).
                                                  addComponent(loginField ,
                                                               javax.swing.GroupLayout.PREFERRED_SIZE ,
                                                               javax.swing.GroupLayout.DEFAULT_SIZE ,
                                                               javax.swing.GroupLayout.PREFERRED_SIZE).
                                                  addComponent(passField ,
                                                               javax.swing.GroupLayout.PREFERRED_SIZE ,
                                                               javax.swing.GroupLayout.DEFAULT_SIZE ,
                                                               javax.swing.GroupLayout.PREFERRED_SIZE))))).
                  addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE ,
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
  this.coreList.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent e ) {
    if ( e.getButton() == MouseEvent.BUTTON3 ) {
     cpop.show(( JList ) e.getSource() , e.getX() , e.getY());
    }
   }
  });
 }
 private final javax.swing.JList coreList = new javax.swing.JList();
 private final JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
 private final JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
 private final JButton levelEdit = new javax.swing.JButton();
 private final JButton logEdit = new javax.swing.JButton();

 private final JTextField loginField = new javax.swing.JTextField();
 private final JLabel loginLabel = new javax.swing.JLabel();
 private final JButton modsEdit = new javax.swing.JButton();
 private final JButton modelEdit = new javax.swing.JButton();
 private final JList modsList = new javax.swing.JList();
 private final JButton options = new javax.swing.JButton();
 private final JPasswordField passField = new javax.swing.JPasswordField();
 private final JLabel passLabel = new javax.swing.JLabel();
 private final JButton start = new javax.swing.JButton();
 private final Pop cpop = new Pop(coreList);
 private final Pop mpop = new Pop(modsList);
 public final Model cmod = new Model();
 public final Model mmod = new Model();

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
