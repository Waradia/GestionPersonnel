package fichiers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mission.Mission;

/**
 *
 * @author selbe
 */
public class FileCsvMissions extends FileCsv{

    /**
     *
     */
    public List<String> data;
	
    /**
     *
     * @param FILENAME
     */
    public FileCsvMissions(String FILENAME) {
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
     * @param Mission
     */
    public void MAJ(Map<Integer, Mission> Mission){
        
		data.clear();	
		data.add(firstLine+'\n');
                
		for (Map.Entry<Integer, Mission> entry : Mission.entrySet())
                    data.add(entry.getValue().toString());	
		
		try {
			super.updateFile(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}