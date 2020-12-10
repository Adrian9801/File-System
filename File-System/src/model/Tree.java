/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author adri-
 */
public class Tree {
    private Node root;

    public Tree() {
        this.root = new Node("root", "Directorio", getHour());
    }
    
    public void addArchivo(String pDireccion, String pNombre, String pExtencion, int pSize){
        Node newNode = new Node(pNombre, "Archivo", getHour());
        newNode.setExtension(pExtencion);
        newNode.setSize(pSize);
        Node directorioPadre = getNodePadre(pDireccion);
        directorioPadre.addChild(newNode);
    }
   
    public void addDirectorio(String pDireccion, String pNombre){
        Node newNode = new Node(pNombre, "Directorio", getHour());
        Node directorioPadre = getNodePadre(pDireccion);
        directorioPadre.addChild(newNode);
    }
    
    public ArrayList<Integer> eliminarElemento(String pDireccion){
        ArrayList<Integer> deletePointers = new ArrayList<Integer>();
        Node nodo = getNodePadre(pDireccion);
        if(nodo.getType().compareToIgnoreCase("Directorio") == 0){
            recorrido(nodo, deletePointers);
        }
        else{
            deletePointers.addAll(nodo.getPunteros());
        }
        return deletePointers;
    }
    
    private void recorrido(Node nodoActual, ArrayList<Integer> deletePointers){
        for (int i = 0; i < nodoActual.getChilds().size(); i++) {
            if(nodoActual.getChilds().get(i).getType().compareToIgnoreCase("Directorio") == 0){
                recorrido(nodoActual.getChilds().get(i), deletePointers);
            }
            else{
                deletePointers.addAll(nodoActual.getChilds().get(i).getPunteros());
            }
        }
    }
    
    private Node getNodePadre(String pDireccion){
        String[] partsDir = pDireccion.split("/");
        Node directorioPadre = this.root;
        for (int i = 1; i < partsDir.length; i++) {
            for (int j = 0; j < directorioPadre.getChilds().size(); j++) {
                if(directorioPadre.getChilds().get(j).getName().compareToIgnoreCase(partsDir[i]) == 0){
                   directorioPadre =  directorioPadre.getChilds().get(j);
                   break;
                }
            }
        }
        return directorioPadre;
    }
    
    private String getHour(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
    }
}
