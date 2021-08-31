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
import java.util.*;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class MaquinaHoc4 {
    TablaSimbolos TabSimb;
    InstrucPrograma Prog[];
    int progp = 0;
    int pc;
    Stack<Datum> stack;
    
    public MaquinaHoc4() {
        this.TabSimb = new TablaSimbolos();
        this.TabSimb.init();
        this.Prog = new InstrucPrograma[2048];
        this.progp = 0;
        this.pc = 0;
        this.stack = new Stack();
        this.stack.clear();
    }
    
    public void initCode() {
        this.progp = 0;
        this.stack.clear();
    }
    
    public Integer code(InstrucPrograma inst) {
        Integer oprogp = this.progp;
        this.Prog[this.progp++] = inst;
        return oprogp;
    }
    
    public Integer code2(InstrucPrograma inst1, InstrucPrograma inst2) {
        Integer oprogp = this.progp;
        this.Prog[this.progp++] = inst1;
        this.Prog[this.progp++] = inst2;
        return oprogp;
    }
    
    public Integer code3(InstrucPrograma inst1, InstrucPrograma inst2, InstrucPrograma inst3) {
        Integer oprogp = this.progp;
        this.Prog[this.progp++] = inst1;
        this.Prog[this.progp++] = inst2;
        this.Prog[this.progp++] = inst3;
        return oprogp;
    }
    
    public void execute(int ind, JTextArea AreaResult, JTable jTablePila) {
        InstrucPrograma instruc;
        Datum op1, op2;
        String CadResult = new String();
        Object os[] = new Object[5];
        String TipDatum = new String();
        String Val = new String();
        String NombSymbol = new String();
        String TypeSymbol = new String();
        String ValSymbol = new String();
        DefaultTableModel modeloTablaPila = (DefaultTableModel) jTablePila.getModel();
        
        pc = ind;
        
        while(Prog[pc].Instruc != EnumInstrMaq.STOP) {
            TipDatum = "";
            Val = "";
            NombSymbol = "";
            TypeSymbol = "";
            ValSymbol = "";
            
            instruc = Prog[pc++];
            
            switch(instruc.Instruc) {
                case ADD:
                    op2 = stack.pop();
                    op1 = stack.pop();
                    op1.val += op2.val;
                    stack.push(op1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    os[0] = (Object)"Val";
                    os[1] = (Object)op1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloTablaPila.addRow(os);
                    break;
                case ASSIGN:
                    op2 = stack.pop();
                    op1 = stack.pop();
                    op2.symb.val = op1.val;
                    op2.symb.TipoSymbol = EnumTipoSymbol.VAR;
                    stack.push(op1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    os[0] = (Object)"Val";
                    os[1] = (Object)op1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloTablaPila.addRow(os);
                    break;
                case BLTIN:
                    instruc = Prog[pc++];
                    
                    switch(instruc.Func_BLTIN) {
                        case ABS:
                            op1 = stack.pop();
                            op1.val = Math.abs(op1.val);
                            stack.push(op1);
                            modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                            os[0] = (Object)"Val";
                            os[1] = (Object)op1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloTablaPila.addRow(os);
                            break;
                        case ATAN:
                            op1 = stack.pop();
                            op1.val = (float)Math.atan((double)op1.val);
                            stack.push(op1);
                            modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                            os[0] = (Object)"Val";
                            os[1] = (Object)op1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloTablaPila.addRow(os);
                            break;
                        case COS:
                            op1 = stack.pop();
                            op1.val = (float)Math.cos((double)op1.val);
                            stack.push(op1);
                            modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                            os[0] = (Object)"Val";
                            os[1] = (Object)op1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloTablaPila.addRow(os);
                            break;
                        case EXP:
                            op1 = stack.pop();
                            op1.val = (float)Math.exp((double)op1.val);
                            stack.push(op1);
                            modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                            os[0] = (Object)"Val";
                            os[1] = (Object)op1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloTablaPila.addRow(os);
                            break;
                        case INTEGER:
                            op1 = stack.pop();
                            op1.val = (float)Math.floor((double)op1.val);
                            stack.push(op1);
                            modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                            os[0] = (Object)"Val";
                            os[1] = (Object)op1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloTablaPila.addRow(os);
                            break;
                        case LO10:
                            op1 = stack.pop();
                            op1.val = (float)Math.log10((double)op1.val);
                            stack.push(op1);
                            modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                            os[0] = (Object)"Val";
                            os[1] = (Object)op1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloTablaPila.addRow(os);
                            break;
                        case LOG:
                            op1 = stack.pop();
                            op1.val = (float)Math.log((double)op1.val);
                            stack.push(op1);
                            modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                            os[0] = (Object)"Val";
                            os[1] = (Object)op1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloTablaPila.addRow(os);
                            break;
                        case SIN:
                            op1 = stack.pop();
                            op1.val = (float)Math.sin((double)op1.val);
                            stack.push(op1);
                            modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                            os[0] = (Object)"Val";
                            os[1] = (Object)op1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloTablaPila.addRow(os);
                            break;
                        case SQRT:
                            op1 = stack.pop();
                            op1.val = (float)Math.sqrt((double)op1.val);
                            stack.push(op1);
                            modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                            os[0] = (Object)"Val";
                            os[1] = (Object)op1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloTablaPila.addRow(os);
                            break;
                        default:
                            break;
                    }
                    break;
                case CONSTPUSH:
                    op1 = new Datum();
                    op1.val = Prog[pc++].symbolHoc.val;
                    stack.push(op1);
                    
                    os[0] = (Object)"Val";
                    os[1] = (Object)op1.val;
                    os[2] = (Object)"";
                    os[3] = (Object)"";
                    os[4] = (Object)"";
                    modeloTablaPila.addRow(os);
                    jTablePila.repaint();
                    break;
                case DIV:
                    op2 = stack.pop();
                    op1 = stack.pop();
                    op1.val /= op2.val;
                    stack.push(op1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    os[0] = (Object)"Val";
                    os[1] = (Object)op1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloTablaPila.addRow(os);
                    break;
                case EVAL:
                    op2 = new Datum();
                    op1 = stack.pop();
                    op2.val = op1.symb.val;
                    stack.push(op2);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    os[0] = (Object)"Val";
                    os[1] = (Object)op2.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloTablaPila.addRow(os);
                    break;
                case MUL:
                    op2 = stack.pop();
                    op1 = stack.pop();
                    op1.val *= op2.val;
                    stack.push(op1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    os[0] = (Object)"Val";
                    os[1] = (Object)op1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloTablaPila.addRow(os);
                    break;
                case NEGATE:
                    op1 = stack.pop();
                    op1.val = -op1.val;
                    stack.push(op1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    os[0] = (Object)"Val";
                    os[1] = (Object)op1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloTablaPila.addRow(os);
                    break;
                case POWER:
                    op2 = stack.pop();
                    op1 = stack.pop();
                    op1.val = (float)Math.pow((double)op1.val,(double)op2.val);
                    stack.push(op1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    modeloTablaPila.removeRow(modeloTablaPila.getRowCount()-1);
                    os[0] = (Object)"Val";
                    os[1] = (Object)op1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloTablaPila.addRow(os);
                    break;
                case PRINT:
                    op1 = stack.pop();
                    CadResult = Float.toString(op1.val) + "\n";
                    AreaResult.append(CadResult);
                    modeloTablaPila.removeRow(modeloTablaPila.getColumnCount()-1);
                    break;
                case STOP:
                    break;
                case SUB:
                    op2 = stack.pop();
                    op1 = stack.pop();
                    op1.val -= op2.val;
                    stack.push(op1);
                    modeloTablaPila.removeRow(modeloTablaPila.getColumnCount()-1);
                    modeloTablaPila.removeRow(modeloTablaPila.getColumnCount()-1);
                    os[0] = (Object)"Val";
                    os[1] = (Object)op1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloTablaPila.addRow(os);
                    break;
                case VARPUSH:
                    op1 = new Datum();
                    op1.symb = Prog[pc++].symbolHoc;
                    stack.push(op1);
                    os[0] = (Object)"SYMBOL";
                    os[1] = (Object)"";
                    os[2] = op1.symb.name;
                    os[3] = op1.symb.TipoSymbol;
                    os[4] = op1.symb.val;
                    modeloTablaPila.addRow(os);
                    break;
                default:
                    break;
            }
        }
    }
}
