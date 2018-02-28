package fichiers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gestion.Employe;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author selbe
 */
public class FileCsvPersonnel extends FileCsv{

    /**
     *
     */
    public List<String> data;
	
    /**
     *
     * @param FILENAME
     */
    public FileCsvPersonnel(String FILENAME) {
		super(FILENAME);
		data = new ArrayList<String>();

		try {
			data = super.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                data.remove(0);
	}
	
    /**
     *
     * @param personnel
     */
    public void MAJ(Map<Integer,Employe> personnel){
            
		data.clear();
		data.add(firstLine+'\n');
		
		for (Map.Entry<Integer, Employe> entry : personnel.entrySet())
                    data.add(entry.getValue().toString());
                    
		try {
			super.updateFile(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
