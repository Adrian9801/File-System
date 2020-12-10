/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tree;

/**
 *
 * @author adri-
 */
public class Controlador {
    private int sizeSector;
    private int cantSectores;
    private int cantSectLibres;
    private ArrayList<String> listDocuments;
    private String dirActual;
    private Tree mem;
    
    public Controlador(int pCantidadSectores, int pSizeSector){
        this.dirActual = "root";
        this.sizeSector = pSizeSector;
        this.cantSectores = pCantidadSectores;
        this.cantSectLibres = pCantidadSectores;
        this.listDocuments = new ArrayList<String>();
        mem = new Tree();
        crearDisco();
    }
    
    public int crearArchivo(String pNombre, String pExtension, String pCont){
        for (int i = 0; i < listDocuments.size(); i++) {
            if(listDocuments.get(i).compareToIgnoreCase(pNombre.concat(pExtension)) == 0)
                return 0;
        }
        ArrayList<Integer> punteros = agregarContDisco(pCont);
        if(punteros.isEmpty())
            return 1;
        mem.addArchivo(dirActual, pNombre.concat(pExtension), pExtension, pCont.length(), punteros);
        listDocuments.add(pNombre.concat(pExtension));
        return 2;
    }
    
    public boolean crearCarpeta(String pNombre){
        for (int i = 0; i < listDocuments.size(); i++) {
            if(listDocuments.get(i).compareToIgnoreCase(pNombre) == 0)
                return false;
        }
        mem.addDirectorio(dirActual, pNombre);
        listDocuments.add(pNombre);
        return true;
    }
    
    public void eliminarElemento(int numDocument){
        ArrayList<Integer> punteros = mem.eliminarElemento(dirActual, listDocuments.get(numDocument));
        limpiarSectores(punteros);
        listDocuments.remove(numDocument);
    }
    
    //metodo verificar si es carpeta o archivo
    
    public void abrirArchivo(int numDocument){
       // nodo = mem.GetArch(dirActual.concat("/"+listDocuments.get(numDocument)));
       
        
    }
    
    public int getPos(String pNombre){
        for (int i = 0; i < listDocuments.size(); i++) {
            if(listDocuments.get(i).compareToIgnoreCase(pNombre) == 0)
                return i;
        }
        return -1;
    }
    
    public void abrirDirectorio(int numDocument){
        dirActual = dirActual.concat("/"+listDocuments.get(numDocument));
       // nodo = mem.GetArch(dirActual.concat("/"+listDocuments.get(numDocument)));
        
        
    }
    
    public void moverElemento(){
        
    }
    
    public void copiarElemento(){
        
    }
    
    public ArrayList<String> getPropiedades(int numDocument){
        ArrayList<String> propiedades = mem.getPropiedades(dirActual.concat("/"+listDocuments.get(numDocument)));
        return propiedades;
    }
    
    public boolean isDirectorio(int numDocument){
        return mem.isDirectorio(dirActual.concat("/"+listDocuments.get(numDocument)));
    }
    
    private void crearDisco(){
        try {
            File file = new File("Disco.txt");
            file.delete();
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String sector = "";
            for (int i = 0; i < sizeSector; i++) {
                sector = sector.concat("0");
            }
            String infoDisco = sector;
            sector = "|".concat(sector);
            for (int i = 1; i < cantSectores; i++) {
                infoDisco = infoDisco.concat(sector);
            }
            bw.write(infoDisco);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<Integer> agregarContDisco(String pCont){
        int cantNecesariaSectores = pCont.length() / sizeSector;
        if(cantNecesariaSectores == 0 || (pCont.length()%sizeSector) != 0)
            cantNecesariaSectores++;
        ArrayList<Integer> punteros = new ArrayList<Integer>();
        if(cantNecesariaSectores <= cantSectLibres){
            File f = new File("Disco.txt"); 
            RandomAccessFile r; 
            int pointer = 0;
            try {
                r = new RandomAccessFile(f,"rw");
                for (int i = cantNecesariaSectores; i > 0;) {
                    char charRead = (char)r.readByte();
                    if(charRead == '0'){
                        r.seek(pointer);
                        if(pCont.length() > sizeSector){
                            r.write(pCont.substring(0, sizeSector).getBytes());
                            pCont = pCont.substring(sizeSector);
                        }
                        else if(pCont.length() > 0)
                            r.write(pCont.getBytes());
                        else
                            r.write("*".getBytes());
                        punteros.add(pointer);
                        i--;
                    }
                    pointer += sizeSector + 1;
                    r.seek(pointer);
                }
                r.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            cantSectLibres -= cantNecesariaSectores;
        }
        return punteros;
    }
    
    private void limpiarSectores(ArrayList<Integer> pPunteros){
        String sectorLimpio = "";
        cantSectLibres += pPunteros.size();
        for (int i = 0; i < sizeSector; i++) {
            sectorLimpio = sectorLimpio.concat("0");
        }
        try {
            File f = new File("Disco.txt"); 
            RandomAccessFile r; 
            r = new RandomAccessFile(f,"rw");
            for (int i = 0; i < pPunteros.size(); i++) {
                r.seek(pPunteros.get(i));
                r.write(sectorLimpio.getBytes());
            }
            r.close();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
