import java.util.ArrayList;
import java.util.Vector;

public class Competences implements ICompetences {

  public Integer idSkill;
  public String description;
  public ArrayList<Personne> personnes;
  public Competences(Integer id,String desc)
  {
      this.idSkill=id;
      this.description=desc;
  }
  public void ajouterPersonne(Personne p)
  {
      if(!this.personnes.contains(p))
      {
          this.personnes.add(p);
      }
  }

  public String getDescription()
  {
      return this.description;
  }

  public Integer getIdSkill()
  {
      return this.idSkill;
  }

  public ArrayList<Personne> getPersonnes()
  {
      return this.personnes;
  }

  public String toString()
  {
      return null;
  }
    /**
   * 
   * @element-type Personne
   */
  
    /**
   * 
   * @element-type Personne
   */
 
}