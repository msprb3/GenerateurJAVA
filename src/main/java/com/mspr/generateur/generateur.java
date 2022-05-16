/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mspr.generateur;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
/**
 *
 * @author abdel
 */


public class generateur {
    public static void main(String [] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        //Parsing des fichier ===========================================================================
        
        //list.txt
        HashMap<String,equipements> mapEquipement = new HashMap<>();

        mapEquipement = parseFichierTxt.parseliste();
        
        //Staff.txt et fiche agent 
        List<agents> listAgents = new ArrayList<>();
        
        listAgents = parseFichierTxt.parseAgent();
         
        //######################################################################
        //Generation des pages html agent.html
        
        String nom;
        String prenom;
        String pathPhotoID;
        String pathNomFichierHtml;
        String contenuHtml;
        
        
        for( int index = 0; index < listAgents.size(); index++)
        {
                nom = listAgents.get(index).getAlias();
                prenom = listAgents.get(index).getNom();
                pathPhotoID = listAgents.get(index).getpathPhotoID();
                   
                            
                contenuHtml = templateHtml.buildAgentHtml(listAgents.get(index),templateHtml.generateListEquipement(listAgents.get(index), mapEquipement));
                
                pathNomFichierHtml = "/var/jenkins_home/workspace/java_executor/javamspr/"+listAgents.get(index).getAlias()+".html";
                
                StringBuilder ecrire = new StringBuilder();
                ecrire.append(contenuHtml);
                FileWriter fstream = new FileWriter(pathNomFichierHtml);
                    BufferedWriter out = new BufferedWriter(fstream);
                out.write(ecrire.toString());
                out.close();
                
                System.out.println("La création de la fiche html de l\'agent "+listAgents.get(index).getNom()+" s'est deroulée avec succès");
        }
                
    }
  
}

