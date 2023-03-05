package punchmix;

import java.io.*;
import java.util.*;

import side.SailoException;

public class MixList implements  Iterable<Mix> {
   
    private boolean chanced = false;
    private String fileName = "";
    
    private final Collection<Mix> items = new ArrayList<Mix>();
    
    
    public void Mixes() {
        //TODO
    }
    
    public void addMix(Mix mix) {
        items.add(mix);
        chanced = true;
    }
    
    public void readFromFile(String file) throws SailoException {
        setFileBaseName(file);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getFileName()))) {
            
            String row;
            while (( row = fi.readLine()) != null ) {
                row = row.trim();
                if ("".equals(row) || row.charAt(0) == ';') continue;
                Mix mix = new Mix();
                mix.parse(row);
                addMix(mix);
            }
            chanced = false;
            
        }catch ( FileNotFoundException e ) {
            throw new SailoException("File " + getFileName() + " not opening");
        } catch ( IOException e ) {
            throw new SailoException("Problem wiht the file: " + e.getMessage());
        }
    }
    
    public void readFromFile() throws SailoException {
        readFromFile(getFileBaseName());
    }
    
    public void save() throws SailoException {
        if (!chanced) return;
        
        File fbak = new File(getBakName());
        File ffile = new File(getFileName());
        fbak.delete();
        ffile.renameTo(fbak);
        
        try(PrintWriter fo = new PrintWriter(new FileWriter(ffile.getCanonicalPath()))) {
            for( Mix mix : this) {
                fo.println(mix.toString());
            }
            
        }catch ( FileNotFoundException ex ) {
            throw new SailoException("File " + ffile.getName() + " doen't open");
        } catch ( IOException ex ) {
            throw new SailoException("File " + ffile.getName() + " writting problem");
        }
        chanced = false;
    }
    
    public int getLkm() {
        return items.size();
    }
    
    public void setFileBaseName(String file) {
        fileName = file;
    }
    
    public String getFileBaseName() {
        return fileName;
    }
    
    public String getFileName() {
        return fileName + ".dat";
    }
    
    public String getBakName() {
        return fileName + ".bak";
    }
    
    @Override
    public Iterator<Mix> iterator() {
        return items.iterator();
    }
    
    /**
     * @param id
     * @return
     */
    public List<Mix> giveMixes(int idd) {
        List<Mix> found = new ArrayList<Mix>();
        for (Mix mix : items)
            if (mix.getIdd() == idd) found.add(mix);
        return found;
    }
    
    
    public static void main(String[] args) {
        MixList mixes = new MixList();
        
        Mix mix1 = new Mix();
        mix1.someMix(2);
        Mix mix2 = new Mix();
        mix2.someMix(1);
        Mix mix3 = new Mix();
        mix3.someMix(2);
        Mix mix4 = new Mix();
        mix4.someMix(2);
        
        mixes.addMix(mix1);
        mixes.addMix(mix2);
        mixes.addMix(mix3);
        mixes.addMix(mix4);

        System.out.println("============= Mix Test =================");

        List<Mix> mixes2 = mixes.giveMixes(2);
        
        for (Mix mixi : mixes2) {
            System.out.println(mixi.getIdd() + " ");
            mixi.print(System.out);
        }
    }
}
