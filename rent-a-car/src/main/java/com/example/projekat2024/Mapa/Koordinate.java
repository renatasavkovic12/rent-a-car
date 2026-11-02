package com.example.projekat2024.Mapa;
import java.io.Serializable;

/**
 * Klasa koja predstavlja KOORDINATE PUTA
 * @author Renata Savkovic
 * @version 1.0
 */
public class Koordinate implements Serializable{
    /**
     * koordinata po x putanji u mapi
     */
    public int i;
    /**
     * koordinata po y putanji u mapi
     */
    public int j;
    public Koordinate(){}
    public Koordinate(int i, int j)
    {
        this.i = i;
        this.j = j;
    }
    /**
     * Override metode toString() koja pretvara informacije o klasi Koordinate u String
     */
    @Override
    public String toString()
    {
        return " x: "+ i + "y: " + j;
    }
    /**
     * Override metode equals() koja poredi dva objekta klase Koordinate
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
        {
            return true;
        }
        if(!(obj instanceof Koordinate k))
        {
            return false;
        }
        return k.i == this.i && k.j == this.j;
    }
}
