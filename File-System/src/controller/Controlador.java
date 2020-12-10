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
    
    public boolean crearArchivo(String pNombre, String pExtension, String pCont){
        ArrayList<Integer> punteros = agregarContDisco(pCont);
        if(punteros.size() == 0)
            return false;
        mem.addArchivo(dirActual, pNombre, pExtension, pCont.length());
        listDocuments.add(pNombre);
        return true;
    }
    
    public void crearCarpeta(String pNombre){
        mem.addDirectorio(dirActual, pNombre);
        listDocuments.add(pNombre);
    }
    
    public void eliminarElemento(int numDocument){
        mem.eliminarElemento(dirActual.concat("/"+listDocuments.get(numDocument)));
        
    }
    
    //metodo verificar si es carpeta o archivo
    
    public void abrirArchivo(int numDocument){
       // nodo = mem.GetArch(dirActual.concat("/"+listDocuments.get(numDocument)));
       
        
    }
    
    public void abrirDirectorio(int numDocument){
        dirActual = dirActual.concat("/"+listDocuments.get(numDocument));
       // nodo = mem.GetArch(dirActual.concat("/"+listDocuments.get(numDocument)));
        
        
    }
    
    public void moverElemento(){
        
    }
    
    public void moverCopiar(){
        
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
        if(cantNecesariaSectores == 0)
            cantNecesariaSectores = 1;
        ArrayList<Integer> punteros = new ArrayList<Integer>();
        if(cantNecesariaSectores <= cantSectLibres){
            File f = new File("Disco.txt"); 
            RandomAccessFile r; 
            int pointer = 0;
            try {
                r = new RandomAccessFile(f,"rw");
                r.skipBytes(pointer);
                for (int i = cantNecesariaSectores; i > 0;) {
                    if(r.readChar() == '0'){
                        if(pCont.length() > sizeSector){
                            r.write(pCont.substring(0, sizeSector).getBytes());
                            pCont = pCont.substring(sizeSector);
                        }
                        else if(pCont.length() > 0)
                            r.write(pCont.getBytes());
                        punteros.add(pointer);
                        i--;
                    }
                    pointer += sizeSector + 1;
                    r.skipBytes(pointer);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            cantSectLibres -= cantNecesariaSectores;
        }
        return punteros;
    }
}
