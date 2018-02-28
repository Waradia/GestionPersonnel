/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author diawara
 */
public abstract class MissionsEnPreparation {
    private Integer idMission;
    private int nbP;
    private int duree;
    private Date dateDebut;
    private Date dateFin;
    private ArrayList<Personne> personnes;
    private ArrayList<Besoin> besoins;
    public static int idFixe=1;
    /**
     * Le constructeur d'une mission en cours de préparation 
     * @param nbP
     * @param duree
     * @param dateDeb
     * @param dateFin 
     */
    public MissionsEnPreparation(int nbP,int duree,Date dateDeb,Date dateFin)
    {
        this.nbP=nbP;
        this.dateDebut=dateDeb;
        this.dateFin=dateFin;
        this.duree=duree;
        this.idMission=MissionsEnPreparation.idFixe;
        MissionsEnPreparation.idFixe++;
        this.besoins=new ArrayList<>();
        this.personnes=new ArrayList<>();
    }
    public MissionsEnPreparation(MissionsEnPreparation m)
    {
        this.dateFin=m.dateFin;
        this.dateDebut=m.dateDebut;
        this.duree=m.duree;
        this.nbP=m.nbP;
        this.besoins=(ArrayList<Besoin>) m.besoins.clone();
        this.personnes=(ArrayList<Personne>) m.personnes.clone();
        this.idMission=m.idMission;
    }
    /**
     * Cette fonction permet d'ajouter un besoin à une mission
     * @param b 
     */
    public void ajouterBesoin(Besoin b)
    {
        if(!this.besoins.contains(b))
        {
            this.besoins.add(b);
        }
    }
    /**
     * Cette fonction permet d'ajouter une personne à une mission
     * @param p 
     */
    public void ajouterPersonne(Personne p)
    {
        if(!this.personnes.contains(p))
        {
            this.personnes.add(p);
        }
    }
    /**
     * Cette fonction renvoit le nombre de personne necessaire pour une mission
     * @return 
     */
    public Integer getNbP()
    {
        return this.nbP;
    }
    /**
     * Cette fonction renvoit les besoins necessaires pour une missions
     * @return 
     */
    public ArrayList<Besoin> getBesoins()
    {
        return this.besoins;
    }
    /**
     * Cette fonction renvoit les personnes affectées à une missions
     * @return 
     */
    public ArrayList<Personne> getPersonnes()
    {
        return this.personnes;
    }
    
    
}
