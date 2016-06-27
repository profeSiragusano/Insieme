/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insieme2;

/**
 *
 * @author Carmelo
 */
import java.io.*;

/**
 *
 * @author Carmelo
 */
public class Insieme2 {
    private Boolean[] intervallo;
    private final int NUM_MAX = 101;
    
    public Insieme2(){
        intervallo = new Boolean[NUM_MAX];
        for(int i = 0; i< this.NUM_MAX; i++)
            this.intervallo[i]=false;
    }
    
    public Insieme2(Insieme2 aInsieme){
        intervallo = new Boolean[NUM_MAX];
        for(int i = 0; i< this.NUM_MAX; i++)
            this.intervallo[i]=aInsieme.intervallo[i];        
    }
    
    public Insieme2 unione(Insieme2 aInsieme){
        Insieme2 oRet = new Insieme2();
        for(int i=0; i<this.NUM_MAX; i++)
            oRet.intervallo[i]=this.intervallo[i]||aInsieme.intervallo[i];
        return oRet;
    }
    
    public Insieme2 intersezione(Insieme2 aInsieme){
        Insieme2 oRet = new Insieme2();
        for(int i=0; i<this.NUM_MAX; i++)
            oRet.intervallo[i]=this.intervallo[i]&&aInsieme.intervallo[i];
        return oRet;
    }
    
    public void inserisciElemento(int k){
        try{
            this.intervallo[k]=true;
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.print("Attenzione!");
        }
    }
    
public void eliminaElemento(int k){
        try{
            this.intervallo[k]=false;
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.print("Attenzione!");
        }
    }

public void salvaInsieme(){
    try{
        BufferedWriter scrivi = new BufferedWriter(new FileWriter("insieme.txt"));
        for (int i=0; i<NUM_MAX; i++ )
            if(intervallo[i]){
                scrivi.write(i);
                scrivi.newLine();
            }
        scrivi.close();
    }catch(IOException e){
        System.out.println("Errore di IO durante l'operazione di salvataggio");
    }
}

public void caricaInsieme(){
    try{
    BufferedReader leggi = new BufferedReader(new FileReader("insieme.txt"));
    String line;
    int elemento;
    while((line = leggi.readLine())!=null){
        elemento = Integer.parseInt(line);
        intervallo[elemento]=true;
    }
    }
    catch(IOException e){
        System.out.println("Errore nel caricamento dell'insieme");
    }
}

public int contaMultipli(int k, Insieme2 pippo)throws ElementoNonPresenteException{
    int risultato = 0;
    if (!intervallo[k])
        throw new ElementoNonPresenteException(k);
    for (int i= 0; i<NUM_MAX; i++){
        if (pippo.intervallo[i] && ( (i%k)==0) )
            risultato++;
    }
    return risultato;
}

public Coppia[] trovaMultipli(Insieme2 insieme){
    Coppia[] multipli = new Coppia[20000];
    int nuovo = 0;
    for (int i = 0; i< NUM_MAX; i++)
        for (int j = 0; j < NUM_MAX; j++)
            if(intervallo[i]&& insieme.intervallo[j])
                if (j%i == 0)
                    multipli[nuovo++]=new Coppia(i,j);
    Coppia[] risultato = new Coppia[nuovo];
    System.arraycopy(multipli, 0, risultato, 0, nuovo);
    return risultato;
}

public boolean equals(Insieme2 pluto){
    boolean risultato = true;
    for (int i = 0; i < NUM_MAX; i++)
        if (!intervallo[i].equals(pluto.intervallo[i])){
            risultato = false;
            break;
        }
     return risultato;       
}


    
    @Override
    public String toString(){
        int i;
        String sRet = "";
        for(i=0; i<this.NUM_MAX; i++)
            if(this.intervallo[i])
                sRet += i + " ";
        if (sRet.equals(""))
                return ("---");
        return sRet;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Insieme2 a = new Insieme2();
        Insieme2 b = new Insieme2();
        a.inserisciElemento(3);
        b.inserisciElemento(4);
        Insieme2 c = a.unione(b);
        System.out.println(c);
    }
    
}
