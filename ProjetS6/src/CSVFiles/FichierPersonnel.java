package CSVFiles;

import Classes.Personne;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Diawara
 */
public class FichierPersonnel extends FileCsv{

    /**
     *
     */
    public ArrayList<String> data;
	
    /**
     * Le constructeur d'un fihier personnel
     * @param FILENAME
     */
    public FichierPersonnel(String FILENAME) {
		super(FILENAME);
		data = new ArrayList<>();
		try {
			data = super.Lecture();
		} catch (IOException e) {
			// TODO Auto-generated catch block
                            System.out.println(e.toString());
		}
                data.remove(0);
	}
	
    /**
     * Cette fonction permet d'ecrire la liste du personnel dans un le fichier
     * @param personnel
     */
    public void MAJ(ArrayList<Personne> personnel){
            
		data.clear();
		data.add(premiereLigne+'\n');
		
		for (Personne p: personnel)
                {
                     data.add(p.toString());
                }   
		try 
                {
			super.Ecrire(data);
                } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
   
	public void MAJ(Personne p){
            
		
                data.add(p.toString()); 
		try 
                {
			super.Ecrire(p.toString());
                } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
}
