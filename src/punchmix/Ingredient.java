/**
 * 
 */
package punchmix;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

import static side.Cheker.*;

/**
 * @author ojjranss
 * @version 25 Feb 2022
 *
 */
public class Ingredient {

    private int idi;
    private String name = "";
    private double eurLitter = 0;
    private double vols = 0;
    
    private static int nextNum = 1;
    
    /**
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param out
     */
    public void print(PrintStream out) {
        out.println(String.format("idi = %04d", idi) + " " + name);
        out.println("Euro per Litter " + String.format("%4.2f", eurLitter));
        out.println("Prosentage " + String.format("%4.2f", vols) + "\n");    
    }
    
    /**
     *  Antaa ainesosalle idi numeron joka on ysilöivä
     * @return idd Numeber for drinks
     * <@example
     * <pre name="test">
     * Ingredient ing = new Ingredient();
     * ing.getIdi() === 0;
     * ing.recIdi();
     * Ingredient ing2 = new Ingredient();
     * ing2.recIdi();
     * int n1 = ing.getIdi();
     * int n2 = ing2.getIdi();
     * n1 === n2-1;
     * </pre>
     */
    public int recIdi() {
        this.idi = nextNum;
        nextNum++;
        return this.idi;
    }
    
    /**
     * @return
     */
    public int getIdi() {
        return idi;
    }
    
    private void setIdi(int num) {
        idi = num;
        if (idi >= nextNum) nextNum = idi + 1;
    }
    
    @Override
    public String toString() {
        return"" +
                getIdi() + "|" +
                name + "|" +
                eurLitter + "|" +
                vols;
    }
    
    public void parse(String row) {
        StringBuffer sb = new StringBuffer();
        setIdi(Mjonot.erota(sb, '|', getIdi()));
        name = Mjonot.erota(sb, '|', name);
        eurLitter = Mjonot.erota(sb, '|', eurLitter);
        vols = Mjonot.erota(sb, '|', vols);
    }
    
    @Override
    public boolean equals(Object ing) {
        if ( ing == null ) return false;
        return this.toString().equals(ing.toString());
    }
    
    @Override
    public int hashCode() {
        return idi;
    }
    
    
    /**
     * 
     */
    public void someIng() {
        name = "Ingredient " + rand(99,9999);
        vols = 40;
        eurLitter = 50;
        
    }
    
    
    /**
     * @param args Not in use
     */
    public static void main(String[] args) {
        Ingredient ing1 = new Ingredient();
        
        ing1.recIdi();
        ing1.print(System.out);
        
        ing1.someIng();
        ing1.print(System.out);
        
    }

}
