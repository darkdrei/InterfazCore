/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazcore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import logica.SettingDataType;
import logica.TemplateRegex;

/**
 *
 * @author dark
 */
public class jpComponenteParametro extends javax.swing.JPanel {

    private String type = "";
    private String name = "";


    public String getNameParameter() {
        return name;
    }

    /**
     * Creates new form jpComponente
     */
    public jpComponenteParametro() {
        initComponents();
    }

    public jpComponenteParametro(int index, String type, String name) {
        initComponents();
        //JPanel
        //setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        this.type = type;
        this.name = name;
        this.lbName.setText(name);
        this.setSize(30, 30);
        this.setVisible(true);
    }
    
    public String getValue(){
        return this.txtValue.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        txtValue = new javax.swing.JTextField();

        lbName.setText("Nombre ");

        txtValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValueActionPerformed(evt);
            }
        });
        txtValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValueKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValueActionPerformed

    private void txtValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValueKeyReleased
        // TODO add your handling code here:
        boolean exist_type = false;
        String pattern = "";
        String date_type = "";
        if (this.type == SettingDataType.DOUBLE || this.type == SettingDataType.FLOAT) {
            exist_type = true;
            pattern = TemplateRegex.REAL;
        } else if (this.type == SettingDataType.INTEGER) {
            exist_type = true;
            pattern = TemplateRegex.NUMBER;
        } else if (this.type == SettingDataType.STRING) {
            exist_type = true;
            pattern = TemplateRegex.TEXT;
        }
        if (exist_type) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(txtValue.getText());
            if (!m.matches()) {
                JOptionPane.showMessageDialog(this, "Solo se permiten valores "+this.type+".", "Validar tipo de dato", HEIGHT);
            }
        }else{
            JOptionPane.showMessageDialog(this, "El tipo de dato no se encuentra definido.", "Validar tipo de dato", HEIGHT);
        }
    }//GEN-LAST:event_txtValueKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel lbName;
    public javax.swing.JTextField txtValue;
    // End of variables declaration//GEN-END:variables
}