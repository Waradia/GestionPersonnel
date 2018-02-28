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
public interface ICompetences {

    /**
     *
     * @param p
     */
    public void ajouterPersonne(Personne p);
    /**
     * 
     * @return 
     */
    public String getDescription();
    /**
     * 
     * @return 
     */
    public Integer getIdSkills();
    /**
     * 
     * @return 
     */
    public ArrayList<Personne> getPersonnes();
}
