using System.Collections.Generic;

namespace LexicAnalyzer
{
    class Estado
    {
        static int generalId = 0;
        int id;
        bool aceptacion;
        int token;
        Dictionary<char, Estado> transiciones;

        //===========================

        public void setAceptacion(bool toSet) { this.aceptacion = toSet; }
        public void setToken(int toSet) { this.token = toSet; }
        public void addTransicion(char toChar, Estado toEstado) { transiciones.Add(toChar, toEstado); }

        public int getId() { return id; }
        public bool getAceptacion() { return aceptacion; }
        public Dictionary<char, Estado> getTransiciones() { return transiciones; }

        public Estado()
        {
            this.id = generalId;
            generalId++;
            this.aceptacion = false;
            this.transiciones = new Dictionary<char, Estado>();
            this.transiciones.Clear();
        }
    }
}
