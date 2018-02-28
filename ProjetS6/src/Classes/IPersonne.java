/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * @author diawara
 */
public interface IPersonne {
    /**
     * 
     * @return 
     */
    public String toString();
    /**
     * 
     * @param c 
     */
    public void ajouterCompetence(Competences c);
    /**
     * 
     * @return 
     */
    public Integer getId();
    /**
     * 
     * @return 
     */
    public Date getDateEntree();
}
