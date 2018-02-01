package fichiers;

import java.io.IOException;
import java.util.List;

import gestion.Employe;
import java.util.Map;

/**
 *
 * @author selbe
 */
public class FileCsvCompetencesPersonnel extends FileCsv{

	//static final String FILENAME = "fichiers/competences_personnel.csv";

    /**
     *
     */
	
	public List<String> data;
	
    /**
     *
     * @param FILENAME
     */
    public FileCsvCompetencesPersonnel(String FILENAME) {
		super(FILENAME);
                
                

		try {
			data = super.readFile();
		} catch (IOException e) {
                    // TODO Auto-generated catch block

		}
                data.remove(0);
	}
	
    /**
     *
     * @param personnelC
     */
    public void MAJ(Map<Integer,Employe> personnelC){
		data.clear();
		data.add(firstLine+'\n');
		
		for (Map.Entry<Integer, Employe> entry : personnelC.entrySet())
			data.add(entry.getValue().toStringComp());//here
		
		try {
			super.updateFile(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		
		}
	}
}

