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

import java.util.Stack;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class MaquinaHoc4 {
    public MaquinaHoc4() {
        _tablaSimbolos = new TablaSimbolos();
        _tablaSimbolos.init();
        // TODO(Garz4): Stop hardcoding numbers like the following.
        _instrucPrograma = new InstrucPrograma[2048];
        _instrucProgramaIndex = 0;
        _programCounter = 0;
        _pilaDatum = new Stack<>();
        _pilaDatum.clear();
    }

    public void initCode() {
        _instrucProgramaIndex = 0;
        _pilaDatum.clear();
    }

    public int code(List<InstrucPrograma> $listInstruc) {
        int currentIndex = _instrucProgramaIndex;

        for (int i = 0; i < $listInstruc.size(); ++i) {
            _instrucPrograma[_instrucProgramaIndex++] = $listInstruc.get(i);
        }

        return currentIndex;
    }

    public void execute(
            int $index, JTextArea $outputTextArea, JTable $outputTable) {
        InstrucPrograma instruc;
        Datum operation1, operation2;
        Object os[] = new Object[5];
        String TipDatum = new String();
        String Val = new String();
        String NombSymbol = new String();
        String TypeSymbol = new String();
        String ValSymbol = new String();
        DefaultTableModel modeloOutputTable =
            (DefaultTableModel) $outputTable.getModel();

        _programCounter = $index;

        while (_instrucPrograma[_programCounter].Instruc != EnumInstrMaq.STOP) {
            TipDatum = "";
            Val = "";
            NombSymbol = "";
            TypeSymbol = "";
            ValSymbol = "";

            instruc = _instrucPrograma[_programCounter++];

            switch (instruc.Instruc) {
                case ADD:
                    operation2 = _pilaDatum.pop();
                    operation1 = _pilaDatum.pop();
                    operation1.val += operation2.val;
                    _pilaDatum.push(operation1);

                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);

                    os[0] = (Object) "Val";
                    os[1] = (Object) operation1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloOutputTable.addRow(os);

                    break;
                case ASSIGN:
                    operation2 = _pilaDatum.pop();
                    operation1 = _pilaDatum.pop();
                    operation2.symb.setValue(operation1.val);
                    operation2.symb.setTipoSymbol(EnumTipoSymbol.VAR);
                    _pilaDatum.push(operation1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    os[0] = (Object) "Val";
                    os[1] = (Object) operation1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloOutputTable.addRow(os);
                    break;
                case BLTIN:
                    instruc = _instrucPrograma[_programCounter++];

                    switch (instruc.Func_BLTIN) {
                        case ABS:
                            operation1 = _pilaDatum.pop();
                            operation1.val = Math.abs(operation1.val);
                            _pilaDatum.push(operation1);
                            modeloOutputTable.removeRow(
                                    modeloOutputTable.getRowCount() - 1);
                            os[0] = (Object) "Val";
                            os[1] = (Object) operation1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloOutputTable.addRow(os);
                            break;
                        case ATAN:
                            operation1 = _pilaDatum.pop();
                            operation1.val =
                                    (float) Math.atan((double) operation1.val);
                            _pilaDatum.push(operation1);
                            modeloOutputTable.removeRow(
                                    modeloOutputTable.getRowCount() - 1);
                            os[0] = (Object) "Val";
                            os[1] = (Object) operation1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloOutputTable.addRow(os);
                            break;
                        case COS:
                            operation1 = _pilaDatum.pop();
                            operation1.val =
                                    (float) Math.cos((double) operation1.val);
                            _pilaDatum.push(operation1);
                            modeloOutputTable.removeRow(
                                    modeloOutputTable.getRowCount() - 1);
                            os[0] = (Object) "Val";
                            os[1] = (Object) operation1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloOutputTable.addRow(os);
                            break;
                        case EXP:
                            operation1 = _pilaDatum.pop();
                            operation1.val =
                                    (float) Math.exp((double) operation1.val);
                            _pilaDatum.push(operation1);
                            modeloOutputTable.removeRow(
                                    modeloOutputTable.getRowCount() - 1);
                            os[0] = (Object) "Val";
                            os[1] = (Object) operation1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloOutputTable.addRow(os);
                            break;
                        case INTEGER:
                            operation1 = _pilaDatum.pop();
                            operation1.val =
                                    (float) Math.floor((double) operation1.val);
                            _pilaDatum.push(operation1);
                            modeloOutputTable.removeRow(
                                    modeloOutputTable.getRowCount() - 1);
                            os[0] = (Object) "Val";
                            os[1] = (Object) operation1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloOutputTable.addRow(os);
                            break;
                        case LO10:
                            operation1 = _pilaDatum.pop();
                            operation1.val =
                                    (float) Math.log10((double) operation1.val);
                            _pilaDatum.push(operation1);
                            modeloOutputTable.removeRow(
                                    modeloOutputTable.getRowCount() - 1);
                            os[0] = (Object) "Val";
                            os[1] = (Object) operation1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloOutputTable.addRow(os);
                            break;
                        case LOG:
                            operation1 = _pilaDatum.pop();
                            operation1.val =
                                    (float) Math.log((double) operation1.val);
                            _pilaDatum.push(operation1);
                            modeloOutputTable.removeRow(
                                    modeloOutputTable.getRowCount() - 1);
                            os[0] = (Object) "Val";
                            os[1] = (Object) operation1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloOutputTable.addRow(os);
                            break;
                        case SIN:
                            operation1 = _pilaDatum.pop();
                            operation1.val =
                                    (float) Math.sin((double) operation1.val);
                            _pilaDatum.push(operation1);
                            modeloOutputTable.removeRow(
                                    modeloOutputTable.getRowCount() - 1);
                            os[0] = (Object) "Val";
                            os[1] = (Object) operation1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloOutputTable.addRow(os);
                            break;
                        case SQRT:
                            operation1 = _pilaDatum.pop();
                            operation1.val = 
                                    (float) Math.sqrt((double) operation1.val);
                            _pilaDatum.push(operation1);
                            modeloOutputTable.removeRow(
                                    modeloOutputTable.getRowCount() - 1);
                            os[0] = (Object) "Val";
                            os[1] = (Object) operation1.val;
                            os[2] = "";
                            os[3] = "";
                            os[4] = "";
                            modeloOutputTable.addRow(os);
                            break;
                        default:
                            break;
                    }

                    break;
                case CONSTPUSH:
                    operation1 = new Datum();
                    operation1.val = _instrucPrograma[_programCounter++]
                                        .symbolHoc.getValue();
                    _pilaDatum.push(operation1);

                    os[0] = (Object) "Val";
                    os[1] = (Object) operation1.val;
                    os[2] = (Object) "";
                    os[3] = (Object) "";
                    os[4] = (Object) "";
                    modeloOutputTable.addRow(os);
                    $outputTable.repaint();
                    break;
                case DIV:
                    operation2 = _pilaDatum.pop();
                    operation1 = _pilaDatum.pop();
                    operation1.val /= operation2.val;
                    _pilaDatum.push(operation1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    os[0] = (Object) "Val";
                    os[1] = (Object) operation1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloOutputTable.addRow(os);
                    break;
                case EVAL:
                    operation2 = new Datum();
                    operation1 = _pilaDatum.pop();
                    operation2.val = operation1.symb.getValue();
                    _pilaDatum.push(operation2);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    os[0] = (Object) "Val";
                    os[1] = (Object) operation2.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloOutputTable.addRow(os);
                    break;
                case MUL:
                    operation2 = _pilaDatum.pop();
                    operation1 = _pilaDatum.pop();
                    operation1.val *= operation2.val;
                    _pilaDatum.push(operation1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    os[0] = (Object) "Val";
                    os[1] = (Object) operation1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloOutputTable.addRow(os);
                    break;
                case NEGATE:
                    operation1 = _pilaDatum.pop();
                    operation1.val = -operation1.val;
                    _pilaDatum.push(operation1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    os[0] = (Object) "Val";
                    os[1] = (Object) operation1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloOutputTable.addRow(os);
                    break;
                case POWER:
                    operation2 = _pilaDatum.pop();
                    operation1 = _pilaDatum.pop();
                    operation1.val =
                            (float) Math.pow(
                                    (double)operation1.val,
                                    (double)operation2.val);
                    _pilaDatum.push(operation1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getRowCount() - 1);
                    os[0] = (Object) "Val";
                    os[1] = (Object) operation1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloOutputTable.addRow(os);
                    break;
                case PRINT:
                    String CadResult = new String();
                    operation1 = _pilaDatum.pop();
                    CadResult = Float.toString(operation1.val) + "\n";
                    $outputTextArea.append(CadResult);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getColumnCount() - 1);
                    break;
                case STOP:
                    break;
                case SUB:
                    operation2 = _pilaDatum.pop();
                    operation1 = _pilaDatum.pop();
                    operation1.val -= operation2.val;
                    _pilaDatum.push(operation1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getColumnCount() - 1);
                    modeloOutputTable.removeRow(
                            modeloOutputTable.getColumnCount() - 1);
                    os[0] = (Object) "Val";
                    os[1] = (Object) operation1.val;
                    os[2] = "";
                    os[3] = "";
                    os[4] = "";
                    modeloOutputTable.addRow(os);
                    break;
                case VARPUSH:
                    operation1 = new Datum();
                    operation1.symb = _instrucPrograma[_programCounter++]
                                        .symbolHoc;
                    _pilaDatum.push(operation1);
                    os[0] = (Object) "SYMBOL";
                    os[1] = (Object) "";
                    os[2] = operation1.symb.getName();
                    os[3] = operation1.symb.getTipoSymbol();
                    os[4] = operation1.symb.getValue();
                    modeloOutputTable.addRow(os);
                    break;
                default:
                    break;
            }
        }
    }

    private TablaSimbolos _tablaSimbolos;
    private Stack<Datum> _pilaDatum;
    // TODO(Garz4): Stop hardcoding numbers like the following.
    private int _instrucProgramaIndex = 0;
    private InstrucPrograma _instrucPrograma[];
    private int _programCounter;
}
