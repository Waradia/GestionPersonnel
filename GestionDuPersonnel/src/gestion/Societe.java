/*package gestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fichiers.*;
import mission.Mission;

public class Societe {

	private Map<Integer,Employe> personnel;
	private Map<String,Competence> competences;
	private List<Mission> mission;
	
	
	public Societe(){		
		//tab de competences
		FileCsvCompetences FCC = new FileCsvCompetences();
		competences = new HashMap<String,Competence>();
		
		
		for ( String line : FCC.data){
			String[] data = line.split( new Character(FCC.separator()).toString());
			competences.put(data[0],new Competence(data) );
		}
		
		//tab personnel
		FileCsvPersonnel FCP = new FileCsvPersonnel();
		personnel = new HashMap<Integer,Employe>();
		
		for ( String line : FCP.data){
	
			String[] data = line.split( new Character(FCP.separator()).toString());
			personnel.put(Integer.parseInt(data[3]),new Employe(data) );
		}
			
		// competences personnel
		FileCsvCompetencesPersonnel FCCP = new FileCsvCompetencesPersonnel();
		
		for (String line2 : FCCP.data){

			String[] data2 = line2.split( new Character(FCCP.separator()).toString());

			for (int i=1; i < data2.length; ++i){
				
				Employe e = personnel.get(Integer.parseInt(data2[0]));
				e.ajouterCompetence(competences.get(data2[i]));
			}
                }
	}
	
	public static void main(String[] g){
		Societe s = new Societe();
		
		for (Map.Entry<Integer, Employe> entry : s.personnel.entrySet()) {
                    System.out.println("[" + entry.getKey() +  "] -> " + entry.getValue() + "\t\t" + entry.getValue().toStringComp()) ;
			}
                 
                
	}
	
}*/