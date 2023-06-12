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

import side.SailoException;

/**
 * @author ojjranss
 * @version 25 Feb 2022
 *
 */
public class DrinkList implements Iterable<Drink> {
    private static final int MAX_DRINKS = 10;
    private boolean chanced = false;
    private int lkm = 0;
    private String fileFullName = "";
    private String fileBaseName = "drinks";
    private Drink item[] = new Drink[MAX_DRINKS];
    
    
    public DrinkList() {
        
    }
    
    
    /**
     * @param drink 
     * @throws SailoException 
     * @throws SailoExeption
     */
    public void addMix(Drink drink) throws SailoException {
        if (lkm >= item.length) throw new SailoException("Too many items!");
        item[lkm] = drink;
        lkm++;
        chanced = true;
    }
    
    
    /**
     * @param i
     * @return
     * @throws IndexOutOfBoundsException
     */
    public Drink give(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Index no awaileble: " + i);
        return item[i];
    }
    
    
    /**
     * @param file
     * @throws SailoException
     */
    public void readFromFile(String file) throws SailoException {
        setFileBaseName(file);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getFileBaseName()))) {
            fileFullName = fi.readLine();
            if ( fileFullName == null ) throw new SailoException("Drink list not found");
            String row = fi.readLine();
            if ( row == null ) throw new SailoException("No MAX size");
            
            while (( row = fi.readLine()) != null ) {
                row = row.trim();
                if ("".equals(row) || row.charAt(0) == ';') continue;
                Drink drink = new Drink();
                drink.parse(row);
                addMix(drink);
            }
            chanced = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("File " + getFileBaseName() + " not opening");
        } catch ( IOException e) {
            throw new SailoException("Problem with file " + e.getMessage());        
        }
    }
    
    /**
     * @throws SailoException
     */
    public void readFromFile() throws SailoException {
        readFromFile(getFileBaseName());
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
        
        try ( PrintWriter fo = new PrintWriter(new FileWriter(ffile.getCanonicalPath())) ){
            fo.println(getFullName());
            fo.println(item.length);
            for (Drink drink : this) {
                fo.println(drink.toString());
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
     * @return items on list
     */
    public int getLkm() {
        return lkm;
    }
    
    public String getFileBaseName() {
        return fileBaseName;
    }
    
    public void setFileBaseName(String name) {
        fileBaseName = name;
    }
    
    public String getFile() {
        return getFileBaseName() + ".dat" ;
    }
    
    public String getBakName() {
        return fileBaseName + ".bak";
    }
    
    
    public class DrinkListIterator implements Iterator<Drink> {
        private int point = 0;
        
        @Override
        public boolean hasNext() {
            return point < getLkm();
        }
        
        @Override
        public Drink next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("There is nothing");
            return give(point++);
        }
        
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Don't delete");
        }

        
    }
    @Override
    public Iterator<Drink> iterator() {
        return new DrinkListIterator();
    }
    
    public Collection<Drink> find(String searchCrit) {
        Collection<Drink> found = new ArrayList<Drink>();
        for (Drink drink : this) {
            found.add(drink);
        }
        return found;
    }
    
    
    
    
    /**
     * @param args
     */
    public static void main(String args[]) {
        DrinkList drinks = new DrinkList();

        Drink drink1 = new Drink(), drink2 = new Drink();
        drink1.recIdd();
        drink1.someMix();
        drink2.recIdd();
        drink2.someMix();

        try {
            drinks.addMix(drink1);
            drinks.addMix(drink2);

            System.out.println("================= Drinks Test=================");

            for (int i = 0; i < drinks.getLkm(); i++) {
                Drink drink = drinks.give(i);
                System.out.println("Drink idd: " + i );
                
                drink.print(System.out);
            }

        } catch ( SailoException ex ) {
            System.out.println(ex.getMessage());
        }
    }


}
