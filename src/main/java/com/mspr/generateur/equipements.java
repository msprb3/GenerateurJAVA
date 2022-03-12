/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mspr.generateur;

/**
 *
 * @author abdel
 */
public class equipements {
    //Variable==================================================================      
    private String nomEquipement;
    private String description;
    private boolean utilisation;
    
    
    //Constructeur==============================================================
    equipements(){
        this.nomEquipement = "aucun";
        this.description = "null";
        this.utilisation = false;
    }
    
    equipements(String nomEquipement,String description){
        this.nomEquipement = nomEquipement;
        this.description = description;
        this.utilisation = false; 
    }
    //getteur===================================================
    public String getNomEquipement(){//pour la variable nomEquipement 
        return this.nomEquipement;
    }
    
    public String getDescription(){//pour la variable description 
        return this.description;
    }
    
    public boolean getUtilisation(){ //Pour la variable utilisation 
        return this.utilisation;
    }
    
    //Setteur
    public void setNomEquipement(String nomEquipement){
        this.nomEquipement = nomEquipement;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
       
    public void setUtilisation(boolean utilisation){
        this.utilisation = utilisation;
    }
    
    //Methode===================================================================
    public void afficheEquipement(){       
        System.out.println("|> Nom de l\'equipement : "+this.getNomEquipement()+"|> description : "+this.getDescription());
    }
  
}
