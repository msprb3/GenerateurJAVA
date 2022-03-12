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

/* Ã  chaque itÃ©ration du while :  - lire la ligne courante  
     *                            - deffinition d'un separateur par tabulation 
     *                            - stock le premier mot dans la premiÃ¨re case du tbaleau et le deuxiÃ¨me mot dans la deuxiÃ¨me case
     *                            - initialise une nouvel instance de la classe equipement
     *                            - ajoute le nouvel objet dans la liste d'objet dequipement
    */

public class parseFichierTxt {
    
    
    public static HashMap<String,equipements> parseliste(){
        
        
        String pathListEquipement = "C:/Users/abdel/OneDrive/Documents/NetBeansProjects/MsprJava/Materiel/liste.txt"; 
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
            equipements equipementRien = new equipements("Vide","Vide");
            mapEquipement.put("Vide", equipementRien);
        }
        catch (IOException e) 
        {     
            System.out.println(e);
        }
        
        
        
    return mapEquipement;  
 }
    
    
    
    public static List<agents> parseAgent(){
        
        String pathListStaff = "C:/Users/abdel/OneDrive/Documents/NetBeansProjects/MsprJava/Agents/staff.txt"; 
        
       
        List<agents> listagents = new ArrayList<>();
        
        String[] bufferAgent;
        
        String ligne;
        ligne = "";
        
        String totalLigne;
        totalLigne = "";
        
        

        try(BufferedReader BufferStaff = new BufferedReader(new FileReader(pathListStaff))) 
        {
            
            
            /* a  chaque itération du while :  - lire la ligne courante  
                                              - initialise une nouvel instance de la classe agent avec l'alias de l'agent
                                              - ajoute le nouvel objet dans la liste d'objet agent*/
            
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
        
        //Ce code sera executer autant de fois que d'agent en intervention (fiche agent prÃ©sente)
        for (int index =0;index < bufferStaff.length ; index++) {
            
            //On recupÃ¨re l'alias de l'agent courant. 
            alias = bufferStaff[index];
            pathFicheAgent = "C:/Users/abdel/OneDrive/Documents/NetBeansProjects/MsprJava/FicheAgent/txt/" + alias + ".txt";
            pathPhotoId = "../../Identite/"+alias+".jpg";
            //verifFicheAgent(alias) retourne "true" si la fiche agent existe et retourne "false" si elle n'existe pas
            presenceFicheAgent = parseFichierTxt.verifFicheAgent(alias);
            
            //si la fiche agent n'existe pas le code dans le bloc "if" n'est pas executer 
            if (presenceFicheAgent) 
            {   
                try (final BufferedReader bufferFicheAgent = new BufferedReader(new FileReader(pathFicheAgent))) {
                    
                    //Variable pour le besoin interne de ce bloc 
                    String ligne = "";

                    String totalContenuLigne = "";
                    
                    String[] bufferMotFicheAgent;
                    bufferMotFicheAgent = null;                   
                    int taille_bufferMotFicheAgent = 0;
                    
                    
                    int taille_bufferEquipementUtiliser = 0;
                     
                    int emplacement_ligne_vide = 0;
             
                    //Variable pour les passer dans les paramettre du constructeur de agents() il sont crÃ©e ici car si la fiche est pas présente il sont pas crée inutilement
                    String detailIntervention ="";
                    
                    String pass = "";
                    
                    String prenom = "";
                    
                    String nom = "";
                    
                    
                    //==========================================================
                    
                    //Lire le fichier ligne par ligne et enregistrer tous sa dans une seul variable
                    while ((ligne = bufferFicheAgent.readLine()) != null) 
                    { 
                        totalContenuLigne = totalContenuLigne + ligne + " ";

                    }
                    //la variable est decouper en plusieurs chaine de caractÃ¨re stocker dans un tableau de String
                    bufferMotFicheAgent = totalContenuLigne.split(" ");
                    
                    //ici on recupÃ¨re la taille du buffer on aura besoin pour faire tourner les "for"
                    taille_bufferMotFicheAgent = bufferMotFicheAgent.length;
                    
                    
                    nom = bufferMotFicheAgent[0];
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
                    
                    
                    //On recupère les infos sur l'agent dans la variable intervention
                    for(int index_fonction = 2;index_fonction <  emplacement_ligne_vide-1; index_fonction++ ){
                        detailIntervention = detailIntervention + bufferMotFicheAgent[index_fonction] +" ";
                    }
                    
                    //On recupÃ¨re le nom des equipement utiliser par l'agent pour sont intervention 
                    
                    /*  traitement sur mon buffer -> 
                     *      case 0 et 1 : Nom / prenom 
                     *      La ligne au dessus de la ligne vide c'est le mot de passe 
                     *      les case entre le prenom et le mot de passe c'est les information sur l'intervention 
                     *      tous ce qui est aprÃ¨s la ligne vide c'est les equipement utiliser par l'agent pour sont intervention
                     */
                    taille_bufferEquipementUtiliser = taille_bufferMotFicheAgent - emplacement_ligne_vide;
                    
                    String[] bufferEquipementUtiliser;
                    
                    bufferEquipementUtiliser = new String[taille_bufferEquipementUtiliser];
                    
                    for(int indexBufferEquipementUtiliser = 0 ;indexBufferEquipementUtiliser < taille_bufferEquipementUtiliser; indexBufferEquipementUtiliser++)
                    {
                        bufferEquipementUtiliser[indexBufferEquipementUtiliser] = bufferMotFicheAgent[emplacement_ligne_vide+indexBufferEquipementUtiliser];
                    }
                    
                    
                   

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
        String pathFicheAgent = "C:/Users/abdel/OneDrive/Documents/NetBeansProjects/MsprJava/FicheAgent/txt/"+alias+".txt";
        File fiche = new File(pathFicheAgent);
        
        return fiche.isFile();
    }


}

