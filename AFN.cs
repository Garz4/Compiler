using System.Collections.Generic;
using System.Linq;

namespace LexicAnalyzer
{
    class AFN
    {
        Estado edoIni;
        HashSet<Estado> edosAceptacion;
        HashSet<char> alfabeto;
        HashSet<Estado> edosAFN;

        //===========================

        HashSet<char> getAlfabeto() { return this.alfabeto; }
        HashSet<Estado> getEdosAFN() { return this.edosAFN; }

        public AFN()
        {
            edoIni = null;
            alfabeto = new HashSet<char>();
            alfabeto.Clear();
            edosAceptacion = new HashSet<Estado>();
            edosAceptacion.Clear();
            edosAFN = new HashSet<Estado>();
            edosAFN.Clear();
        }

        public AFN(char s)
        {
            edoIni = new Estado();
            Estado ef = new Estado();
            ef.setAceptacion(true);
            edosAceptacion = new HashSet<Estado>();
            edosAceptacion.Clear();
            edosAceptacion.Add(ef);
            edosAFN = new HashSet<Estado>();
            edosAFN.Clear();
            edosAFN.Add(edoIni);
            edosAFN.Add(ef);
            alfabeto = new HashSet<char>();
            alfabeto.Clear();
            alfabeto.Add(s);
            edoIni.addTransicion(s, ef);
        }

        public AFN AFNBasico(char s)
        {
            Estado ef;
            edoIni = new Estado();
            ef = new Estado();
            ef.setAceptacion(true);
            edosAceptacion = new HashSet<Estado>();
            edosAceptacion.Clear();
            edosAceptacion.Add(ef);
            edosAFN = new HashSet<Estado>();
            edosAFN.Clear();
            edosAFN.Add(edoIni);
            edosAFN.Add(ef);
            alfabeto = new HashSet<char>();
            alfabeto.Clear();
            alfabeto.Add(s);
            edoIni.addTransicion(s, ef);
            return this;
        }

        AFN unirAFN(AFN f)
        {
            Estado e1 = new Estado();
            Estado e2 = new Estado();

            e1.addTransicion('&', this.edoIni);
            e1.addTransicion('&', f.edoIni);

            foreach (Estado e in this.edosAceptacion)
            {
                e.addTransicion('&', e2);
                e.setAceptacion(false);
            }

            foreach (Estado e in f.edosAceptacion)
            {
                e.addTransicion('&', e2);
                e.setAceptacion(false);
            }

            e2.setAceptacion(true);

            this.alfabeto.Union(f.getAlfabeto());
            this.edosAFN.Union(f.getEdosAFN());
            this.edosAFN.Add(e1);
            this.edosAFN.Add(e2);
            this.edosAceptacion.Clear();
            this.edosAceptacion.Add(e2);
            this.edoIni = e1;
            f = null;

            return this;
        }

        AFN concatenar(AFN f2)
        { //Thompson
            foreach (Estado e in this.edosAceptacion)
            {
                foreach (KeyValuePair<char, Estado> entry in f2.edoIni.getTransiciones())
                {
                    e.addTransicion(entry.Key, entry.Value);
                }
            }
            f2.edosAFN.Remove(f2.edoIni);
            foreach (Estado e in this.edosAceptacion)
            {
                e.setAceptacion(false);
            }
            this.edosAceptacion.Clear();
            foreach (Estado e in f2.edosAceptacion)
            {
                this.edosAceptacion.Add(e);
            }
            foreach (char a in f2.getAlfabeto())
            {
                this.alfabeto.Add(a);
            }
            foreach (Estado e in f2.edosAFN)
            {
                this.edosAFN.Add(e);
            }
            f2 = null;
            return this;
        }

        AFN CerrMas()
        {
            Estado NuevoIni = new Estado();
            Estado NuevoFin = new Estado();
            NuevoIni.addTransicion('&', this.edoIni);
            foreach (Estado e in this.edosAceptacion)
            {
                e.addTransicion('&', NuevoFin);
                e.addTransicion('&', this.edoIni);
                e.setAceptacion(false);
            }
            NuevoFin.setAceptacion(true);
            NuevoFin.setToken(10);
            this.edosAceptacion.Clear();
            this.edosAceptacion.Add(NuevoFin);
            this.edosAFN.Add(NuevoIni);
            this.edosAFN.Add(NuevoFin);
            this.edoIni = NuevoIni;
            return this;
        }

        AFN CerrEstrella()
        {
            Estado NuevoIni = new Estado();
            Estado NuevoFin = new Estado();
            NuevoIni.addTransicion('&', this.edoIni);
            foreach (Estado e in this.edosAceptacion)
            {
                e.addTransicion('&', NuevoFin);
                e.addTransicion('&', this.edoIni);
                e.setAceptacion(false);
            }
            NuevoFin.setAceptacion(true);
            NuevoFin.setToken(10);
            this.edosAceptacion.Clear();
            this.edosAceptacion.Add(NuevoFin);
            this.edosAFN.Add(NuevoIni);
            this.edosAFN.Add(NuevoFin);
            this.edoIni.addTransicion('&', NuevoFin);
            this.edoIni = NuevoIni;
            return this;
        }

        HashSet<Estado> Cerradura_e(Estado e)
        {
            Estado e2;
            HashSet<Estado> R = new HashSet<Estado>();
            R.Clear();
            Stack<Estado> P = new Stack<Estado>();
            P.Clear();
            P.Push(e);
            while (P.Count != 0)
            {
                e2 = P.Pop();
                if (R.Contains(e2))
                {
                    continue;
                }
                R.Add(e2);
                foreach (KeyValuePair<char, Estado> entry in e2.getTransiciones())
                {
                    if (entry.Key == '&')
                    {
                        if (!R.Contains(entry.Value))
                        {
                            P.Push(entry.Value);
                        }
                    }
                }
            }
            return R;
        }

        HashSet<Estado> Cerradura_e(HashSet<Estado> c)
        {
            HashSet<Estado> R = new HashSet<Estado>();
            R.Clear();
            foreach (Estado e in c)
                R.Union(Cerradura_e(e));
            return R;
        }
    }
}
