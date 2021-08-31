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

import java.util.HashSet;

public class AFD {
    Estado edoIni;
    HashSet<Estado> edosAceptacion;
    HashSet<Character> alfabeto;
    HashSet<Estado> edosAFN;
    
    //=========================
    
    AFD(){
        edosAceptacion = new HashSet<>();
        alfabeto = new HashSet<>();
        edosAFN = new HashSet<>();
    }

    void setEdoIni(Estado toSet){ this.edoIni = toSet; edosAFN.add(toSet); }
    void addEstado(Estado toAdd){ edosAFN.add(toAdd); }
    void addEdoAceptacion(Estado toSet){ edosAceptacion.add(toSet); }
    void setAlfabeto(HashSet<Character> toSet){ this.alfabeto = toSet; }

    Estado getEdoIni(){ return this.edoIni; }
    HashSet<Estado> getAllEstados(){ return this.edosAFN; }
}
