package hoc3;
import java_cup.runtime.*;
import java.io.Reader;

%% /* Inicia */
%class AnalizadorLexico
%line
%column
%char
%cup

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LetraMin = [a-z]
Digito = [0-9]
%%
[ \t]+                  { ; }
"\n"                    { return symbol(AnalizadorSintacticoSym.Enter); }
{Digito}+(\.{Digito}+)? { return symbol(AnalizadorSintacticoSym.NUM, new Float(yytext())); }
"="                     { return symbol(AnalizadorSintacticoSym.OpAsig); }
"/"                     { return symbol(AnalizadorSintacticoSym.OpDiv); }
"*"                     { return symbol(AnalizadorSintacticoSym.OpProd); }
"-"                     { return symbol(AnalizadorSintacticoSym.OpResta); }
"+"                     { return symbol(AnalizadorSintacticoSym.OpSuma); }
")"                     { return symbol(AnalizadorSintacticoSym.ParDer); }
"("                     { return symbol(AnalizadorSintacticoSym.ParIzq); }
"^"                     { return symbol(AnalizadorSintacticoSym.OpExp); }
"tan"                   { return symbol(AnalizadorSintacticoSym.TAN); }
"sen"                   { return symbol(AnalizadorSintacticoSym.SEN); }
"cos"                   { return symbol(AnalizadorSintacticoSym.COS); }
"sqrt"                  { return symbol(AnalizadorSintacticoSym.SQRT); }
"log"                   { return symbol(AnalizadorSintacticoSym.LOG); }
"log10"                 { return symbol(AnalizadorSintacticoSym.LOGG); }
"sec"                   { return symbol(AnalizadorSintacticoSym.SEC); }
"csc"                   { return symbol(AnalizadorSintacticoSym.CSC); }
"cot"                   { return symbol(AnalizadorSintacticoSym.COT); }
{LetraMin}              {int IndVar; IndVar = (int)(yytext().charAt(0)) - (int)'a'; return symbol(AnalizadorSintacticoSym.VAR, new Integer(IndVar)); }
.                       { return symbol(AnalizadorSintacticoSym.error); }
