import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Personne implements IPersonne {

  private  String nom;
  private  String Prenom;
  private Date dateEntree;
  private Integer idPersonne;
  ArrayList <Competences> skills;
  public static Integer idfixe=0;
  public Personne(String nom, String prenom, Date entree)
  {
      this.nom=nom;
      this.Prenom=prenom;
      this.dateEntree=entree;
      this.idPersonne=Personne.idfixe+1;
      Personne.idfixe=this.idPersonne;
  }
  public String toString()
  {
      return null;
  }

  public void AjouterComptence(Competences c)
  {
      if(!this.skills.contains(c))
      {
          this.skills.add(c);
      }
  }
  public Integer getID()
  {
      return this.idPersonne;
  }
  public Date getDateEntree()
  {
      return this.dateEntree;
  }
}