package fichiers;

import java.io.IOException;
import java.util.List;

import gestion.Competence;
import gestion.Employe;
import java.util.Map;

/**
 *
 * @author selbe
 */
public class FileCsvCompetences extends FileCsv{

	//static final String FILENAME = "fichiers/liste_competences.csv";

    /**
     *
     */
	
	public List<String> data;
		
    /**
     *
     * @param FILENAME
     */
    public FileCsvCompetences(String FILENAME) {
		super(FILENAME);

		try {
			data = super.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    /**
     *
     * @param competences
     */
    public void MAJ(Map<String, Competence> competences){
		data.clear();		
                
		for (Map.Entry<String, Competence> entry : competences.entrySet())
                    data.add(entry.getValue().toString());	
		
		try {
			super.updateFile(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
