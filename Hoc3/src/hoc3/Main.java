package hoc3;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.*;
import java.lang.Math;

public class Main extends javax.swing.JFrame {
    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TXT_ToAnalyze = new javax.swing.JTextArea();
        BTN_Lexic = new javax.swing.JButton();
        BTN_Sintactic = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXT_Lexic = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        TXT_Sintactic = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Calculadora HOC3");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Introduzca la cadena:");

        TXT_ToAnalyze.setColumns(20);
        TXT_ToAnalyze.setRows(5);
        jScrollPane1.setViewportView(TXT_ToAnalyze);

        BTN_Lexic.setText("Analizar lexicamente");
        BTN_Lexic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_LexicActionPerformed(evt);
            }
        });

        BTN_Sintactic.setText("Analizar sintacticamente");
        BTN_Sintactic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SintacticActionPerformed(evt);
            }
        });

        TXT_Lexic.setColumns(20);
        TXT_Lexic.setRows(5);
        jScrollPane2.setViewportView(TXT_Lexic);

        TXT_Sintactic.setColumns(20);
        TXT_Sintactic.setRows(5);
        jScrollPane3.setViewportView(TXT_Sintactic);

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("3CM6, Uriel García Rivas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN_Lexic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BTN_Sintactic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 465, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_Lexic)
                    .addComponent(BTN_Sintactic))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_LexicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_LexicActionPerformed
        Symbol simb;
        String Lexema = new String();
        String CadAux = new String();
        File ArchEntrada = new File("ArchEntrada.txt");
        PrintWriter escribir;
        
        TXT_Lexic.setText("");
        TXT_Sintactic.setText("");
        
        try {
            escribir = new PrintWriter(ArchEntrada);
            escribir.print(TXT_ToAnalyze.getText());
            escribir.close();
        } catch(FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Reader lector;
            lector = new BufferedReader(new FileReader("ArchEntrada.txt"));
            
            AnalizadorLexico Lexic = new AnalizadorLexico(lector);
            do {
                simb = Lexic.next_token();
                CadAux = Integer.toString(simb.sym);
                Lexema = Lexic.yytext();
                if(simb.sym == AnalizadorSintacticoSym.EOF) {
                    CadAux = "Token: " + CadAux + "\tIdentToken: FIN \n";
                }
                else {
                    switch(simb.sym) {
                        case AnalizadorSintacticoSym.Enter:
                            CadAux = "Token: " + CadAux + "\tIdentToken: Enter\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.NUM:
                            CadAux = "Token: " + CadAux + "\tIdentToken: NUM\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.OpSuma:
                            CadAux = "Token: " + CadAux + "\tIdentToken: OpSuma\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.OpResta:
                            CadAux = "Token: " + CadAux + "\tIdentToken: OpResta\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.OpProd:
                            CadAux = "Token: " + CadAux + "\tIdentToken: OpProd\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.OpDiv:
                            CadAux = "Token: " + CadAux + "\tIdentToken: OpDiv\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.ParIzq:
                            CadAux = "Token: " + CadAux + "\tIdentToken: ParIzq\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.ParDer:
                            CadAux = "Token: " + CadAux + "\tIdentToken: ParDer\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.OpAsig:
                            CadAux = "Token: " + CadAux + "\tIdentToken: OpAsig\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.error:
                            CadAux = "Token: " + CadAux + "\tIdentToken: ERROR\t Lexema: " + Lexema + "\n";
                            break;
                        case AnalizadorSintacticoSym.VAR:
                            CadAux = "Token: " + CadAux + "\tIdentToken: VAR\t Lexema: " + Lexema + ", índice: " + Integer.toString((Integer) simb.value) + "\n";
                            break;
                        default:
                            CadAux = "Token: " + CadAux + "\tIdentToken: BLTIN\t Lexema: " + Lexic.yytext() + "\n";
                            break;
                    }
                }
                TXT_Lexic.append(CadAux);
            } while(simb.sym != AnalizadorSintacticoSym.EOF);
        } catch(IOException ex) {
            TXT_Lexic.append("IOException\n");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_LexicActionPerformed

    private void BTN_SintacticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SintacticActionPerformed
        try {
            AnalizadorSintactico Sintac = new AnalizadorSintactico(new AnalizadorLexico(new FileReader("ArchEntrada.txt")));
            Sintac.interfaz = this;
            Object result = Sintac.parse().value;
            TXT_Sintactic.append("FIN DEL ANÁLISIS SINTÁCTICO.\n");
        } catch(Exception ex) {
            TXT_Sintactic.append("Análisis sintáctico finalizado sin éxito, errores encontrados.\n");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_BTN_SintacticActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    public void PonerTextoSintact(String s) {
        TXT_Sintactic.append(s);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Lexic;
    private javax.swing.JButton BTN_Sintactic;
    private javax.swing.JTextArea TXT_Lexic;
    private javax.swing.JTextArea TXT_Sintactic;
    private javax.swing.JTextArea TXT_ToAnalyze;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
