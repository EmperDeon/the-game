/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import utils.Logger;

/**
 *
 * @author ilya
 */
public class LogManager extends javax.swing.JFrame {

 /**
  * Creates new form LogExplorer
  */
 public LogManager () {
  setTitle("LogManager");
  setLocation(600 , 600);
  initComponents();
 }

 /**
  * This method is called from within the constructor to initialize the form.
  * WARNING: Do NOT modify this code. The content of this method is always
  * regenerated by the Form Editor.
  */
 @SuppressWarnings( "unchecked" )
 // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
 private void initComponents() {

  jTabbedPane2 = new javax.swing.JTabbedPane();
  jPanel1 = new javax.swing.JPanel();
  jScrollPane2 = new javax.swing.JScrollPane();
  jTextPane1 = new javax.swing.JTextPane();
  jPanel2 = new javax.swing.JPanel();
  jScrollPane3 = new javax.swing.JScrollPane();
  jTextPane2 = new javax.swing.JTextPane();
  jPanel3 = new javax.swing.JPanel();
  jScrollPane4 = new javax.swing.JScrollPane();
  jTextPane3 = new javax.swing.JTextPane();
  jPanel4 = new javax.swing.JPanel();
  jScrollPane5 = new javax.swing.JScrollPane();
  jTextPane4 = new javax.swing.JTextPane();
  jButton1 = new javax.swing.JButton();

  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

  jScrollPane2.setViewportView(jTextPane1);

  javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
  jPanel1.setLayout(jPanel1Layout);
  jPanel1Layout.setHorizontalGroup(
   jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
  );
  jPanel1Layout.setVerticalGroup(
   jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
  );

  jTabbedPane2.addTab("Exceptions", jPanel1);

  jScrollPane3.setViewportView(jTextPane2);

  javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
  jPanel2.setLayout(jPanel2Layout);
  jPanel2Layout.setHorizontalGroup(
   jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
  );
  jPanel2Layout.setVerticalGroup(
   jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
  );

  jTabbedPane2.addTab("Warnings", jPanel2);

  jScrollPane4.setViewportView(jTextPane3);

  javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
  jPanel3.setLayout(jPanel3Layout);
  jPanel3Layout.setHorizontalGroup(
   jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
  );
  jPanel3Layout.setVerticalGroup(
   jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
  );

  jTabbedPane2.addTab("Info", jPanel3);

  jScrollPane5.setViewportView(jTextPane4);

  javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
  jPanel4.setLayout(jPanel4Layout);
  jPanel4Layout.setHorizontalGroup(
   jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
  );
  jPanel4Layout.setVerticalGroup(
   jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
  );

  jTabbedPane2.addTab("Debug", jPanel4);

  jButton1.setText("Open File");
  jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
   public void mouseClicked(java.awt.event.MouseEvent evt) {
    jButton1MouseClicked(evt);
   }
  });

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(layout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jButton1)
    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
  );
  layout.setVerticalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(layout.createSequentialGroup()
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addGroup(layout.createSequentialGroup()
      .addGap(33, 33, 33)
      .addComponent(jButton1))
     .addGroup(layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
  );

  pack();
 }// </editor-fold>//GEN-END:initComponents

 private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
  JFileChooser f = new JFileChooser(Main.mdir);
  if ( f.showDialog(null , "Open File") == JFileChooser.APPROVE_OPTION ) {
   try ( ObjectInputStream t = new ObjectInputStream(new FileInputStream(f.
           getSelectedFile())) ) {
    log = ( Logger ) t.readObject();
    this.jTextPane1.setText(log.getE());
    this.jTextPane2.setText(log.getW());
    this.jTextPane3.setText(log.getI());
    this.jTextPane4.setText(log.getD());
   } catch ( IOException | ClassNotFoundException e ) {
   }
  }
 }//GEN-LAST:event_jButton1MouseClicked

 public static void main ( String args[] ) {
  try {
   for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.
           getInstalledLookAndFeels() ) {
    if ( "Nimbus".equals(info.getName()) ) {
     javax.swing.UIManager.setLookAndFeel(info.getClassName());
     break;
    }
   }
  } catch ( ClassNotFoundException | InstantiationException |
            IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex ) {
  }

  java.awt.EventQueue.invokeLater(() -> {
   new LogManager().setVisible(true);
  });
 }

 private Logger log = new Logger();
 // Variables declaration - do not modify//GEN-BEGIN:variables
 private javax.swing.JButton jButton1;
 private javax.swing.JPanel jPanel1;
 private javax.swing.JPanel jPanel2;
 private javax.swing.JPanel jPanel3;
 private javax.swing.JPanel jPanel4;
 private javax.swing.JScrollPane jScrollPane2;
 private javax.swing.JScrollPane jScrollPane3;
 private javax.swing.JScrollPane jScrollPane4;
 private javax.swing.JScrollPane jScrollPane5;
 private javax.swing.JTabbedPane jTabbedPane2;
 private javax.swing.JTextPane jTextPane1;
 private javax.swing.JTextPane jTextPane2;
 private javax.swing.JTextPane jTextPane3;
 private javax.swing.JTextPane jTextPane4;
 // End of variables declaration//GEN-END:variables
}
