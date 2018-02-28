/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author diawara
 */
public class Competences implements ICompetences{
    
    private String description;
    private final Integer idSkill;
    public static int idFixe=1;
    private  ArrayList<Personne> personnes;

    /**
     * Le constructeur d'une compétence
     * @param description
     */
    public Competences(String description)
    {
        this.description=description;
        this.idSkill=Competences.idFixe;
        Competences.idFixe++;
        this.personnes=new ArrayList<>();
    }
    /**
     * Cette fonction permet d'ajouter un employé posssedant cette compétence
     * @param p
     */
    @Override
    public void ajouterPersonne(Personne p)
    {
        if(!this.personnes.contains(p))
        {
           this.personnes.add(p);
        }
    }
    /**
     * Cette fonction renvoit l'identifiant d'une compétence
     * @return 
     */
    @Override
    public String getDescription()
    {
        return this.description;
    }
    /**
     * Cette fonction renvoit l'identifiant d'une compétence
     * @return 
     */
    @Override
    public Integer getIdSkills()
    {
        return this.idSkill;
    }
    /**
     * Cette fonction renvoit la liste de toutes les personnes possedant cette compétence
     * @return 
     */
    @Override
    public ArrayList<Personne> getPersonnes()
    {
        return this.personnes;
    }
    
}
