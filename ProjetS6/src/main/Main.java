/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import CSVFiles.FichierPersonnel;
import Fenetrecompetences.AjoutCompet;
import Fenetrecompetences.DetailCompt;
import Fenetrecompetences.GestionCompetence;
import Fenetrecompetences.ModifCompt;
import Fenetrecompetences.RecherCompt;
import Fenetrecompetences.SupCompt;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import Fenetrepersonnels.AjoutPersonnel;
import Fenetrepersonnels.DetailEmploye;
import Fenetrepersonnels.Accueil;
import Fenetrepersonnels.GestionPersonnel;
import Fenetrepersonnels.ModifyPersonel;
import Fenetrepersonnels.RecherchPersonel;
import Fenetrepersonnels.RecherchPersonel;
import Fenetrepersonnels.SupPersonnel;
import Fenetremission.AJoutPersMis;
import Fenetremission.AjoutCompMis;
import Fenetremission.AjoutMission;
import Fenetremission.Detail;
import Fenetremission.GestionMission;
import Fenetremission.ModifyMission;
import Fenetremission.RechercheMission;
import Fenetremission.SupprimerMi;

/**
 *
 * @author guisse
 */
public class Main extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT1 = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;
    
    /*
    Accueil 
    */
    static  Accueil accueil = new  Accueil();
    /*
    Getions des Missions
    */
    static GestionMission gestionM = new GestionMission();
    static Detail detailM = new Detail();
    static AjoutMission ajouterMis = new AjoutMission();
    static AjoutCompMis ajoutCompteMis = new AjoutCompMis();
    static RechercheMission rechMis = new RechercheMission();
    static SupprimerMi supriM = new SupprimerMi();
    static ModifyMission modifM = new ModifyMission();
    static AJoutPersMis ajoutPersMis = new AJoutPersMis();
    /*
    gestion des Personnels
    */
   static GestionPersonnel gestPerso = new GestionPersonnel();
   static ModifyPersonel modifMiP = new ModifyPersonel() ;
   static AjoutPersonnel ajoutPers = new AjoutPersonnel();
   static DetailEmploye detailEmp = new DetailEmploye();
   static  RecherchPersonel rechP = new RecherchPersonel();
   static SupPersonnel supPerso = new SupPersonnel();
   /*
   gestion des competences 
   */
   static GestionCompetence gestCompt = new GestionCompetence();
   static AjoutCompet ajoutcompt = new AjoutCompet();
   static ModifCompt modifCompt = new ModifCompt();
   static RecherCompt rechCompt= new RecherCompt();
   static SupCompt supComp = new SupCompt();
   static DetailCompt detailCompt = new DetailCompt();
    public static FichierPersonnel personnalFile  = null ;
    public static void main(String[] args) {
        
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                accueil.setVisible(true);
            }
        });
    }    
    public static void accueil(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                accueil.setVisible(true);
                
            }
        });
    }
     public static void fermerAccueil(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                accueil.setVisible(false);
                
            }
        });
    }
    /*
     Gestion Competences 
    */
    public static void ouvirGestComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gestCompt.setVisible(true);
                
            }
        });
    }      
    public static void fermerGestComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gestCompt.setVisible(false);
                
            }
        });
    }
     public static void ouviAjoutComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ajoutcompt.setVisible(true);
                
            }
        });
    }      
    public static void fermerAjoutComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ajoutcompt.setVisible(false);
                
            }
        });
    }
      public static void ouviModifComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modifCompt.setVisible(true);
                
            }
        });
    }      
    public static void fermermodifComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modifCompt.setVisible(false);
                
            }
        });
    }
      public static void ouvirDetailComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                detailCompt.setVisible(true);
                
            }
        });
    }      
    public static void fermerDetailComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                detailCompt.setVisible(false);
                
            }
        });
    }
     public static void ouvirRecherComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                rechCompt.setVisible(true);
                
            }
        });
    }      
    public static void fermerRecherComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                rechCompt.setVisible(false);
                
            }
        });
    }
     public static void ouvirSupComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                supComp.setVisible(true);
                
            }
        });
    }      
    public static void fermerSupComp(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                supComp.setVisible(false);
                
            }
        });
    }
     /* 
     Gestion Personnel 
    */
    public static void ouvirGestPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gestPerso.setVisible(true);
                
            }
        });
    }      
    public static void fermerGestPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gestPerso.setVisible(false);
                
            }
        });
    }
    public static void ouvirModifPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modifMiP.setVisible(true);
                
            }
        });
    }      
    public static void fermerModifPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modifMiP.setVisible(false);
                
            }
        });
    }
     public static void ouvirdetailPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                detailEmp.setVisible(true);
                
            }
        });
    }      
    public static void fermerdetailPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                detailEmp.setVisible(false);
                
            }
        });
    }
     public static void ouvirRechPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                rechP.setVisible(true);
                
            }
        });
    }      
    public static void fermerRechePerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                rechP.setVisible(false);
                
            }
        });
    }
     public static void ouvirAjoutPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ajoutPers.setVisible(true);
                
            }
        });
    }      
    public static void fermerAjoutPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ajoutPers.setVisible(false);
                
            }
        });
    }
     public static void ouvirSuppriPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                supPerso.setVisible(true);
                
            }
        });
    }      
    public static void fermerSupprPerso(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                supPerso.setVisible(false);
                
            }
        });
    }
    
    
    
     /*
    Gestion Missions    
    */
     public static void ouvirGestMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gestionM.setVisible(true);
                
            }
        });
    }
    public static void fermeGestMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gestionM.setVisible(false);
                
            }
        });
    }   
    
    public static void modifyMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modifM.setVisible(true);
                
            }
        });
    }
    public static void fermemodifyMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modifM.setVisible(false);
                
            }
        });
    }
    public static void detailMis(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                detailM.setVisible(true);
                
            }
        });
    }
    public static void fermerdetailMis(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                detailM.setVisible(false);
                
            }
        });
    }
    public static void ajouterMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ajouterMis.setVisible(true);
                
            }
        });
    }
    public static void fermerajouterMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ajouterMis.setVisible(false);
                
            }
        });
    }
    public static void rechercheMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                rechMis.setVisible(true);
                
            }
        });
    }
    public static void fermerechercheMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                rechMis.setVisible(false);
                
            }
        });
    }
    public static void supprimerMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                supriM.setVisible(true);
                
            }
        });
    }
    public static void fermesupprimerMission(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                supriM.setVisible(false);
                
            }
        });
    }
    public static void ajoutPMision(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ajoutPersMis.setVisible(true);
                
            }
        });
    }
    public static void fermeajoutPMision(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ajoutPersMis.setVisible(false);
                
            }
        });
    }
     
    
}
    
    
    
    
    
     
        
        
        
        
        

    
    
    
    

