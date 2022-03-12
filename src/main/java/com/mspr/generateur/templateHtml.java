/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mspr.generateur;

import java.util.HashMap;

/**
 *
 * @author abdel
 */
public class templateHtml {
   
    
    public static String buildAgentHtml(agents agent, String contenu){
        StringBuilder concatenes = new StringBuilder();
        
        String chaineHtml;
        
        concatenes.append("<!DOCTYPE html><html><head><meta charset=\"utf-8\" /><link rel=\"stylesheet\" href=\"..\\css\\style.css\"/><title>Agent ").append(agent.getPrenom()).append("</title></head><body><br><br><br><br><br><br><h2> Agent : ").append(agent.getPrenom()).append(" ").append(agent.getNom()).append(" </h2><br><br><img class=\"photoId\" src=\"").append(agent.getpathPhotoID()).append("\"  alt=\"PiÃ¨ce d'identitÃ© de ").append(agent.getPrenom()).append(" ").append(agent.getNom()).append("\" /><br><br>").append(contenu).append("<a href=\"..\\..\\index.html\"><img class=\"logoSociete\" src=\"..\\..\\logo\\logo.png\", alt=\"Le logo de la sociÃ©tÃ©\"></a></div><div class=boutonRetour><a href=\"..\\..\\index.html\"> <img src= \"..\\..\\logo\\logoRetour.png\", alt=\"Logo retour\"> </a></div><footer><a href=\"..\\..\\index.html\">Accueil</a><br><br><a href=\"mailto:bastien.jarre@epsi.fr\"> Nous contacter </a><br><br><a href=\"..\\..\\mentionsLegales.html\"> Mentions lÃ©gales </a></footer></body></html>");
        
        chaineHtml = concatenes.toString();
        return chaineHtml;
    }
    
    public static String generateListEquipement(agents agent,HashMap<String,equipements> mapEquipements){
        
        String listeEquipementsUtiliser= "<div class=tableau><table>";
        int tailleBufferString = agent.getbufferEquipementUtiliser().length;
        
        
        for(int index = 1;index < tailleBufferString; index++){
            try{
                listeEquipementsUtiliser = listeEquipementsUtiliser+"<tr><td>"+mapEquipements.get(agent.getbufferEquipementUtiliser()[index]).getDescription()+"</td></tr>\n";
            }catch(Exception e){
                listeEquipementsUtiliser = "<div class=tableau><table><tr><td>aucuns equipements</td></tr>";
            }
           
        }
        listeEquipementsUtiliser = listeEquipementsUtiliser+"</table></div>";
        return listeEquipementsUtiliser;
    }
    
    
}

