/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ComponentAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

/**
 *
 * @author ilya
 */
public class LogManager extends javax.swing.JFrame {

 private int width, height;

 /**
  * Creates new form LogExplorer
  */
 public LogManager () {
  setVisible(false);
  setTitle("LogManager");
  setBounds(0 , 0 , this.getMaximumSize().width , this.getMaximumSize().height);
  setLayout(null);
  addComponentListener(new ComponentAdapter() {
   @Override
   public void componentResized ( java.awt.event.ComponentEvent evt ) {
    resize();
   }
  });
  addWindowListener(new WindowAdapter() {
   @Override
   public void windowClosing ( WindowEvent e ) {
    main.Main.main.destroy();
    setVisible(false);
   }
  });

  jScrollPane1.setViewportView(jTextPane1);
  jScrollPane2.setViewportView(jTextPane2);
  jScrollPane3.setViewportView(jTextPane3);
  jScrollPane4.setViewportView(jTextPane4);
  jScrollPane5.setViewportView(jTextPane5);
  jScrollPane5.getVerticalScrollBar().setValue(jScrollPane5.getVerticalScrollBar().getMaximum());
  jPanel1.add(jScrollPane1);
  jPanel2.add(jScrollPane2);
  jPanel3.add(jScrollPane3);
  jPanel4.add(jScrollPane4);
  jPanel5.add(jScrollPane5);

  jPanel1.setLayout(null);
  jPanel2.setLayout(null);
  jPanel3.setLayout(null);
  jPanel4.setLayout(null);
  jPanel5.setLayout(null);

  jTabbedPane1.addTab("Exceptions" , jPanel1);
  jTabbedPane1.addTab("Warnings" , jPanel2);
  jTabbedPane1.addTab("Info" , jPanel3);
  jTabbedPane1.addTab("Debug" , jPanel4);
  jTabbedPane1.addTab("All" , jPanel5);

  add(jTabbedPane1);

  resize();

  repaint();

 }

 private void resize () {
  width = getWidth();
  height = getHeight();

  jTabbedPane1.setBounds(0 , 0 , width , height);

  jScrollPane1.setBounds(0 , 0 , width - 2 , height - 52);
  jScrollPane2.setBounds(0 , 0 , width - 2 , height - 52);
  jScrollPane3.setBounds(0 , 0 , width - 2 , height - 52);
  jScrollPane4.setBounds(0 , 0 , width - 2 , height - 52);
  jScrollPane5.setBounds(0 , 0 , width - 2 , height - 52);

  jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
  jScrollPane2.getVerticalScrollBar().setValue(jScrollPane2.getVerticalScrollBar().getMaximum());
  jScrollPane3.getVerticalScrollBar().setValue(jScrollPane3.getVerticalScrollBar().getMaximum());
  jScrollPane4.getVerticalScrollBar().setValue(jScrollPane4.getVerticalScrollBar().getMaximum());
  jScrollPane5.getVerticalScrollBar().setValue(jScrollPane5.getVerticalScrollBar().getMaximum());
 }

 public void update () {
  jTextPane1.setText(main.Main.LOG.getE());
  jTextPane2.setText(main.Main.LOG.getW());
  jTextPane3.setText(main.Main.LOG.getI());
  jTextPane4.setText(main.Main.LOG.getD());
  jTextPane5.setText(main.Main.LOG.getAll());
 }

 private final JPanel jPanel1 = new JPanel();
 private final JPanel jPanel2 = new JPanel();
 private final JPanel jPanel3 = new JPanel();
 private final JPanel jPanel4 = new JPanel();
 private final JPanel jPanel5 = new JPanel();
 private final JScrollPane jScrollPane1 = new JScrollPane();
 private final JScrollPane jScrollPane2 = new JScrollPane();
 private final JScrollPane jScrollPane3 = new JScrollPane();
 private final JScrollPane jScrollPane4 = new JScrollPane();
 private final JScrollPane jScrollPane5 = new JScrollPane();
 private final JTabbedPane jTabbedPane1 = new JTabbedPane();
 private final JTextPane jTextPane1 = new JTextPane();
 private final JTextPane jTextPane2 = new JTextPane();
 private final JTextPane jTextPane3 = new JTextPane();
 private final JTextPane jTextPane4 = new JTextPane();
 private final JTextPane jTextPane5 = new JTextPane();
}
