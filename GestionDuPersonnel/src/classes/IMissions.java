
import java.util.ArrayList;

public interface IMissions {

  public int getnbP();
  public ArrayList<IPersonne> getpersonnes();
  public String getDescription();

    /**
     *
     * @return
     */
 public ArrayList<ICompetences> getCompetences();

  public void ajouterPersonne( Personne p);

  public void ajouterCompetence( Competences c);

}