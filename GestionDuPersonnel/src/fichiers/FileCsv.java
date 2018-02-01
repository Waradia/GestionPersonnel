package fichiers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author selbe
 */
public class FileCsv {
	
    /**
     *
     */
    protected final static char SEPARATOR = ';';
	private File file;

    /**
     *
     */
    protected String firstLine;
	
    /**
     *
     * @param csvName
     */
    public FileCsv(String csvName){
		this.file = new File(csvName);
	}
	
    /**
     *
     * @return
     * @throws IOException
     */
    public List<String> readFile() throws IOException{
		List<String> result = new ArrayList<String>();
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
		    for (String line = br.readLine(); line != null; line = br.readLine()) {
		        if (!line.isEmpty())
		        	result.add(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		} finally{
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		    if (fr != null) {
		        try {
		            fr.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		
		}
		
		//effacer premi�re valeur 
		this.firstLine = result.get(0);
                
		return result; 
	}

    /**
     *
     * @param data
     * @throws IOException
     */
    public void updateFile(List<String> data) throws IOException{
		
		FileWriter fr = null;
		BufferedWriter br = null;
		
		try {
			fr = new FileWriter(file,false);
			br = new BufferedWriter(fr);
			
		    for (String line : data) 
		        br.write(line);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		} finally{
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		    if (fr != null) {
		        try {
		            fr.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
	
    /**
     *
     * @return
     */
    public char separator(){
		return (SEPARATOR);
	}
}
