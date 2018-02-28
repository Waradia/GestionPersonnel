package CSVFiles;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Diawara
 */
public class FileCsv {

    protected final static char SEPARATEUR = ';';
    private File fichier;
    protected String premiereLigne;
	
    /**
     * Le constructeur d'un fichier CSV
     * @param fileName
     */
    public FileCsv(String fileName)
    {
        
        this.fichier = new File(fileName);
    }
	
    /**
     * La Lecture d'un fichier CSV
     * @return
     * @throws IOException
     */
    public ArrayList<String> Lecture() throws IOException{
		ArrayList<String> result;
                result = new ArrayList<>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fichier);
			br = new BufferedReader(fr);
			
		    for (String line = br.readLine(); line != null; line = br.readLine()) {
		        if (!line.isEmpty())
		        	result.add(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
                            System.out.println(e.toString());
		} 
		
		/**
                 * On garde la première ligne de la liste 
                 */
		this.premiereLigne = result.get(0);
                
		return result; 
	}

    /**
     *
     * @param donnees
     * @throws IOException
     */
    public void Ecrire(ArrayList<String> donnees) throws IOException{
		
		FileWriter fr = null;
		BufferedWriter br = null;
		
		try {
			fr = new FileWriter(this.fichier,false);
			br = new BufferedWriter(fr);
			
		    for (String line : donnees) 
		        br.write(line);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
                        System.out.println(e.toString());
		}
	}
	public void Ecrire(String ligne) throws IOException{
		
		FileWriter fr = null;
		BufferedWriter br = null;
		
		try {
			fr = new FileWriter(this.fichier,false);
			br = new BufferedWriter(fr);
		        br.write(ligne);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
                        System.out.println(e.toString());
		}
	}
    /**
     *
     * @return
     */
    public char getSeparateur(){
	return FileCsv.SEPARATEUR;
    }
    public static int nombreLigne() throws IOException
    {
        int cpt=0;
        
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("C:\\Users\\diawara\\Desktop\\CSV\\liste_personnel.csv");
			br = new BufferedReader(fr);
			
		    for (String line = br.readLine(); line != null; line = br.readLine()) {
		        if (!line.isEmpty())
		        	cpt++;
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
                            System.out.println(e.toString());
		} 
        return cpt;
		  
    }
}
