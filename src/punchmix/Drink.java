/**
 * 
 */
package punchmix;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

import static side.Cheker.*;

/**
 * @author ojjranss
 * @version 22 Feb 2022
 *
 */
public class Drink {
    
    private int idd;
    private String name = "";
    private double price = 0;
    private double volTotal= 0;
    private double eurLitter = 0;
    private double vols = 0;
    
    private static int nextNum = 1;
    
    /**
     * Tulostaa Drinking tiedot
     * @param out tietovirata mihin tulostetaan
     */
    public void print(PrintStream out) {
        out.println(String.format("idd = %04d", idd) + " " + name);
        out.println("Price " + String.format("%4.2f", price) + " Total Volume " + String.format("%4.2f", volTotal));
        out.println("Euro per Litter " + String.format("%4.2f", eurLitter));
        out.println("Vols % " + String.format("%4.2f", vols)+ "\n");
    }
    
    public void print(OutputStream os) {
    	print(new PrintStream(os));
    }
    
    
    /**
     * @return Drinks name
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * adds some parameters for drink
     */
    public void someMix() {
        name = "Mix " + rand(99,9999);
        price = 10;
        volTotal = 200;
        eurLitter = volTotal/price;
        vols = 15;
    }
    
    /**
     *  Antaa Drinkille idd numeron joka on ysilöivä
     * @return idd Numeber for drinks
     * <@example
     * <pre name="test">
     * Drink drink = new Drink();
     * drink.getIdd() === 0;
     * drink.recIdd();
     * Drink mix2 = new Drink();
     * mix2.recIdd();
     * int n1 = drink.getIdd();
     * int n2 = mix2.getIdd();
     * n1 === n2-1;
     * </pre>
     */
    public int recIdd() {
        idd = nextNum;
        nextNum++;
        return idd;
    }
    
    
    /**
     * @return Drinks id
     */
    public int getIdd() {
        return idd;
    }
    
    private void setIdd(int num) {
        idd = num;
        if (idd >= nextNum) nextNum = idd + 1; 
    }
    
    @Override
    public String toString() {
        return"" +
                getIdd() + "|" +
                name + "|" +
                price + "|" +
                volTotal + "|" +
                eurLitter + "|" +
                vols;
    }
    
    public void parse(String row) {
        StringBuffer sb = new StringBuffer();
        setIdd(Mjonot.erota(sb, '|', getIdd()));
        name = Mjonot.erota(sb, '|', name);
        price = Mjonot.erota(sb, '|', price);
        volTotal = Mjonot.erota(sb, '|', volTotal);
        eurLitter = Mjonot.erota(sb, '|', eurLitter);
        vols = Mjonot.erota(sb, '|', vols);
    }
    
    
    @Override
    public boolean equals(Object drink) {
        if ( drink == null ) return false;
        return this.toString().equals(drink.toString());
    }
    
    @Override
    public int hashCode() {
        return idd;
    }
    
    /**
     * @param args Not in use
     */
    public static void main(String[] args) {
        Drink drink = new Drink();
        
        drink.recIdd();
        drink.print(System.out);
        
        drink.someMix();
        drink.print(System.out);

    }

}
 