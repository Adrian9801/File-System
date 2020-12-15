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
    
    public void addArchivo(String pDireccion, String pNombre, String pExtencion, int pSize, ArrayList<Integer> pPunteros){
        Node newNode = new Node(pNombre, "Archivo", getHour());
        newNode.setExtension(pExtencion);
        newNode.setNewPunteros(pPunteros);
        Node directorioPadre = getNodePadre(pDireccion, pSize);
        newNode.setSize(pSize);
        directorioPadre.addChild(newNode);
    }
   
    public void addDirectorio(String pDireccion, String pNombre){
        Node newNode = new Node(pNombre, "Directorio", getHour());
        Node directorioPadre = getNodePadre(pDireccion, 0);
        directorioPadre.addChild(newNode);
    }
    
    public boolean isDirectorio(String pDireccion){
        Node nodo = getNodePadre(pDireccion, 0);
        return nodo.getType().compareToIgnoreCase("Directorio") == 0;
    }
    
    public ArrayList<String[]> buscarDocument(String pNombre){
        String dir = "root";
        if(pNombre.contains("*."))
            pNombre = pNombre.substring(1);
        ArrayList<String[]> listDir = new ArrayList<String[]>();
        recorridoBusqueda(this.root, dir, listDir, pNombre);
        return listDir;
    }
    
    public boolean moverDocument(String pDireccion, String pDireccionDestino, String pNombre){
        if(pDireccion.compareToIgnoreCase(pDireccionDestino+"/"+pNombre) != 0){
            int pos = pDireccion.lastIndexOf("/");
            String nombreViejo = pDireccion.substring(pos+1);
            String dir = pDireccion.substring(0, pos);
            Node nodo = getNodePadre(pDireccionDestino, 0);
            ArrayList<Node> childs = nodo.getChilds();
            for (int i = 0; i < childs.size(); i++) {
                if(childs.get(i).getName().compareToIgnoreCase(pNombre) == 0)
                    return false; 
            }
            Node nodeMove = getNodePadre(pDireccion, 0);
            Node nodePadre = getNodePadre(dir, -nodeMove.getSize());
            childs = nodePadre.getChilds();
            for (int i = 0; i < childs.size(); i++) {
                if(childs.get(i).getName().compareToIgnoreCase(nombreViejo) == 0){
                    childs.remove(i);
                    break;
                }    
            }
            nodeMove.setName(pNombre);
            nodo.addChild(nodeMove);
            getNodePadre(pDireccionDestino, nodeMove.getSize());
        }
        return true;
    }
    
    public ArrayList<String[]> getCarpetaDocuments(String pDireccion){
        ArrayList<String[]> listDir = new ArrayList<String[]>();
        Node nodo = getNodePadre(pDireccion, 0);
        for (int i = 0; i < nodo.getChilds().size(); i++) {
            String[] dir = {nodo.getChilds().get(i).getName(), nodo.getChilds().get(i).getType()};
            listDir.add(dir);
        }
        return listDir;
    }
    
    public ArrayList<String> getPropiedades(String pDireccion){
        ArrayList<String> propiedades = new ArrayList<String>();
        Node nodo = getNodePadre(pDireccion, 0);
        propiedades.add(nodo.getName());
        if(nodo.getType().compareToIgnoreCase("Archivo") == 0)
            propiedades.add(nodo.getExtension());
        propiedades.add(nodo.getFechaCreacion());
        propiedades.add(nodo.getFechaModificacion());
        propiedades.add(Integer.toString(nodo.getSize()));
        return propiedades;
    }
    
    public ArrayList<Integer> eliminarElemento(String pDireccion, String pNombre){
        ArrayList<Integer> deletePointers = new ArrayList<Integer>();
        Node nodo = getNodePadre(pDireccion.concat("/"+pNombre), 0);
        int size = - nodo.getSize();
        Node nodoPadre = getNodePadre(pDireccion, size);
        if(nodo.getType().compareToIgnoreCase("Directorio") == 0)
            recorrido(nodo, deletePointers);
        else
            deletePointers.addAll(nodo.getPunteros());
        for (int i = 0; i < nodoPadre.getChilds().size(); i++) {
            if(nodoPadre.getChilds().get(i).getName().compareToIgnoreCase(pNombre) == 0){
                nodoPadre.getChilds().remove(i);
                break;
            }
        }
        return deletePointers;
    }
    
    public ArrayList<Integer> getPunteros(String pDireccion){
        ArrayList<Integer> pointers = new ArrayList<Integer>();
        Node nodo = getNodePadre(pDireccion, 0);
        if(nodo.getType().compareToIgnoreCase("Directorio") == 0)
            recorrido(nodo, pointers);
        else
            pointers.addAll(nodo.getPunteros());
        return pointers;
    }
    
    private void recorrido(Node nodoActual, ArrayList<Integer> pPointers){
        for (int i = 0; i < nodoActual.getChilds().size(); i++) {
            if(nodoActual.getChilds().get(i).getType().compareToIgnoreCase("Directorio") == 0)
                recorrido(nodoActual.getChilds().get(i), pPointers);
            else
                pPointers.addAll(nodoActual.getChilds().get(i).getPunteros());
        }
    }
    
    private Node getNodePadre(String pDireccion, int pSize){
        String[] partsDir = pDireccion.split("/");
        Node directorioPadre = this.root;
        directorioPadre.setSize(directorioPadre.getSize()+pSize);
        for (int i = 1; i < partsDir.length; i++) {
            for (int j = 0; j < directorioPadre.getChilds().size(); j++) {
                if(directorioPadre.getChilds().get(j).getName().compareToIgnoreCase(partsDir[i]) == 0){
                   directorioPadre =  directorioPadre.getChilds().get(j);
                   directorioPadre.setSize(directorioPadre.getSize()+pSize);
                   break;
                }
            }
        }
        return directorioPadre;
    }
    
    public ArrayList<Integer> abrirArchivo(String pDireccion){
        Node nodo = getNodePadre(pDireccion, 0);
        return nodo.getPunteros();
    }
    
    public void modificarArchivo(String pDireccion, int pSize, ArrayList<Integer> pPunteros){
        Node nodo = getNodePadre(pDireccion, 0);
        getNodePadre(pDireccion, -nodo.getSize() + pSize);
        nodo.setSize(pSize);
        nodo.setFechaModificacion(getHour());
        nodo.setNewPunteros(pPunteros);
    }
    
    private void recorridoBusqueda(Node nodoActual, String pDirActual, ArrayList<String[]> pListDir, String pNombre){
        for (int i = 0; i < nodoActual.getChilds().size(); i++) {
            if(nodoActual.getChilds().get(i).getType().compareToIgnoreCase("Directorio") == 0){
                if((nodoActual.getChilds().get(i).getName().toLowerCase()).contains(pNombre.toLowerCase())){
                    String[] dir = {pDirActual.concat("/"+nodoActual.getChilds().get(i).getName()), "Directorio"}; 
                    pListDir.add(dir);
                }
                recorridoBusqueda(nodoActual.getChilds().get(i), pDirActual.concat("/"+nodoActual.getName()), pListDir, pNombre);
            }
            else{
                if((nodoActual.getChilds().get(i).getName().toLowerCase()).contains(pNombre.toLowerCase())){
                    String[] dir = {pDirActual.concat("/"+nodoActual.getChilds().get(i).getName()), "Archivo"}; 
                    pListDir.add(dir);
                }
            }
        }
    }
    
    private String getHour(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
    }
}
