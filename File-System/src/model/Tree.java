/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;

/**
 *
 * @author adri-
 */
public class Tree {
    private Node root;

    public Tree() {
        this.root = new Node("root","Directorio",new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
    }
    
    public void addArchivo(String pDireccion, String pNombre, String pExtencion){
        Node newNode = new Node(pNombre,pDireccion,new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
        newNode.setExtension(pExtencion);
        
    }
   
    
}
