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

public class Estado {
    public Estado() {
        // TODO(Garz4): Avoid magic numbers like the following.
        _token = -1;
        _id = _generalId++;
        _aceptacion = false;
        _transiciones = new HashSet<>();
        _transiciones.clear();
    }

    public void addTransicion(char $simbolo, Estado $estado) {
        _transiciones.add(new Transicion($simbolo, $estado));
    }

    public int getId() { return _id; }
    public int getToken() { return _token; }
    public boolean getAceptacion() { return _aceptacion; }
    public HashSet<Transicion> getTransiciones() { return _transiciones; }

    public void setAceptacion(boolean $aceptacion) {
        _aceptacion = $aceptacion;
    }
    public void setToken(int $token) { _token = $token; }
    public void setId(int $id) { _id = $id; }

    private static int _generalId = 1;
    private HashSet<Transicion> _transiciones;
    private int _id;
    private int _token;
    private boolean _aceptacion;
}
