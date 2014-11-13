/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n.queens.problem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import n.queens.problem.Pagalba;
/**
 *
 * @author Justinas Ruika
 */
public class NQueensProblem {

    public static ArrayList<Busena> medis = new ArrayList<Busena>();
    public static final int GYLIO_RIBA = 4;    

    public static void main(String[] args) throws IOException { 
               
      int atsakymas = paieskaGilyn(GYLIO_RIBA);
      
      israsyti(medis,atsakymas);
      
      System.in.read();
    }
    
    public static int paieskaGilyn( int gylio_riba ) throws IOException {       
        medis.add( pradineBusena() );
        int n = 0;
        while( n != -1 )  {
            if( yraTikslas(medis.get(n)) ) {
                return n;
            }
            if( medis.get(n).gylis < gylio_riba ) {
                ArrayList<Busena> nauji_vaikai = isskleisti( medis.get(n) );
                irasytiEilen(medis,nauji_vaikai);
            } else {
                medis.get(n).skleistas = true;
            }
            
            n = raskPaskutiniNeskleistaMazga(medis);
            
        }
        
        return -1;
    }
    
    public static Busena pradineBusena() {
       int[] stulpeliai = { 0,1,2,3,4,5,6,7,8,9 };
       Busena pradine = new Busena();
       Pagalba.maisytiMasyva(stulpeliai);
       
       pradine.lenta =  Arrays.copyOf(stulpeliai, stulpeliai.length);
       
       return pradine;
    }
    
    public static boolean yraTikslas( Busena busena ) {
        
        for (int i = 0; i < busena.lenta.length; i++) {
            for( int j = i+1; j < busena.lenta.length; j++) {
                // Kerta įstrizai
                if( Math.abs(i - j) == Math.abs( busena.lenta[i] - busena.lenta[j]) ) {
                    return false;
                }
                // Kerta horizontaliai
                if( busena.lenta[i] == busena.lenta[j] ) {
                    return false;
                }
                // Kerta kaip zirgas
                if( (Math.abs(i - j) == 2 && Math.abs(busena.lenta[i] - busena.lenta[j]) == 1) ||
                    (Math.abs(i - j) == 1 && Math.abs(busena.lenta[i] - busena.lenta[j]) == 2)   
                        ) {
                     return false;
                }
            }
        }
        
        return true;
    }
    
    public static ArrayList<Busena> isskleisti( Busena busena) throws IOException {
        busena.skleistas = true;
        int senas_gylis = busena.gylis;
        int naujas_gylis = busena.gylis + 1;
        ArrayList<Busena> naujos_busenos = new ArrayList<Busena>();

        for (int i = senas_gylis; i < busena.lenta.length -1; i++) {  
            for (int j = i+1; j < busena.lenta.length; j++) {
                Busena naujaBusena = new Busena();
                naujaBusena.gylis = naujas_gylis;
                naujaBusena.tevas = busena;
                        
                int[] dabartine_lenta = Arrays.copyOf( busena.lenta, busena.lenta.length );
                int karaliene1 =  dabartine_lenta[i];
                int karaliene2 =  dabartine_lenta[j];
                dabartine_lenta[j] = karaliene1;
                dabartine_lenta[i] = karaliene2;
                
                naujaBusena.lenta =  Arrays.copyOf(dabartine_lenta, dabartine_lenta.length); ; 
                
                if( !medis.contains(naujaBusena) ) {
                    naujos_busenos.add( naujaBusena.clone() );
                }
                
            }    
        }
        
        return naujos_busenos;
    }
    
    public static void irasytiEilen( ArrayList<Busena> medis,ArrayList<Busena> nauji_vaikai ) {
        if( nauji_vaikai.size() == 0 )
            return;
        
        for (Busena busena : nauji_vaikai) {
            medis.add(busena);
        }
    }

    public static int raskPaskutiniNeskleistaMazga( ArrayList<Busena> medis ) {
        for (int j = medis.size() - 1; j >= 0; j--) {
            if( !medis.get(j).skleistas ) {
                return j;
            }
        }
        
        return -1;
    }
    
    
    public static void israsyti(ArrayList<Busena> medis,int atsakymas) {
        
        if( atsakymas == -1 ) {
            System.out.print( "Atsakymas nerastas!" );
            return;
        }
        
        Busena atsakymoBusena = medis.get(atsakymas);
        
        while( atsakymoBusena.tevas != null ) {
            piestiLenta(atsakymoBusena);
            atsakymoBusena =  atsakymoBusena.tevas;
        }
        piestiLenta(atsakymoBusena);
        
    }
    
    public static void piestiLenta( Busena atsakymoBusena ) {
        
         for (int i = 0; i < atsakymoBusena.lenta.length; i++) {
             System.out.print( atsakymoBusena.lenta[i] + "|" );
         }
        System.out.print( " Gylis: " + atsakymoBusena.gylis );
        System.out.println();
        
        for (int i = 0; i < atsakymoBusena.lenta.length; i++) {
            
            
            
            for (int j = 0; j < atsakymoBusena.lenta.length; j++) {
                System.out.print("|");
                if( atsakymoBusena.lenta[j] == i )
                    System.out.print("*");
                else
                    System.out.print("_");
                
                System.out.print("|"); 
            }
            System.out.println(); 
        }
        
        System.out.println(); 
    }
    
}
