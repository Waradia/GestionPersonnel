
import java.util.Date;

public interface IPersonne {

  public String toString();

  public void AjouterComptence(Competences c);
  public Integer getID();
  public Date getDateEntree();

}