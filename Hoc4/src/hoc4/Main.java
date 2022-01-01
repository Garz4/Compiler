/*
 * MIT License
 * 
 * Copyright (c) 2021 Uriel Rivas
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * https://github.com/Garz4/compilers/blob/master/LICENSE
 */

package hoc4;

import java.io.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.*;
import javax.swing.table.DefaultTableModel;

public class Main extends javax.swing.JFrame {
    AnalizadorSintactico Sintac;

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
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCodigo = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTablePila = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaResultados = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Calculadora HOC4");

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

        jButton1.setText("Ejecutar código");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableCodigo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "INST-SYMB-FUNC", "NAME", "VAL", "FUNCIÓN"
            }
        ));
        jScrollPane4.setViewportView(jTableCodigo);

        jLabel3.setText("Pila:");

        jTablePila.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Datum", "Valor", "Symbol", "Type Symbol", "Val Symbol"
            }
        ));
        jScrollPane6.setViewportView(jTablePila);

        jLabel5.setText("Resultados:");

        jTextAreaResultados.setColumns(20);
        jTextAreaResultados.setRows(5);
        jScrollPane5.setViewportView(jTextAreaResultados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 1022, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                    .addComponent(BTN_Lexic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BTN_Sintactic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane5))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTN_Lexic)
                            .addComponent(BTN_Sintactic))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5)))
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
        } catch (FileNotFoundException ex) {
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
                if (simb.sym == AnalizadorSintacticoSym.EOF) {
                    CadAux = "Token: " + CadAux + "\tIdentToken: FIN \n";
                } else {
                    switch (simb.sym) {
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
                            CadAux = "Token: " + CadAux + "\tIdentToken: VAR\t Lexema: " + Lexema /*+ ", índice: " + Integer.toString((Integer) simb.value) */+ "\n";
                            break;
                        default:
                            CadAux = "Token: " + CadAux + "\tIdentToken: BLTIN\t Lexema: " + Lexic.yytext() + "\n";
                            break;
                    }
                }

                TXT_Lexic.append(CadAux);
            } while (simb.sym != AnalizadorSintacticoSym.EOF);
        } catch (IOException ex) {
            TXT_Lexic.append("IOException\n");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_LexicActionPerformed

    private void BTN_SintacticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SintacticActionPerformed
        String cadAux = new String();
        String CadInst = new String();
        String Name = new String();
        String Valor = new String();
        String ptrFunc = new String();
        Object os[] = new Object[4];
        DefaultTableModel modeloTabla = (DefaultTableModel) jTableCodigo.getModel();
        InstrucPrograma instruc;
        AnalizadorLexico Lexic = null;

        try {
            Lexic = new AnalizadorLexico(new FileReader("ArchEntrada.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        modeloTabla.setRowCount(0);

        try {
            Sintac = new AnalizadorSintactico(Lexic);

            Sintac.interfaz = this;
            Sintac.maquinaHoc4 = new MaquinaHoc4();
            Lexic.maqHoc = Sintac.maquinaHoc4;

            SymbolHoc s;
            Iterator it = Lexic.maqHoc.TabSimb.getHashtableSimbolos().iterator();

            while (it.hasNext()) {
                s = (SymbolHoc) it.next();
                cadAux = "==============================\n"
                        + "Symbol: " + s.getName() + "\n"+
                        "==============================\n";
                TXT_Sintactic.append(cadAux);
            }

            try {
                Object result = Sintac.parse().value;
                InstrucPrograma inst2 = new InstrucPrograma();
                inst2.TipInstr = EnumTipoInstr.INSTRUC;
                inst2.Instruc = EnumInstrMaq.STOP;
                Sintac.maquinaHoc4.code(inst2);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            TXT_Sintactic.append("FIN DEL ANÁLISIS SINTÁCTICO.\n");

            for (int i = 0; i < Sintac.maquinaHoc4.progp; ++i) {
                instruc = Sintac.maquinaHoc4.Prog[i];
                cadAux = "====== ERROR ======";
                CadInst = "";
                Name = "";
                Valor = "";
                ptrFunc = "";

                switch (instruc.TipInstr) {
                    case INSTRUC:
                        switch (instruc.Instruc) {
                            case ADD:
                                cadAux = "instruc ADD";
                                CadInst = "ADD";
                                break;
                            case ASSIGN:
                                cadAux = "instruc ASSIGN";
                                CadInst = "ASSIGN";
                                break;
                            case BLTIN:
                                cadAux = "instruc BLTIN";
                                CadInst = "BLTIN";
                                break;
                            case CONSTPUSH:
                                cadAux = "instruc CONSTPUSH";
                                CadInst = "CONSTPUSH";
                                break;
                            case DIV:
                                cadAux = "instruc DIV";
                                CadInst = "DIV";
                                break;
                            case EVAL:
                                cadAux = "instruc EVAL";
                                CadInst = "EVAL";
                                break;
                            case MUL:
                                cadAux = "instruc MUL";
                                CadInst = "MUL";
                                break;
                            case NEGATE:
                                cadAux = "instruc NEGATE";
                                CadInst = "NEGATE";
                                break;
                            case POWER:
                                cadAux = "instruc POWER";
                                CadInst = "POWER";
                                break;
                            case PRINT:
                                cadAux = "instruc PRINT";
                                CadInst = "PRINT";
                                break;
                            case STOP:
                                cadAux = "instruc STOP";
                                CadInst = "STOP";
                                break;
                            case SUB:
                                cadAux = "instruc SUB";
                                CadInst = "SUB";
                                break;
                            case VARPUSH:
                                cadAux = "instruc VARPUSH";
                                CadInst = "VARPUSH";
                                break;
                        }

                        cadAux += "\n";
                        TXT_Sintactic.append(cadAux);
                        break;
                    case BLTIN:
                        CadInst = "FuncPredef";
                        switch (instruc.Func_BLTIN) {
                            case ABS:
                                cadAux = "instruc ABS";
                                Name = "abs";
                                ptrFunc = "ABS";
                                break;
                            case ATAN:
                                cadAux = "instruc ATAN";
                                Name = "atan";
                                ptrFunc = "ATAN";
                                break;
                            case COS:
                                cadAux = "instruc COS";
                                Name = "cos";
                                ptrFunc = "COS";
                                break;
                            case EXP:
                                cadAux = "instruc EXP";
                                Name = "exp";
                                ptrFunc = "EXP";
                                break;
                            case INTEGER:
                                cadAux = "instruc INTEGER";
                                Name = "integer";
                                ptrFunc = "INTEGER";
                                break;
                            case LO10:
                                cadAux = "instruc LOG10";
                                Name = "log10";
                                ptrFunc = "LOG10";
                                break;
                            case LOG:
                                cadAux = "instruc LOG";
                                Name = "log";
                                ptrFunc = "LOG";
                                break;
                            case SIN:
                                cadAux = "instruc SIN";
                                Name = "sin";
                                ptrFunc = "SIN";
                                break;
                            case SQRT:
                                cadAux = "instruc SQRT";
                                Name = "sqrt";
                                ptrFunc = "SQRT";
                                break;
                        }

                        cadAux += "\n";
                        TXT_Sintactic.append(cadAux);
                        break;
                    case SYMBOL:
                        cadAux = "Symbol name: " + instruc.symbolHoc.getName() + 
                                ", val = " + Float.toString(instruc.symbolHoc.getValue()) + "\n";
                        TXT_Sintactic.append(cadAux);

                        switch (instruc.symbolHoc.getTipoSymbol()) {
                            case VAR:
                                CadInst = "VAR";
                                Name = instruc.symbolHoc.getName();
                                Valor = Float.toString(instruc.symbolHoc.getValue());
                                ptrFunc = "";
                                break;
                            case UNDEF:
                                CadInst = "UNDEF";
                                Name = instruc.symbolHoc.getName();
                                Valor = "---";
                                ptrFunc = "";
                                break;
                            case CONST_NUM:
                                CadInst = "CONST_NUM";
                                Name = instruc.symbolHoc.getName();
                                Valor = Float.toString(instruc.symbolHoc.getValue());
                                ptrFunc = "";
                                break;
                            case CONST_PREDEF:
                                CadInst = "CONST_PREDEF";
                                Name = instruc.symbolHoc.getName();
                                Valor = Float.toString(instruc.symbolHoc.getValue());
                                ptrFunc = "";
                                break;
                        }

                        break;
                }
                os[0] = (Object)CadInst;
                os[1] = (Object)Name;
                os[2] = (Object)Valor;
                os[3] = (Object)ptrFunc;
                modeloTabla.addRow(os);
            }
        } catch (Exception ex) {
            TXT_Sintactic.append("Análisis sintáctico finalizado sin éxito, errores encontrados.\n");
            ex.printStackTrace();
        }

        //TXT_Sintactic.append("FIN DEL ANÁLISIS SINTÁCTICO.\n");
    }//GEN-LAST:event_BTN_SintacticActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Object os[] = new Object[5];
        DefaultTableModel modeloPila = (DefaultTableModel) jTablePila.getModel();
        modeloPila.setNumRows(0);
        String Cad1 = new String();
        String Cad2 = new String();
        String Cad3 = new String();
        String Cad4 = new String();
        String Cad5 = new String();
        jTextAreaResultados.setText("");
        os[0] = Cad1;
        os[1] = Cad2;
        os[2] = Cad3;
        os[3] = Cad4;
        os[4] = Cad5;
        modeloPila.addRow(os);
        modeloPila.addRow(os);
        modeloPila.addRow(os);
        modeloPila.addRow(os);
        modeloPila.fireTableDataChanged();
        Sintac.maquinaHoc4.execute(0, jTextAreaResultados, this.jTablePila);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTableCodigo;
    private javax.swing.JTable jTablePila;
    private javax.swing.JTextArea jTextAreaResultados;
    // End of variables declaration//GEN-END:variables
}
