/**
 * 
 */
package punchmix;

import java.io.*;
import static side.Cheker.*;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author ojjranss
 * @version 25 Feb 2022
 *
 */
public class Mix {
    
    private int mixId;
    private int idd;
    private int idi;
    private int amount;
    
    private static int nextNum = 1;

    
    public Mix() {
        //todo 
    }
        
    /**
     * @param idd Drink id 
     */
    public Mix(int idd) {
        this.idd = idd;
    }
    /**
     * @return mixin oma id
     */
    public int getMixId() {
        return mixId;
    }
   
    /**
     * @return Drinkin id tunniste
     */
    public int getIdd() {
        return idd;
    }
    
    /**
     * @return Aines osan id tunniste
     */
    public int getIdi() {
        return idi;
    }

    /**
     * @return
     */
    public int getAmount() {
        return amount;
    }
    
    private void setMixId(int num) {
        mixId = num;
        if( mixId >= nextNum) nextNum = mixId + 1;
    }
    
    /**
     * @param out
     */
    public void print(PrintStream out) {
        out.println(idd + " " + idi + " " + amount);
    }
    

    /**
     * @param os
     */
    public void print(OutputStream os) {
        print(new PrintStream(os));
    }
    
    /**
     * @return Add mixId
     */
    public int recId() {
        mixId = nextNum;
        nextNum++;
        return mixId;
    }
    
    /**
     * @param nro
     */
    public void someMix(int nro) {
        idd = nro;
        idi = rand(0, 99);
        amount = rand(9,300);
    }
    
    
    @Override
    public String toString() {
        return "" + getMixId() + "|" + idd + "|" + idi + "|" + amount;
    }
    
    public void parse(String row) {
        StringBuffer sb = new StringBuffer(row);
        setMixId(Mjonot.erota(sb, '|', getMixId()));
        idd = Mjonot.erota(sb, '|', idd);
        idi = Mjonot.erota(sb, '|', idi);
        amount = Mjonot.erota(sb, '|', amount);
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if ( obj == null) return false;
        return this.toString().equals(obj.toString());
    }
    
    @Override
    public int hashCode() {
        return mixId;
    }
    
    /**
     * @param args Not in use
     */
    public static void main(String[] args) {
        Mix mix = new Mix();
        mix.someMix(1);
        mix.print(System.out);
    }

}
