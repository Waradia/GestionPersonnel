package mission;

import gestion.Competence;
import gestion.Employe;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author selbe
 */
public class Mission {
   
    /**
     *
     */
    public int idMission;

    /**
     *
     */
    public int nbPersonnes;

    /**
     *
     */
    public String description;

    /**
     *
     */
    
    public LinkedList<Competence> competencesDeLaMission;

    /**
     *
     */
    
    public LinkedList<Employe> employesDeLaMission;

    /**
     *
     */
    public int[] nbPersonnesParCompetence;

    /**
     *
     */
    public String dateDebut;

    /**
     *
     */
    public int dureeJours;
    
    /**
     *
     */
    
    public Mission(){
    }

    
    public Mission(String[] data){
        
        this.idMission = Integer.parseInt(data[0]);
        this.description = data[1];
        this.dateDebut = data[2];
        this.dureeJours = Integer.parseInt(data[3]);
        this.nbPersonnes = Integer.parseInt(data[4]);
        
        this.competencesDeLaMission = new LinkedList<Competence>();
        this.nbPersonnesParCompetence = new int[Integer.parseInt(data[4])];
        this.employesDeLaMission = new LinkedList<Employe>();
        
    }
    public Mission(Mission m){
        
        this.idMission = m.getIdMission();
        this.description = m.getDescription();
        this.dateDebut = m.getDateDebutString();
        this.dureeJours = m.getDureeJours();
        this.nbPersonnes = m.nbPersonnes;
      
        this.competencesDeLaMission = (LinkedList<Competence>) m.getCompetencesDeLaMission().clone();
        this.employesDeLaMission = (LinkedList<Employe>) m.getEmployesDeLaMission().clone();
        this.nbPersonnesParCompetence = m.getNbPersonnesParCompetence().clone();  
        
    }
    
    public String toString(){
        
        String r = String.valueOf(this.idMission) + ';'+this.description+';'+
                   this.dateDebut+';'+ String.valueOf(this.dureeJours)+';'+
                   String.valueOf(this.nbPersonnes) +';' ;
        return r;
    }
    
    /**
     *
     * @return
     */
    public String toStringCompMission(){
      String r = String.valueOf(this.idMission) + ';';
      
      for (Competence c : this.competencesDeLaMission)
          r = r + c.getId() + ';';
      
      return r;
    }

    public String getDescription() {
        return description;
    }
    
    /**
     *
     * @return
     */
    public String toStringPersoParComp(){
      String r = String.valueOf(this.idMission) + ';';
      
      for (int i: this.nbPersonnesParCompetence)
          r= r + String.valueOf(i) +';';
      
      return r;
    }
    
    /**
     *
     * @return
     */
    public String toStringPersoMission(){
      String r = String.valueOf(this.idMission) + ';';
      
      for (Employe e: this.employesDeLaMission)
          r= r + String.valueOf(e.getId()) +';';
      
      return r;
    }
    
    /**
     *
     * @return
     */
    public int getNbPersonnes() {
        return nbPersonnes;
    }

    /**
     *
     * @return
     */
    public LinkedList<Competence> getCompetencesDeLaMission() {
        return competencesDeLaMission;
    }

    /**
     *
     * @return
     */
    public LinkedList<Employe> getEmployesDeLaMission() {
        return employesDeLaMission;
    }

    /**
     *
     * @return
     */
    public int[] getNbPersonnesParCompetence() {
        return nbPersonnesParCompetence;
    }

    /**
     *
     * @return
     */
    public Date getDateDebut() throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateDebut) ;
    }
    public String getDateDebutString(){
        return dateDebut;
    }

    /**
     *
     * @return
     */
    public int getDureeJours() {
        return dureeJours;
    }

    public int getIdMission() {
        return idMission;
    }
    
    public String getClassName(){
        if ("MissionModifiable".equalsIgnoreCase(getClass().getSimpleName()))
            return "En preparation";
        else if ("MissionEnCours".equalsIgnoreCase(getClass().getSimpleName()))
            return "En cours";
        else if ("MissionPlanifiee".equalsIgnoreCase(getClass().getSimpleName()))
            return "Planifiee";
        else if ("MissionTerminee".equalsIgnoreCase(getClass().getSimpleName()))
            return "Terminee";
        else
            return "Mission non validée et date dépassée";
    }
    
    public static Date addSubstractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
                
		return cal.getTime();
	}
    
    public boolean possedeCompetence(Competence c){
            return this.competencesDeLaMission.contains(c);
        }

    public boolean contientEmploye(Employe e) {
            return this.employesDeLaMission.contains(e); 
    }
    
    public int getNmpPourComp(Competence c){
        
        if (possedeCompetence(c))
            return this.nbPersonnesParCompetence[this.competencesDeLaMission.indexOf(c)];
        else
            return 0;
    }
    
    public Date dateFin(){
        Date d = null;
        
        try {
            d = Mission.addSubstractDays(new SimpleDateFormat("dd/MM/yyyy").parse(this.dateDebut) , this.dureeJours);
        } catch (ParseException ex) {
        }
     
        return d;
    }
    
}
