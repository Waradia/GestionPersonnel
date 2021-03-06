/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import fichiers.*;
import gestion.*;
import mission.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author selbe
 */
public class GestionSociete extends javax.swing.JFrame {

    
    /*Variable utilisées pour la manipulation de données*/
    
    public static final String FILES_DIRECTORY = "./../fichiers/";
    public static final String MISSIONS_DIRECTORY = "./../missions/";
    
    public static final String FICHIER_PERSONNEL = "liste_personnel.csv";
    public static final String FICHIER_COMP_PERSONNEL = "competences_personnel.csv";
    public static final String FICHIER_COMPETENCES = "liste_competences.csv";
    public static final String FICHIER_MISSIONS = "liste_missions.csv";
    public static final String FICHIER_NB_P_COMP_MISSION = "mission_nbpParComp.csv";
    public static final String FICHIER_COMP_MISSION = "competences_mission.csv";
    public static final String FICHIER_PERS_MISSION = "personnel_mission.csv";
    
    

    /**
     *
     */

    public static boolean competencePersonnelReady = false;

    /**
     *
     */
    public static int rowID = -1;

    /**
     *
     */
    public static String rowID2;
    /*Données manipulés*/

    /**
     *
     */

    public static Map<Integer,Employe> personnel = new HashMap<Integer,Employe>();

    /**
     *
     */
    public static Map<String,Competence> competences = new LinkedHashMap<String,Competence>();

    /**
     *
     */
    
    public static Map<Integer, Mission> missions = new HashMap<Integer,Mission>();
    
    
    /*fichiers utilisés*/

    /**
     *
     */

    public static FileCsvPersonnel fcp = null ;

    /**
     *
     */
    public static FileCsvCompetencesPersonnel fccp = null;

    /**
     *
     */
    public static FileCsvCompetences fcc = null;

    /**
     *
     */
    public static FileCsvMissions fcm = null;
    
    public static FileCsvMissionPersonneParComp fcmppc = null;
    
    public static FileCsvCompetencesMission fccm = null;
    
    public static FileCsvPersonnelMission fcpm = null;
    
    /**
     * Creates new form GestionSociete
     */
    public GestionSociete() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean isInteger(String s) {
      boolean isValidInteger = false;
      try
      {
         Integer.parseInt(s);
 
         isValidInteger = true;
      }
      catch (NumberFormatException ex)
      {   
      }
      return isValidInteger;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        GestionPersonnel = new javax.swing.JButton();
        GestionCompetences = new javax.swing.JButton();
        GestionMissions = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Application Java");
        setResizable(false);

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Connecté sous le nom de diawara");
        jLabel1.setToolTipText("");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel1.setForeground(new java.awt.Color(102, 102, 0));
        jPanel1.setFocusable(false);
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 1000));

        GestionPersonnel.setText("Gestion Personnel");
        GestionPersonnel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GestionPersonnel.setPreferredSize(new java.awt.Dimension(140, 25));
        GestionPersonnel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestionPersonnelActionPerformed(evt);
            }
        });

        GestionCompetences.setText("Gestion Competences");
        GestionCompetences.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GestionCompetences.setPreferredSize(new java.awt.Dimension(140, 25));
        GestionCompetences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestionCompetencesActionPerformed(evt);
            }
        });

        GestionMissions.setText("Gestion Missions");
        GestionMissions.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GestionMissions.setPreferredSize(new java.awt.Dimension(145, 25));
        GestionMissions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestionMissionsActionPerformed(evt);
            }
        });

        jButton1.setText("Charger tous les fichiers rapidement");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(GestionPersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GestionCompetences, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GestionMissions, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GestionMissions, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GestionPersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GestionCompetences, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void GestionMissionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GestionMissionsActionPerformed
        // TODO add your handling code here:
        
        
        GestionMissions GM = new GestionMissions();
        this.dispose();
        GM.setVisible(true);
    }//GEN-LAST:event_GestionMissionsActionPerformed

    private void GestionPersonnelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GestionPersonnelActionPerformed
        // TODO add your handling code here:
        GestionPersonnel GP = new GestionPersonnel();
        GP.setVisible(true);
        //java.awt.EventQueue.invokeLater(new Runnable(){public void run() {new GestionPersonnel().setVisible(true);}});
        this.dispose();
        
    }//GEN-LAST:event_GestionPersonnelActionPerformed

    private void GestionCompetencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GestionCompetencesActionPerformed
        // TODO add your handling code here:
        GestionCompetences GC = new GestionCompetences();
        this.dispose();
        GC.setVisible(true);
    }//GEN-LAST:event_GestionCompetencesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        //Fichier des compétences
        GestionSociete.fcc = new FileCsvCompetences(GestionSociete.FILES_DIRECTORY + GestionSociete.FICHIER_COMPETENCES);

        for(String line : GestionSociete.fcc.data){

                String[] data = line.split( new Character(GestionSociete.fcc.separator()).toString());
                GestionSociete.competences.put(data[0],new Competence(data));
        }
        //Fichier du personnel
        GestionSociete.fcp = new FileCsvPersonnel(GestionSociete.FILES_DIRECTORY + GestionSociete.FICHIER_PERSONNEL);

        for(String line : GestionSociete.fcp.data){

                String[] data = line.split( new Character(GestionSociete.fcp.separator()).toString());
                GestionSociete.personnel.put(Integer.parseInt(data[3]),new Employe(data) );

            }
        
        //Fichier Compétences du personnel
        GestionSociete.fccp = new FileCsvCompetencesPersonnel(GestionSociete.FILES_DIRECTORY + GestionSociete.FICHIER_COMP_PERSONNEL);

        for(String line : GestionSociete.fccp.data){

                String[] data = line.split( new Character(GestionSociete.fccp.separator()).toString());


                for (int i=1; i < data.length; ++i){

                        if (GestionSociete.personnel.containsKey(Integer.parseInt(data[0]))){
                            Employe e = GestionSociete.personnel.get(Integer.parseInt(data[0]));
                            e.ajouterCompetence(GestionSociete.competences.get(data[i]));
                        }
                }
        }
        this.competencePersonnelReady = true;
        
        /* Lecture des fichiers des missions*/
        //Fichier Missions
        GestionSociete.fcm = new FileCsvMissions(GestionSociete.MISSIONS_DIRECTORY + GestionSociete.FICHIER_MISSIONS);
        for(String line : GestionSociete.fcm.data){

                String[] data = line.split( new Character(GestionSociete.fcm.separator()).toString());


                for (int i=1; i < data.length; ++i)
                    GestionSociete.missions.put(Integer.parseInt(data[0]), new MissionModifiable(data));
        }
        
        
        //Competences requises dans la mission
        
        GestionSociete.fccm = new FileCsvCompetencesMission(GestionSociete.MISSIONS_DIRECTORY + GestionSociete.FICHIER_COMP_MISSION);
        
        for(String line : GestionSociete.fccm.data){

                String[] data = line.split( new Character(GestionSociete.fccm.separator()).toString());


                for (int i=1; i < data.length; ++i){
                    if (GestionSociete.missions.containsKey(Integer.parseInt(data[0]))){
                            MissionModifiable m = (MissionModifiable) GestionSociete.missions.get(Integer.parseInt(data[0]));
                            m.ajouterCompetence(GestionSociete.competences.get(data[i]));
                        }
                }   
        }
        
        //Nombre de personnes par competence
        
        GestionSociete.fcmppc = new FileCsvMissionPersonneParComp(GestionSociete.MISSIONS_DIRECTORY + GestionSociete.FICHIER_NB_P_COMP_MISSION);
        
        for(String line : GestionSociete.fcmppc.data){

                String[] data = line.split( new Character(GestionSociete.fcmppc.separator()).toString());


                for (int i=1; i < data.length; ++i){
                    if (GestionSociete.missions.containsKey(Integer.parseInt(data[0]))){
                            MissionModifiable m = (MissionModifiable) GestionSociete.missions.get(Integer.parseInt(data[0]));
                            m.nbPersonnesParCompetence[i-1] = Integer.parseInt(data[i]);
                        }
                }
        }
        
        //Personnel affecté à la mission
        
        GestionSociete.fcpm = new FileCsvPersonnelMission(GestionSociete.MISSIONS_DIRECTORY + GestionSociete.FICHIER_PERS_MISSION);
        
        for(String line : GestionSociete.fcpm.data){

                String[] data = line.split( new Character(GestionSociete.fcpm.separator()).toString());


                for (int i=1; i < data.length; ++i){
                    if (GestionSociete.missions.containsKey(Integer.parseInt(data[0]))){
                            MissionModifiable m = (MissionModifiable) GestionSociete.missions.get(Integer.parseInt(data[0]));
                            m.ajouterEmploye(GestionSociete.personnel.get(Integer.parseInt(data[i])));
                        }
                }
        }

        
        /*
        for (Map.Entry<Integer, Mission> entry : GestionSociete.missions.entrySet()){
            System.out.println(entry.getValue().toString());
            System.out.println(entry.getValue().toStringCompMission());
            System.out.println(entry.getValue().toStringPersoMission());
            System.out.println(entry.getValue().toStringPersoParComp());
        }
        
        
            LinkedList<Employe> employes = new LinkedList<>();
            employes.add(GestionSociete.personnel.get(1));
            employes.add(GestionSociete.personnel.get(1));
            
            
            for (Employe e : employes)
                System.out.println(e.toString());
            
            while (employes.contains(GestionSociete.personnel.get(1)))
                employes.remove(GestionSociete.personnel.get(1));
            
            for (Employe e : employes)
                System.out.println("hey" + e.toString());
            */
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Acceuil a=new Acceuil();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     *
     * @param date
     * @return
     */
    
    public static boolean isValidDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = null; 
        
        try
        {
          testDate = sdf.parse(date);
        }
        catch (ParseException e)
        {
          return false;
        }
        if (!sdf.format(testDate).equals(date))
        {
          return false;
        }
        return true;
    } // end isValidDate

   
    public static void main(String args[]) {
        
       Acceuil  GS = new Acceuil();
            /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(GestionSociete.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
        
        //</editor-fold>

        /* Create and display the form */
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GS.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GestionCompetences;
    private javax.swing.JButton GestionMissions;
    private javax.swing.JButton GestionPersonnel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
