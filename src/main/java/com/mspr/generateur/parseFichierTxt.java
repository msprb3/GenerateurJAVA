/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mspr.generateur;

import java.util.*;
import java.io.*;
/**
 *
 * @author adaoudi
 */


public class parseFichierTxt {
    
    
    public static HashMap<String,equipements> parseliste(){
        
        
        String pathListEquipement = "/var/jenkins_home/workspace/java_executor/javamspr/liste.txt"; 
        String nomEquipement = " ";        
        String description = " ";         
        HashMap<String,equipements> mapEquipement = new HashMap<>();
        
        try(BufferedReader BufferEquipements = new BufferedReader(new FileReader(pathListEquipement))) 
        {
            String ligne;
            
            String[] bufferEquipements;
            
            while ((ligne = BufferEquipements.readLine()) != null) 
            {
                    bufferEquipements = ligne.split("\t");
                    
                    nomEquipement = bufferEquipements[0];
                    description = bufferEquipements[1];
                    
                    equipements equipement = new equipements(nomEquipement,description);

                    mapEquipement.put(nomEquipement, equipement);
            }
            equipements equipementRien = new equipements("Vide","Vide");//ceci correspond au fait que l'agent na aucun equipement 
            mapEquipement.put("Vide", equipementRien);
        }
        catch (IOException e) 
        {     
            System.out.println(e);
        }
        
        
        
    return mapEquipement;  
 }
    
    
    
    public static List<agents> parseAgent(){
        
        String pathListStaff = "/var/jenkins_home/workspace/java_executor/javamspr/staff.txt"; 
        
       
        List<agents> listagents = new ArrayList<>();
        
        String[] bufferAgent;
        
        String ligne;
        ligne = "";
        
        String totalLigne;
        totalLigne = "";

        try(BufferedReader BufferStaff = new BufferedReader(new FileReader(pathListStaff))) 
        {
            
            while ((ligne = BufferStaff.readLine()) != null) {
                   totalLigne = totalLigne + ligne + " ";      
            }
            bufferAgent = totalLigne.split(" ");
            
            listagents = parseFichierTxt.parseFichesAgent(bufferAgent);
            
        }
        catch (IOException e) {
            
            System.out.println(e);
            
        }
        
        return listagents;
    }
    
    public static List<agents> parseFichesAgent(String[] bufferStaff) {
        
        boolean presenceFicheAgent;
        
        String alias = "";
         
        String pathPhotoId = "";
        String pathFicheAgent;
        
        
        //La list que notre methode vas retourner 
        List<agents> listagents = new ArrayList<>();
        
        //Ce code sera executer autant de fois que d'agent en intervention (fiche agent présente)
        for (int index =0;index < bufferStaff.length ; index++) {
            
            //On recupère l'alias de l'agent courant. 
            alias = bufferStaff[index];
            pathFicheAgent = "/var/jenkins_home/workspace/java_executor/javamspr/" + alias + ".txt";
            pathPhotoId = "../../Identite/"+alias+".jpg";
            
            //verifFicheAgent(alias) retourne "true" si la fiche agent existe et retourne "false" si elle n'existe pas
            presenceFicheAgent = parseFichierTxt.verifFicheAgent(alias);
            
            //si la fiche agent n'existe pas le code dans le bloc "if" n'est pas executer 
            if (presenceFicheAgent) 
            {   //Si jamais ça suffit pas, on gère les exception (erreurs de lecture) avec try catch 
                try (final BufferedReader bufferFicheAgent = new BufferedReader(new FileReader(pathFicheAgent))) {
                    
                    //Variable pour le besoin interne de ce bloc 
                    String ligne = "";

                    String totalContenuLigne = "";
                    
                    String[] bufferMotFicheAgent;
                    bufferMotFicheAgent = null;                   
                    int taille_bufferMotFicheAgent = 0;
                    
                    
                    int taille_bufferEquipementUtiliser = 0;
                     
                    int emplacement_ligne_vide = 0;
             
                    //Variable a passer dans les paramettre du constructeur de agents() il sont crée ici car si la fiche est pas présente il sont pas crée inutilement
                    String detailIntervention ="";
                    
                    String pass = "";
                    
                    String prenom = "";
                    
                    String nom = "";
                    
                   
                    
                    //Lire le fichier ligne par ligne et enregistrer tous sa dans une seul chaine de caractère separer par des espaces 
                    while ((ligne = bufferFicheAgent.readLine()) != null) 
                    { 
                        totalContenuLigne = totalContenuLigne + ligne + " ";

                    }
                    //la variable est decouper en plusieurs chaine de caractère stocker dans un tableau de String
                    bufferMotFicheAgent = totalContenuLigne.split(" ");
                    
                    //ici on recupère la taille du buffer on aura besoin comme limite pour nos boucles for
                    taille_bufferMotFicheAgent = bufferMotFicheAgent.length;
                    
                     /* ---------------------[traitement sur tableau de string[] bufferMotFicheAgent]---------------------------------
                     *      - case 0 et 1 : Nom / prenom.                                                                            |
                     *      - La ligne au dessus de la ligne vide c'est le mot de passe.                                             |
                     *      - les case entre le prenom et le mot de passe c'est les details sur l'intervention.                      |
                     *      - tous ce qui est après la ligne vide c'est les equipement utiliser par l'agent pour sont intervention.  |
                     *--------------------------------------------------------------------------------------------------------------|
                     */
                    //On recupère le nom
                    nom = bufferMotFicheAgent[0]; 
                    
                    //On recupère le prenom
                    prenom = bufferMotFicheAgent[1];
                    
                    //On recupère le mot de pass
                    for(int index_ligne_vide = 0;index_ligne_vide < taille_bufferMotFicheAgent;index_ligne_vide++)
                    {
                        if(bufferMotFicheAgent[index_ligne_vide].equals(""))
                        {
                            pass = bufferMotFicheAgent[index_ligne_vide-1];
                            emplacement_ligne_vide = index_ligne_vide;
                        }
                    }
                    
                    
                    //On recupère les details de l'intervention 
                    for(int index_fonction = 2; index_fonction <  emplacement_ligne_vide-1; index_fonction++ ){
                        detailIntervention = detailIntervention + bufferMotFicheAgent[index_fonction] +" ";
                    }
                    
                    //On recupère le nom des equipement utiliser par l'agent pour sont intervention 

                    taille_bufferEquipementUtiliser = taille_bufferMotFicheAgent - emplacement_ligne_vide;
                    
                    String[] bufferEquipementUtiliser;
                    
                    bufferEquipementUtiliser = new String[taille_bufferEquipementUtiliser];
                    
                    for(int indexBufferEquipementUtiliser = 0 ;indexBufferEquipementUtiliser < taille_bufferEquipementUtiliser; indexBufferEquipementUtiliser++)
                    {
                        bufferEquipementUtiliser[indexBufferEquipementUtiliser] = bufferMotFicheAgent[emplacement_ligne_vide+indexBufferEquipementUtiliser];
                    }
                    
                    
                   
                    //Création de l'agent 
                    agents agent = new agents(nom, prenom, detailIntervention, pass, alias,pathPhotoId, bufferEquipementUtiliser);
                    
                    listagents.add(agent);
                }catch (IOException e) {
                    
                    System.out.println(e);
                    
                }
            }
            
        }
      
        return listagents;
    }        

    
    public static boolean verifFicheAgent(String alias){
        String pathFicheAgent = "/var/jenkins_home/workspace/java_executor/javamspr/"+alias+".txt";
        File fiche = new File(pathFicheAgent);
        
        return fiche.isFile();
    }


}

