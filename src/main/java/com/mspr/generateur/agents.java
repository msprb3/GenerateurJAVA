/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mspr.generateur;

import java.io.*;
/**
 *
 * @author abdel
 */
public class agents {
     private String nom;
    private String prenom;
    private String detailIntervention;
    private String pass;
    private String alias;
    private String[] bufferEquipementUtiliser;
    private String pathPhotoID;
    
    
    
    agents(){
        this.nom = "null";
        this.prenom = "null";
        this.detailIntervention = "null";
        this.pass = "null";
        this.alias = "null";
        this.bufferEquipementUtiliser = null;

    }
    
    agents(String nom, String prenom, String detailIntervention, String pass,String alias,String pathPhotoID, String[] bufferEquipementUtiliser){
        this.nom = nom;
        this.prenom = prenom;
        this.detailIntervention = detailIntervention;
        this.pass = pass;
        this.alias = alias;
        this.bufferEquipementUtiliser = bufferEquipementUtiliser;
        this.pathPhotoID = pathPhotoID;
        
        
    }
    
//==============================[Geteur]========================================   
    public String getNom(){
        return this.nom;
    }
    
    public String getPrenom(){
        return this.prenom;
    } 
    
    public String getDetailIntervention(){
        return this.detailIntervention;
    }
    
    
    public String getPass(){
        return this.pass;
    }
    public String getAlias(){
        return this.alias;
    }
    
    public String getpathPhotoID(){
        return this.pathPhotoID;
    }
    
    public String[] getbufferEquipementUtiliser(){
        return this.bufferEquipementUtiliser;
    }
    
//============================[Seteur]==========================================
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public void setPrenom(String prenom){
        this.prenom = prenom;
    } 
    
    public void setDetailIntervention(String detailIntervention){
        this.detailIntervention = detailIntervention;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }
    public void setAlias(String alias){
        this.alias = alias;
    }
    
 //=============================================================================   
    
    public void afficheInfoAgents(){
        System.out.println(" |> Nom : "+this.getNom()+"\n |> Prenom : "+this.getPrenom()+"\n |> detail de l'intervention : "+this.getDetailIntervention()+"\n |> Mot de passe : "+this.getPass()+"\n |> Alias : "+this.getAlias()+"\n");
        System.out.println("Liste dequipement utiliser :");
         for (String bufferEquipementUtiliser1 : this.bufferEquipementUtiliser) {
             System.out.println("|"+bufferEquipementUtiliser1+"|");
              System.out.println("-------------------------");
         }
    }
    
    public boolean verifFicheAgent(){
        String pathFicheAgent = "/var/jenkins_home/workspace/java_executor/javamspr"+this.alias+".txt";
        
        File fiche = new File(pathFicheAgent);
  
            return fiche.isFile();
        
    }
    
    public String getPathFicheAgent(String alias){
        String pathFicheAgent = "/var/jenkins_home/workspace/java_executor/javamspr/"+this.alias+".txt";
        
       return pathFicheAgent;
    }
    
    
    
}
