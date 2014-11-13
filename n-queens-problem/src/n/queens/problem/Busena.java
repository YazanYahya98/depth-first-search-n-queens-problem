/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n.queens.problem;

import java.util.Arrays;

/**
 *
 * @author Justinas Ruika
 */
public class Busena implements Cloneable {
    public boolean skleistas;
    public int gylis;
    public int[] lenta = new int [4];
    public Busena tevas;
    
    public Busena() {
        for (int i = 0; i < this.lenta.length; i++) 
            this.lenta[i] = -1;
        
        this.tevas = null;
        this.skleistas = false;
        this.gylis = 0;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if (object != null && object instanceof Busena)
        {
            //sameSame = this.value == ((Busena) object).value;
            if ( Arrays.equals(this.lenta, ((Busena)object).lenta) )
            {
                return true;
            }
        }

        return false;
    }
    
    public Busena clone() {
        try {
            return (Busena) super.clone();
        } catch (CloneNotSupportedException e) {        
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
   
}
