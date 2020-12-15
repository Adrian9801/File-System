/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author adri-
 */
public class Node {
    private ArrayList<Node> childs;
    private ArrayList<Integer> punteros;
    private String name;
    private String type;
    private String extension;
    private int size;
    private String fechaCreacion;
    private String fechaModificacion;

    public Node(String name, String type, String pFechaCreacion) {
        this.name = name;
        this.type = type;
        this.size = 0;
        this.fechaCreacion = pFechaCreacion;
        this.fechaModificacion = pFechaCreacion;
        this.childs = new ArrayList<Node>();
        this.punteros = new ArrayList<Integer>();
    }

    public ArrayList<Node> getChilds() {
        return childs;
    }

    public void addChild(Node child) {
        this.childs.add(child);
    }

    public ArrayList<Integer> getPunteros() {
        return punteros;
    }

    public void setPunteros(int puntero) {
        this.punteros.add(puntero);
    }
    
    public void setNewPunteros(ArrayList<Integer> pPunteros) {
        this.punteros = pPunteros;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
