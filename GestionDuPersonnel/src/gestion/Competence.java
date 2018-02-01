package gestion;

/**
 *
 * @author selbe
 */
public class Competence {

	private String id;
	private String nomFr;
	private String nomEn;
	
    /**
     *
     * @param data
     */
    public Competence(String[] data){
		this.id = data[0];
		this.nomEn = data[1];
		this.nomFr = data[2];
	}
	
	public String toString(){
		return (id +";" + nomEn+";" + nomFr +'\n');
	}
	
    /**
     *
     * @return
     */
    public String getNomEn() {
		return nomEn;
	}
	
    /**
     *
     * @param nomEn
     */
    public void setNomEn(String nomEn) {
		this.nomEn = nomEn;
	}
	
    /**
     *
     * @return
     */
    public String getNomFr() {
		return nomFr;
	}
	
    /**
     *
     * @param nomFr
     */
    public void setNomFr(String nomFr) {
		this.nomFr = nomFr;
	}
	
    /**
     *
     * @return
     */
    public String getId() {
		return id;
	}
	
    /**
     *
     * @param id
     */
    public void setId(String id) {
		this.id = id;
	}
}
