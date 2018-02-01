/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mission;

import gestion.Competence;

/**
 *
 * @author selbe
 */
public class MissionPlanifiee extends MissionModifiable{
    
    /**
     *
     * @param data
     * @param idMission
     * @param description
     * @param nbPersonnes
     * @param dateDebut
     * @param dureeJours
     */

    public MissionPlanifiee(String[] data) {
        super(data);
    }   
    
    public MissionPlanifiee(Mission m) {
        super(m);
    } 
    
}
