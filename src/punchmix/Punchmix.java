/**
 * 
 */
package punchmix;

import java.io.File;
import java.util.*;

import side.SailoException;

/**
 * @author ojjranss
 * @version 22 Mar 2022
 *
 */
public class Punchmix {
    private DrinkList drinks = new DrinkList();
    private IngredientsList ings = new IngredientsList();
    private MixList mixes = new MixList();
    
    public int getDrinks() {
        return drinks.getLkm();
    }
    
    public int getIngredients() {
        return ings.getLkm();
    }
    
    public int delete(@SuppressWarnings("unused") int nro) {
        return 0;
    }
    
    public void addMix(Drink drink) throws SailoException {
        drinks.addMix(drink);
    }
    
    public void addMix(Ingredient ing) throws SailoException {
        ings.addMix(ing);
    }
    
    public void addMix(Mix mix) {
        mixes.addMix(mix);
    }
    
    /**
     * @param searhCrit
     * @param i
     * @return
     * @throws SailoException
     */
    public Collection<Drink> find(String searhCrit) throws SailoException {
        return drinks.find(searhCrit);
    }
    
    
    
    public Drink giveDrink(int i) {
        return drinks.give(i);
    }

    public Ingredient giveIng(int i) {
        return ings.give(i);
    }
    
    
    /**
     * @param drink
     * @return
     * @throws SailoException
     */
    public List<Mix> giveMixes(Drink drink) throws SailoException {
        return mixes.giveMixes(drink.getIdd());
    }
    
    public void setFile(String name) {
        File dir = new File(name);
        dir.mkdirs();
        String explorerName = "";
        if (!name.isEmpty() ) explorerName = name + "/";
        drinks.setFileName(explorerName + "drinks");
        mixes.setFileBaseName(explorerName + "mixes");
    }
    
    public void readFromFile(String name) throws SailoException {
        drinks = new DrinkList();
        mixes = new MixList();
        
        setFile(name);
        drinks.readFromFile(name);
        ings.readFromFile(name);
        mixes.readFromFile(name);
    }
    
    
    public void save() throws SailoException {
        String error = "";
        try {
            drinks.save();         
        } catch ( SailoException ex ) {
            error = ex.getMessage();
        }
        try {
            ings.save();
        } catch ( SailoException ex ) {
            error += ex.getMessage();
        }
        try {
            mixes.save();
        } catch ( SailoException ex ) {
            error += ex.getMessage();
        }
        if (!"".equals(error)) throw new SailoException(error);
    }
    
    
    /**
     * @param args Not in use
     */
    public static void main(String[] args) {
        Punchmix punchmix = new Punchmix();
        
        try {
            Drink drink1 = new Drink(), drink2 = new Drink();
            
            drink1.recIdd();
            drink2.recIdd();
            drink1.someMix();
            drink2.someMix();

            punchmix.addMix(drink1);
            punchmix.addMix(drink2);
            int id1 = drink1.getIdd();
            int id2 = drink2.getIdd();
            
            Mix mix11 = new Mix(id1); mix11.someMix(id1); punchmix.addMix(mix11);
            Mix mix12 = new Mix(id1); mix12.someMix(id1); punchmix.addMix(mix12);
            Mix mix21 = new Mix(id2); mix21.someMix(id1); punchmix.addMix(mix21);
            Mix mix22 = new Mix(id2); mix22.someMix(id1); punchmix.addMix(mix22);
            
            System.out.println("============= Punchmix Test =================");

            Collection<Drink> drinks = punchmix.find("", -1);
            int i = 0;
            for (Drink drink : drinks) {
                System.out.println("Drink from place: " + i);
                drink.print(System.out);
                List<Mix> found = punchmix.giveMixes(drink);
                for (Mix mix : found)
                    mix.print(System.out);
                i++;
            }
            
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

}
