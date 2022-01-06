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
    public Transicion() {
        // TODO(Garz4): Avoid magic numbers like the following.
        _simbolo = 0;
        _estado = new Estado();
    }

    public Transicion(char $simbolo, Estado $estado){
        _simbolo = $simbolo;
        _estado = $estado;
    }

    public void setEstado(Estado $estado) { _estado = $estado; }
    public void setSimbolo(char $simbolo) { _simbolo = $simbolo; }
    public char getSimbolo() { return _simbolo; }
    public Estado getEstado() { return _estado; }

    private char _simbolo;
    private Estado _estado;
}
