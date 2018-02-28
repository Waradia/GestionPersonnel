/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author diawara
 */
public class Personne implements IPersonne {
    private String nom;
    private String prenom;
    private Date dateEntree;
    private final Integer id;

    /**
     *Cette variable sert à construire des identifiants uniques pour chaque employé
     */
    public static  int idFixe;
    
    ArrayList<Competences> skills;
    /**
     * Le constructeur de la Personne qui sera l'employée
     * @param nom
     * @param prenom
     * @param dateEntree 
     */
    
    public Personne(String nom,String prenom,Date dateEntree)
    {
        this.prenom=prenom;
        this.nom=nom;
        this.dateEntree=dateEntree;
        this.skills=new ArrayList<>();
        try{
            Personne.idFixe=CSVFiles.FileCsv.nombreLigne();
            Personne.idFixe++;
        }
        catch (IOException e)
        {
            Personne.idFixe=1;
        }
        this.id=Personne.idFixe;
        Personne.idFixe++;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return this.prenom+";"+this.nom+";"+this.dateEntree+";"+this.id;
    }
    /**
     * Cette fonction permet d'ajouter une competence à un employé
     * @param c 
     */
    @Override
    public void ajouterCompetence(Competences c)
    {
        if(!this.skills.contains(c))
        {
            this.skills.add(c);
        }
    }
    /**
     * Cette fonction renvoie l'identifiant d'un employé
     * @return 
     */
    @Override
    public Integer getId()
    {
        return this.id;
    }
    /**
     * Cette fonction la date d'entrée d'un employé
     * @return 
     */
    @Override
    public Date getDateEntree()
    {
        return this.dateEntree;
    }
    /**
     * Cette fonction renvoie le nom d'un employé
     * @return 
     */
    public String getNom()
    {
        return this.nom;
    }
    /**
     * Cette fonction renvoie le prenom d'un employé
     * @return 
     */
    public String getPrenom()
    {
        return this.prenom;
    }
}
