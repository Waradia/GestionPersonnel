package gestion;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author selbe
 */
public class Employe {
	
	private int id;
	private String prenom;
	private String nom;
	private String dateEntree;
	
    /**
     *
     */
    public Map<String,Competence> competences;
	
    /**
     *
     * @param data
     */
    public Employe(String[] data){
		competences = new HashMap<String,Competence>();
		this.id = Integer.parseInt(data[3]);
		this.prenom = data[0];
		this.nom = data[1];
		this.dateEntree = data[2];
	}
	
    /**
     *
     * @param c
     */
    public void ajouterCompetence(Competence c){
		if(c != null)
			competences.put(c.getId(), c);
	}
	
	public String toString(){
		return (prenom+';'+nom+';'+dateEntree+';'+id+'\n');
	}
	
    /**
     *
     * @return
     */
    public String toStringComp(){
		String r = String.valueOf(id)+';';
		
		for(Map.Entry<String, Competence> entry : competences.entrySet())
			r = r + entry.getValue().getId() + ';';
		
                r = r + '\n';
                
		return r;
	}
        
    /**
     *
     * @param c
     * @return
     */
    public boolean possedeCompetence(Competence c){
            return this.competences.containsKey(c.getId());
        }
	
    /**
     *
     * @return
     */
    public int getId() {
		return id;
	}
	
    /**
     *
     * @param id
     */
    public void setId(int id) {
		this.id = id;
	}
	
    /**
     *
     * @return
     */
    public String getPrenom() {
		return prenom;
	}
	
    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
    /**
     *
     * @return
     */
    public String getNom() {
		return nom;
	}
	
    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
		this.nom = nom;
	}
	
    /**
     *
     * @return
     */
    public String getDateEntree() {
		return dateEntree;
	}
	
    /**
     *
     * @param dateEntree
     */
    public void setDateEntree(String dateEntree) {
		this.dateEntree = dateEntree;
	}

    /**
     *
     * @return
     */
    public boolean toStingComp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}
