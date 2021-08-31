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

class Estado {
    static int generalId = 1;
    HashSet<Transicion> transiciones;
    int id;
    int token;
    boolean aceptacion;

    //=========================

    int getId(){return this.id;}
    int getToken(){return this.token;}
    boolean getAceptacion(){return this.aceptacion;}
    HashSet<Transicion> getTransiciones(){return this.transiciones;}

    void setAceptacion(boolean toAceptacion){this.aceptacion = toAceptacion;}
    void setToken(int toToken){this.token = toToken;}
    void setId(int toId){ this.id = toId; }
    
    void addTransicion(char toChar, Estado toEstado){this.transiciones.add(new Transicion(toChar, toEstado));}

    Estado(){
        this.token = -1;
        this.id = generalId;
        this.aceptacion = false;
        this.transiciones = new HashSet<>();
        this.transiciones.clear();
        generalId++;
    }
}
