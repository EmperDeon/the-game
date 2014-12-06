/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 *
 * @author ilya
 */
public class ModDialog extends javax.swing.JDialog {

 /**
  * A return status code - returned if Cancel button has been pressed
  */
 public static final int RET_CANCEL = 0;
 /**
  * A return status code - returned if OK button has been pressed
  */
 public static final int RET_OK = 1;

 /**
  * Creates new form NewOkCancelDialog
  *
  * @param parent
  * @param modal
  */
 public ModDialog ( boolean modal ) {
  super(( JFrame ) null , modal);
  initComponents();

  String cancelName = "cancel";
  InputMap inputMap = getRootPane().getInputMap(
          JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE , 0) , cancelName);
  ActionMap actionMap = getRootPane().getActionMap();
  actionMap.put(cancelName , new AbstractAction() {
   public void actionPerformed ( ActionEvent e ) {
    doClose(RET_CANCEL);
   }
  });
 }

 public int getReturnStatus () {
  return returnStatus;
 }
                        
 private void initComponents () {
  setLayout(null);
  setBounds(300 , 300 , 300 , 200);

  addWindowListener(new java.awt.event.WindowAdapter() {
   @Override
   public void windowClosing ( java.awt.event.WindowEvent evt ) {
    doClose(RET_CANCEL);
   }
  });

  okButton.setText("OK");
  okButton.addActionListener(( java.awt.event.ActionEvent evt ) -> {
   doClose(RET_OK);
  });

  cancelButton.setText("Cancel");
  cancelButton.addActionListener(( java.awt.event.ActionEvent evt ) -> {
   doClose(RET_CANCEL);
  });

  getRootPane().setDefaultButton(okButton);

  add(okButton);
  add(cancelButton);
  add(label);
  repaint();
 }                        

 private void doClose ( int retStatus ) {
  returnStatus = retStatus;
  setVisible(false);
  dispose();
 }

 private final JLabel label = new JLabel();
 private final JButton cancelButton = new JButton();
 private final JButton okButton = new JButton();
 private int returnStatus = RET_CANCEL;
}
