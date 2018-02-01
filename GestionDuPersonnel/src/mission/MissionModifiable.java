/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mission;

import gestion.Competence;
import gestion.Employe;

/**
 *
 * @author selbe
 */
public class MissionModifiable extends Mission{
    
    /**
     *
     * @param data
     */
    
    public MissionModifiable(String[] data)
    {
        super(data);    
    }
    public MissionModifiable(Mission m){
        super(m);
    }

    /**
     *
     */
    public void ajouterCompetence(Competence c) {
        this.competencesDeLaMission.add(c);
    }
    
    public void ajouterCompetence(Competence c,int nbEmp) {
        this.competencesDeLaMission.add(c);
        this.nbPersonnesParCompetence[competencesDeLaMission.indexOf(c)] = nbEmp;
    }
    
    
    
    /**
     *
     */
    public void ajouterEmploye(Employe e){
        this.employesDeLaMission.add(e);
    }
}
