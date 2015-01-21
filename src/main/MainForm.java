package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListDataListener;
import static main.Main.LOG;
import static main.Main.Tr;
import static main.Main.mods;

public class MainForm extends JFrame {

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
 private final JPanel panel = new JPanel();
 private final JPanel logpanel = new JPanel();

 private final CPop cpop = new CPop();
 private final MPop mpop = new MPop();
 public final MModel cmod = new MModel();
 public final CModel mmod = new CModel();

 public MainForm () {
  setTitle("Launcher");
  setBounds(600 , 300 , 569 , 240);
  setResizable(false);
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  addWindowListener(new WindowAdapter() {
   @Override
   public void windowClosing ( WindowEvent e ) {
    LOG.save();
    mods.fsave();
   }
  });
  setLayout(null);

  modsList.setModel(mmod);
  jScrollPane1.setViewportView(modsList);
  jScrollPane1.setBounds(10 , 10 , 150 , 200);
  modsList.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent e ) {
    if ( e.getButton() == MouseEvent.BUTTON3 ) {
     mpop.show(( JList ) e.getSource() , e.getX() , e.getY());
    }
   }
  });
  modsList.setCellRenderer(new MCellRenderer());

  coreList.setModel(cmod);
  jScrollPane2.setViewportView(coreList);
  jScrollPane2.setBounds(170 , 10 , 150 , 200);
  coreList.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent e ) {
    if ( e.getButton() == MouseEvent.BUTTON3 ) {
     cpop.show(( JList ) e.getSource() , e.getX() , e.getY());
    }
   }
  });
  coreList.setCellRenderer(new CCellRenderer());

  start.setText("Start");
  start.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( java.awt.event.MouseEvent evt ) {
    Tr.start();
    setVisible(false);
   }
  });
  start.setBounds(325 , 10 , 100 , 30);

  options.setText("Options");
  options.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    main.Main.optionseditor.setVisible(true);
    setVisible(false);
   }
  });
  options.setBounds(459 , 10 , 100 , 30);

  panel.setBounds(325 , 45 , 235 , 90);
  panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
  panel.setLayout(null);

  modelEdit.setText("Models");
  modelEdit.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    main.Main.modeditor.setVisible(true);
    setVisible(false);
   }
  });
  modelEdit.setBounds(5 , 5 , 100 , 30);

  modsEdit.setText("Mods");
  modsEdit.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    main.Main.modeditor.setVisible(true);
    setVisible(false);
   }
  });
  modsEdit.setBounds(130 , 5 , 100 , 30);

  logEdit.setText("Logs");
  logEdit.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    main.Main.logmanager.setVisible(true);
   }
  });
  logEdit.setBounds(5 , 48 , 100 , 30);

  levelEdit.setText("Level");
  levelEdit.addMouseListener(new java.awt.event.MouseAdapter() {
   @Override
   public void mouseClicked ( MouseEvent evt ) {
    main.Main.leveleditor.setVisible(true);
    setVisible(false);
   }
  });
  levelEdit.setBounds(130 , 48 , 100 , 30);

  logpanel.setBounds(325 , 143 , 235 , 65);
  logpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
  logpanel.setLayout(null);

  loginLabel.setText("Логин:");
  loginLabel.setBounds(5 , 10 , 80 , 15);

  loginField.setText("test");
  loginField.setBounds(5 , 30 , 110 , 30);

  passField.setText("test");
  passField.setBounds(120 , 30 , 110 , 30);

  passLabel.setText("Пароль:");
  passLabel.setBounds(120 , 10 , 80 , 15);

  add(jScrollPane1);
  add(jScrollPane2);
  add(start);
  add(options);
  panel.add(modelEdit);
  panel.add(modsEdit);
  panel.add(logEdit);
  panel.add(levelEdit);
  add(panel);
  logpanel.add(loginLabel);
  logpanel.add(loginField);
  logpanel.add(passLabel);
  logpanel.add(passField);
  add(logpanel);

  java.awt.EventQueue.invokeLater(() -> {
   setVisible(true);
  });

  repaint();
 }

 private class CPop extends JPopupMenu {

  private final JMenuItem enab;
  private final JMenuItem disb;
  private final JMenuItem delt;

  public CPop () {

   enab = new JMenuItem("Включить выбранный мод");
   enab.addActionListener(e -> {
    cmod.enable(coreList.getSelectedIndex());
   });

   disb = new JMenuItem("Выключить выбранный мод");
   disb.addActionListener(e -> {
    cmod.disable(coreList.getSelectedIndex());
   });

   delt = new JMenuItem("Удалить выбранный мод");
   delt.addActionListener(e -> {
    cmod.delete(coreList.getSelectedIndex());
   });

   add(enab);
   add(disb);
   add(delt);
  }
 }

 private class MPop extends JPopupMenu {

  private final JMenuItem enab;
  private final JMenuItem disb;
  private final JMenuItem delt;

  public MPop () {

   enab = new JMenuItem("Включить выбранный мод");
   enab.addActionListener(e -> {
    mmod.enable(modsList.getSelectedIndex());
   });

   disb = new JMenuItem("Выключить выбранный мод");
   disb.addActionListener(e -> {
    mmod.disable(modsList.getSelectedIndex());
   });

   delt = new JMenuItem("Удалить выбранный мод");
   delt.addActionListener(e -> {
    mmod.delete(modsList.getSelectedIndex());
   });

   add(enab);
   add(disb);
   add(delt);
  }
 }

 public class MModel extends AbstractListModel {

  private final ArrayList<String> cont = new ArrayList<>();
  private final ArrayList<String> disb = new ArrayList<>();

  public MModel () {
   cont.add("test");
  }

  /**
   * Adds a listener to the list that's notified each time a change
   * to the data model occurs.
   *
   * @param l the <code>ListDataListener</code> to be added
   */
  @Override
  public void addListDataListener ( ListDataListener l ) {
   listenerList.add(ListDataListener.class , l);
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
  }

  /**
   * Removes a listener from the list that's notified each time a
   * change to the data model occurs.
   *
   * @param l the <code>ListDataListener</code> to be removed
   */
  @Override
  public void removeListDataListener ( ListDataListener l ) {
   listenerList.remove(ListDataListener.class , l);
  }

  @Override
  public int getSize () {
   return cont.size();
  }

  @Override
  public Object getElementAt ( int i ) {
   return cont.get(i);
  }

  public void add ( String obj ) {
   cont.add(obj);
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
  }

  public boolean isDisabled ( int i ) {
   if ( i != -1 ) {
    return disb.contains(cont.get(i));
   }
   return false;
  }

  public void delete ( int i ) {
   if ( i != -1 ) {
    cont.remove(i);
   } else {
    main.Main.LOG.addE("No selected mod");
   }
   // main.Main.mods.deleteMod(cont.get(i));
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
  }

  public void disable ( int i ) {
   if ( i != -1 ) {
    disb.add(cont.get(i));
   } else {
    main.Main.LOG.addE("No selected mod");
   }
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
   // main.Main.mods.disableMod(cont.get(i));
  }

  public void enable ( int i ) {
   if ( i != -1 ) {
    disb.remove(i);
   } else {
    main.Main.LOG.addE("No selected mod");
   }
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
  }
 }

 public class CModel extends AbstractListModel {

  private final ArrayList<String> cont = new ArrayList<>();
  private final ArrayList<String> disb = new ArrayList<>();

  public CModel () {
   cont.add("test");
  }

  /**
   * Adds a listener to the list that's notified each time a change
   * to the data model occurs.
   *
   * @param l the <code>ListDataListener</code> to be added
   */
  @Override
  public void addListDataListener ( ListDataListener l ) {
   listenerList.add(ListDataListener.class , l);
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
  }

  /**
   * Removes a listener from the list that's notified each time a
   * change to the data model occurs.
   *
   * @param l the <code>ListDataListener</code> to be removed
   */
  @Override
  public void removeListDataListener ( ListDataListener l ) {
   listenerList.remove(ListDataListener.class , l);
  }

  @Override
  public int getSize () {
   return cont.size();
  }

  @Override
  public Object getElementAt ( int i ) {
   return cont.get(i);
  }

  public void add ( String obj ) {
   cont.add(obj);
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
  }

  public boolean isDisabled ( int i ) {
   if ( i != -1 ) {
    return disb.contains(cont.get(i));
   }
   return false;
  }

  public void delete ( int i ) {
   if ( i != -1 ) {
    cont.remove(i);
   } else {
    main.Main.LOG.addE("No selected mod");
   }
   // main.Main.mods.deleteCMod(cont.get(i));
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
  }

  public void disable ( int i ) {
   if ( i != -1 ) {
    disb.add(cont.get(i));
   } else {
    main.Main.LOG.addE("No selected mod");
   }
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
   // main.Main.mods.disableCMod(cont.get(i));
  }

  public void enable ( int i ) {
   if ( i != -1 ) {
    disb.remove(i);
   } else {
    main.Main.LOG.addE("No selected mod");
   }
   for ( ListDataListener e : listenerList.getListeners(ListDataListener.class) ) {
    e.contentsChanged(null);
   }
  }
 }

 public class MCellRenderer extends DefaultListCellRenderer {

  @Override
  public Component getListCellRendererComponent ( JList list , Object value ,
                                                  int index , boolean isSelected ,
                                                  boolean cellHasFocus ) {
   Component c = super.getListCellRendererComponent(list , value , index ,
                                                    isSelected , cellHasFocus);
   if ( mmod.isDisabled(index) ) {
    c.setBackground(new Color(176 , 179 , 184));
   } else if ( isSelected ) {
    c.setBackground(new Color(214 , 217 , 223));
   }

   return c;
  }
 }

 public class CCellRenderer extends DefaultListCellRenderer {

  @Override
  public Component getListCellRendererComponent ( JList list , Object value ,
                                                  int index , boolean isSelected ,
                                                  boolean cellHasFocus ) {
   Component c = super.getListCellRendererComponent(list , value , index ,
                                                    isSelected , cellHasFocus);
   if ( cmod.isDisabled(index) ) {
    c.setBackground(new Color(176 , 179 , 184));
   } else if ( isSelected ) {
    c.setBackground(new Color(214 , 217 , 223));
   }

   return c;
  }
 }
}
