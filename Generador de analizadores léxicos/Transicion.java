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
 
 public class Transicion {
    char simbolo;
    Estado estado;
    
    public Transicion(){
        this.simbolo = 0;
        this.estado = new Estado();
    }
    
    public Transicion(char sim, Estado E){
        this.simbolo = sim;
        this.estado = E;
    }
    
    public void setEstado(Estado toSet){ this.estado = toSet; }
    public void setSimbolo(char toSet){ this.simbolo = toSet; }
    public char getSimbolo(){ return this.simbolo; }
    public Estado getEstado(){ return this.estado; }
}
