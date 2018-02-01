/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import fichiers.FileCsvCompetencesMission;
import fichiers.FileCsvMissionPersonneParComp;
import fichiers.FileCsvMissions;
import fichiers.FileCsvPersonnelMission;
import gestion.Competence;
import gestion.Employe;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import mission.Mission;
import mission.MissionEnCours;
import mission.MissionModifiable;
import mission.MissionNonModifiable;
import mission.MissionPlanifiee;
import mission.MissionTerminee;

/**
 *
 * @author selbe
 */
public class GestionMissions extends javax.swing.JFrame {

    
/**
     * Creates new form GestionMissions
     */
    public static Date date = new Date();
    
    public GestionMissions() {
        initComponents();
        console.removeAll();
        jButton15.setVisible(false);
        jButton12.setVisible(false);
        jButton10.setVisible(false);
        jButton2.setVisible(false);
        
        this.jFormattedTextField1.setValue(date);
        
        
        if (!GestionSociete.missions.isEmpty()){
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            
            GestionSociete.missions = GestionMissions.updateMissions(GestionSociete.missions, date);
            
            for (Map.Entry<Integer, Mission> entry : GestionSociete.missions.entrySet())
                model.addRow(new Object[] {String.valueOf(entry.getValue().getIdMission()),entry.getValue().getDescription(),entry.getValue().getDateDebutString(),
                            entry.getValue().getDureeJours(),entry.getValue().getNbPersonnes(),entry.getValue().getClassName()} );
        }
        
    }
    
    public static HashMap<Integer,Mission> updateMissions(Map<Integer,Mission> missions, Date date){
        HashMap<Integer,Mission> result = new HashMap<Integer,Mission>();
        
        for (Map.Entry<Integer,Mission> entry : GestionSociete.missions.entrySet()){
            //Mission terminee
            
            if (entry.getValue().dateFin().before(GestionMissions.date)){
                MissionTerminee m = new MissionTerminee(entry.getValue());
                result.put(entry.getValue().getIdMission(), m);
            }
            else
            {
                //Mission pas terminee
                    try {
                        //Mission En Cours
                        if (entry.getValue().getDateDebut().before(GestionMissions.date)) {
                            MissionEnCours m = new MissionEnCours(entry.getValue());
                            result.put(entry.getValue().getIdMission(), m);
                        }
                        //Mission En Preparation/planifiee
                        else
                        {
                            //Mission En preparation
                            if (entry.getValue().getEmployesDeLaMission().isEmpty()){
                                MissionModifiable m = new MissionModifiable(entry.getValue());
                                result.put(entry.getValue().getIdMission(), m);
                            }
                            else{
                                MissionPlanifiee m = new MissionPlanifiee(entry.getValue());
                                result.put(entry.getValue().getIdMission(), m);
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(GestionMissions.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            
            try {
                if (entry.getValue().getEmployesDeLaMission().isEmpty() && entry.getValue().getDateDebut().before(GestionMissions.date)){
                    result.remove(entry.getValue().getIdMission(), entry.getValue());
                    MissionNonModifiable m = new MissionNonModifiable(entry.getValue());
                    result.put(entry.getValue().getIdMission(), m);
                }
            } catch (ParseException ex) {
                Logger.getLogger(GestionMissions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    
    public static Map<Integer,Employe> employesCandidatsMission(Mission m){
        
        Map<Integer,Employe> employes = new HashMap<>();
        
         for (Competence c :m.competencesDeLaMission){ 
            for (Map.Entry<Integer, Employe> entry : GestionSociete.personnel.entrySet())
                if (entry.getValue().possedeCompetence(c) && !employes.containsKey(entry.getValue().getId()))
                    employes.put(entry.getValue().getId(), entry.getValue());
         }
        return employes;
    }
    
    
    
    public static int apparaitNbFois(LinkedList<Competence> compMission,Employe e){
        int r = 0;
        
        for (Competence c : compMission)
            if (e.possedeCompetence(c))
                ++r;
        
        return r;
    }
    
    public static MissionModifiable affectationEmployes(MissionModifiable m){
        Map<Integer,Employe> candidats = GestionMissions.employesCandidatsMission(m);
        Map<Integer,Employe> candidatsBis = new HashMap<Integer,Employe>(candidats);
        
        int[] tabNbEmpMission = m.getNbPersonnesParCompetence().clone();
        LinkedList<Competence> compMission = new LinkedList<Competence>(m.getCompetencesDeLaMission());
        LinkedList<Competence> compMissionBis = (LinkedList<Competence>) compMission.clone();
        int coutEmploye = 0;
        boolean fini = false;
        
        int i = 0;
        
        int persoManquand = 0;
        for (int j : tabNbEmpMission) 
            persoManquand = persoManquand + j;
        
        while (!fini && persoManquand > 0){
            
            for (Competence c : compMission){
                if (tabNbEmpMission[i] > 0){
                    for (Map.Entry<Integer,Employe> entry : candidats.entrySet()){
                        if (entry.getValue().possedeCompetence(c)) {
                            if ((apparaitNbFois(compMissionBis, entry.getValue()) - coutEmploye) == 1 && tabNbEmpMission[i] >0){
                                tabNbEmpMission[i]--;
                                persoManquand--;
                                m.ajouterEmploye(entry.getValue());
                                candidatsBis.remove(entry.getValue().getId(), entry.getValue());
                            }
                        }
                        else if (apparaitNbFois(compMissionBis, entry.getValue()) == 0)
                                candidatsBis.remove(entry.getValue().getId(), entry.getValue());
                            
                    }
                    
                    candidats.clear();
                    candidats.putAll(candidatsBis);

                    if (tabNbEmpMission[i] == 0){
                        System.out.println(compMissionBis.indexOf(c) +"          "+ (compMissionBis.size()-1));
                        if (compMissionBis.indexOf(c) != compMissionBis.size()-1)
                            for (int j = compMissionBis.indexOf(c); j < compMissionBis.size()-1; j++)
                                tabNbEmpMission[j] = tabNbEmpMission[j+1];
                        
                        compMissionBis.remove(c);
                        coutEmploye--;
                        i--;
                    }
                }
                ++i;
            }
            i=0;
                
            compMission.clear();
            compMission.addAll(compMissionBis);
                
            if (compMissionBis.isEmpty() || candidatsBis.isEmpty())
                fini = true;
            
            System.out.println("Application.GestionMissions.affectationEmployes(4)");
            coutEmploye++;
            
            if (coutEmploye == GestionSociete.competences.size())
                coutEmploye = 0;
        }
        
        System.out.println(persoManquand);
       
        
        if (!compMission.isEmpty()){
            for (Map.Entry<Integer, Employe> entry : GestionSociete.personnel.entrySet()) {
                if(!m.getEmployesDeLaMission().contains(entry.getValue()) && persoManquand > 0){
                    m.ajouterEmploye(entry.getValue());
                    persoManquand--;
                }
            }
        }
        
        return m;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestion des Missions");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 0, 0)));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id", "Description", "Date de début", "Durée", "Nombre de personnes affectées","Etat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        });
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton13.setText("Retour");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton12.setText("Modifier une mission");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton11.setText("Ajouter une mission");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton10.setText("Supprimer une mission");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton9.setText("Charger les fichiers des missions");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setText("Sauvegarder les fichiers des missions");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel2.setText("Date");

        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Actualiser");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        console.setColumns(20);
        console.setRows(5);
        jScrollPane3.setViewportView(console);

        jButton14.setText("Clear");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Détails de la mission");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton2.setText("Voir/Modifier les affectations du personnel");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(144, 144, 144)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14)
                        .addGap(0, 85, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15)
                .addGap(1, 1, 1)
                .addComponent(jButton13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         if (GestionSociete.missions.isEmpty()){
             console.setText(null);
             console.append("Vous devez charger le fichier des missions\n ou de créer des missions \navant de poursuivre!");
        }
         else{
           if (GestionSociete.fcm != null){
              GestionSociete.fcm.MAJ(GestionSociete.missions);
              GestionSociete.fcpm.MAJ(GestionSociete.missions);
              GestionSociete.fcmppc.MAJ(GestionSociete.missions);
              GestionSociete.fccm.MAJ(GestionSociete.missions);
           }else{
               
            GestionSociete.fcm = new FileCsvMissions(GestionSociete.MISSIONS_DIRECTORY + GestionSociete.FICHIER_MISSIONS );
          
            GestionSociete.fccm = new FileCsvCompetencesMission(GestionSociete.MISSIONS_DIRECTORY + GestionSociete.FICHIER_COMP_MISSION);

            GestionSociete.fcmppc = new FileCsvMissionPersonneParComp(GestionSociete.MISSIONS_DIRECTORY + GestionSociete.FICHIER_NB_P_COMP_MISSION );

            GestionSociete.fcpm = new FileCsvPersonnelMission(GestionSociete.MISSIONS_DIRECTORY + GestionSociete.FICHIER_PERS_MISSION );
            
            GestionSociete.fcm.MAJ(GestionSociete.missions);
            GestionSociete.fcpm.MAJ(GestionSociete.missions);
            GestionSociete.fcmppc.MAJ(GestionSociete.missions);
            GestionSociete.fccm.MAJ(GestionSociete.missions);
           }
         }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        JFileChooser dialogue = new JFileChooser();
        dialogue.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // affichage
        
        dialogue.showOpenDialog(null);
        
        dialogue.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                
                if(f.getName().equalsIgnoreCase("missions"))
                    return true;
                else
                    return false;
                }
            @Override
            public String getDescription(){
                return "Le fichier ne correspond pas!";//To change body of generated methods, choose Tools | Templates.
            }
        });
        if (dialogue.getSelectedFile() != null){
            System.out.println(dialogue.getSelectedFile().toString());
            /* Lecture des fichiers des missions*/
            //Fichier Missions
            GestionSociete.fcm = new FileCsvMissions(dialogue.getSelectedFile().toString() +GestionSociete.FICHIER_MISSIONS);
            for(String line : GestionSociete.fcm.data){

                    String[] data = line.split( new Character(GestionSociete.fcm.separator()).toString());

                    for (int i=1; i < data.length; ++i)
                        GestionSociete.missions.put(Integer.parseInt(data[0]), new MissionModifiable(data));
                    
            }
            
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            for (Map.Entry<Integer, Mission> entry : GestionSociete.missions.entrySet()){
                
            model.addRow(new Object[] {String.valueOf(entry.getValue().getIdMission()),entry.getValue().getDescription(),entry.getValue().getDateDebutString(),
                        entry.getValue().getDureeJours(),entry.getValue().getNbPersonnes(),entry.getValue().getClassName()} );
            }


            //Competences requises dans la mission

            GestionSociete.fccm = new FileCsvCompetencesMission(dialogue.getSelectedFile().toString() +GestionSociete.FICHIER_COMP_MISSION);

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

            GestionSociete.fcmppc = new FileCsvMissionPersonneParComp(dialogue.getSelectedFile().toString() +GestionSociete.FICHIER_NB_P_COMP_MISSION);

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

            GestionSociete.fcpm = new FileCsvPersonnelMission(dialogue.getSelectedFile().toString() +GestionSociete.FICHIER_PERS_MISSION);

            for(String line : GestionSociete.fcpm.data){

                    String[] data = line.split( new Character(GestionSociete.fcpm.separator()).toString());


                    for (int i=1; i < data.length; ++i){
                        if (GestionSociete.missions.containsKey(Integer.parseInt(data[0]))){
                                MissionModifiable m = (MissionModifiable) GestionSociete.missions.get(Integer.parseInt(data[0]));
                                m.ajouterEmploye(GestionSociete.personnel.get(Integer.parseInt(data[i])));
                            }
                    }   
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
       
       if (GestionSociete.missions.isEmpty()){ 
            int validerSuppr = JOptionPane.showConfirmDialog(null, "Vous allez ajouter des missions sans connaître si elles existent déjà dans votre fichier car il n'est pas chargé!", "Attention", JOptionPane.YES_NO_OPTION);
            if (validerSuppr == 0){
                new AjoutMission().setVisible(true);
                this.dispose();
            }
       }
       else{
            new AjoutMission().setVisible(true);
                this.dispose();
       }
           
       
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        GestionSociete GS = new GestionSociete();
        this.dispose();
        GS.setVisible(true);
       
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        console.setText(null);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        
        new DetailsMission().setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        if(jTable2.getSelectedRow()>-1){
            //System.out.print(jTable1.getValueAt(jTable1.getSelectedRow(),0));
            
            jButton12.setVisible(false);
            jButton15.setVisible(false);
            jButton10.setVisible(false);
            jButton2.setVisible(false);
            
            GestionSociete.rowID = Integer.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),0).toString());
            
            if (GestionSociete.missions.get(GestionSociete.rowID) instanceof MissionModifiable){
                jButton12.setVisible(true);
                jButton15.setVisible(true);
                jButton10.setVisible(true);
                jButton2.setVisible(true);
            }
            else {
                jButton15.setVisible(true);
                jButton10.setVisible(true);
                
            }
        } 
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        new ModificationMission().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        int validerSuppr = JOptionPane.showConfirmDialog(null, "Voulez-Vous supprimer la mission N° "+ jTable2.getValueAt(jTable2.getSelectedRow(),0), "Attention", JOptionPane.YES_NO_OPTION);
        if (validerSuppr == 0){
            //System.out.print(jTable1.getValueAt(jTable1.getSelectedRow(),0));
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            GestionSociete.missions.remove(Integer.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),0).toString()));
            model.removeRow(jTable2.getSelectedRow());
            jTable2.setModel(model);
            jButton15.setVisible(false);
            jButton12.setVisible(false);
            jButton10.setVisible(false);
            jButton2.setVisible(false);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new AffectationMission().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        GestionMissions.date = (Date) this.jFormattedTextField1.getValue();
        /* Code de mise à jour */

        GestionSociete.missions = GestionMissions.updateMissions(GestionSociete.missions, date);
        
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        
        for (Map.Entry<Integer, Mission> entry : GestionSociete.missions.entrySet())
                model.addRow(new Object[] {String.valueOf(entry.getValue().getIdMission()),entry.getValue().getDescription(),entry.getValue().getDateDebutString(),
                            entry.getValue().getDureeJours(),entry.getValue().getNbPersonnes(),entry.getValue().getClassName()} );
            
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea console;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
