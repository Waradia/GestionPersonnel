/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author diawara
 */
public class Besoin {
    private int nbP;
    private Competences skillRequired;
    /**
     * Le constructeur d'un besoin
     * @param c
     * @param nbP 
     */
    public Besoin(Competences c,int nbP)
    {
        this.nbP=nbP;
        this.skillRequired=c;
    }
    /**
     * Cette fonction retourne le nombre de personne necessaire pour remplir un besoin
     * @return 
     */
    public int getNbP() {
        return nbP;
    }
    /**
     * Cette fonction renvoit la compétence necessaire pour un besoin
     * @return 
     */
    public Competences getSkillRequired() {
        return skillRequired;
    }
    
}
