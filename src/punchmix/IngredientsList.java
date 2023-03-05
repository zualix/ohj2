package punchmix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import punchmix.DrinkList.DrinkListIterator;
import side.SailoException;

/**
 * @author ojjranss
 * @version 25 Feb 2022
 *
 */
public class IngredientsList implements Iterable<Ingredient> {
    private static final int MAX_INGREDIENTS = 10;
    private boolean chanced = false;
    private int lkm = 0;
    private String fileFullName = "";
    private String fileName = "ingredients";
    private Ingredient item[] = new Ingredient[MAX_INGREDIENTS];
    
    
    /**
     * @param ingredient
     * @throws SailoException
     */
    public void addMix(Ingredient ingredient) throws SailoException {
        if (lkm >= item.length) throw new SailoException("Too many items!");
        item[lkm] = ingredient;
        lkm++;
    }
    
    
    /**
     * @param i
     * @return
     * @throws IndexOutOfBoundsException
     */
    public Ingredient give(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Index no awaileble: " + i);
        return item[i];
    }
    
    /**
     * @param file 
     * @throws SailoException
     */
    public void readFromFile(String file) throws SailoException {
        setFileName(file);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getFileName()))) {
            fileFullName = fi.readLine();
            if ( fileFullName == null ) throw new SailoException("Drink list not found");
            String row = fi.readLine();
            if ( row == null ) throw new SailoException("No MAX size");
            
            while (( row = fi.readLine()) != null ) {
                row = row.trim();
                if ("".equals(row) || row.charAt(0) == ';') continue;
                Ingredient ing= new Ingredient();
                ing.parse(row);
                addMix(ing);
            }
            chanced = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("File " + getFileName() + " not opening");
        } catch ( IOException e) {
            throw new SailoException("Problem with file " + e.getMessage());        
        }
    }
    
    /**
     * @throws SailoException
     */
    public void readFromFile() throws SailoException {
        readFromFile(getFileName());
    }
    
    /**
     * @throws SailoException
     */
    public void save() throws SailoException {
        if (!chanced) return;
        
        File fbak = new File(getBakName());
        File ffile = new File(getFile());
        fbak.delete();
        ffile.renameTo(fbak);
        
        try ( PrintWriter fo = new PrintWriter(new FileWriter(ffile.getCanonicalPath()))){
            fo.println(getFullName());
            fo.println(item.length);
            for (Ingredient ingredient : this) {
                fo.println(ingredient.toString());
            }
            
        }catch ( FileNotFoundException ex ) {
            throw new SailoException("File " + ffile.getName() + " doen't open");
        } catch ( IOException ex ) {
            throw new SailoException("File " + ffile.getName() + " writting problem");
        }

        chanced = false;
    }
    
    /**
     * @return
     */
    public String getFullName() {
        return fileFullName;
    }
    
    /**
     * @return kuinka monta ainettta on tiedostossa
     */
    public int getLkm() {
        return lkm;
    }
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String name) {
        fileName = name;
    }
    
    public String getFile() {
        return getFileName() + ".dat" ;
    }
    
    public String getBakName() {
        return fileName + ".bak";
    }
    
    public class IngredietsListIterator implements Iterator<Ingredient> {
        private int point = 0;
        
        @Override
        public boolean hasNext() {
            return point < getLkm();
        }
        
        @Override
        public Ingredient next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("There is nothing");
            return give(point++);
        }
        
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Don't delete");
        }

    }

    @Override
    public Iterator<Ingredient> iterator() {
        return new IngredietsListIterator();
    }
    
    public Collection<Ingredient> find(String searchCrit, int i) {
        Collection<Ingredient> found = new ArrayList<Ingredient>();
        for (Ingredient ing: this) {
            found.add(ing);
        }
        return found;
    }
    
    /**
     * @param args Not in use
     */
    public static void main(String[] args) {
        IngredientsList ings = new IngredientsList();
        
        Ingredient ing1 = new Ingredient();
        ing1.recIdi();
        ing1.someIng();
        
        try {
            ings.addMix(ing1);
            
            System.out.println("================= Ingredients Test =================");
        
            for (int i = 0; i < ings.getLkm(); i++) {
                Ingredient ing = ings.give(i);
                System.out.println("Ingredient idi: " + i);
                ing.print(System.out);
            }

        } catch ( SailoException ex ) {
            System.out.println(ex.getMessage());
        }
    }

}
