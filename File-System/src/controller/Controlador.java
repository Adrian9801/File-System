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
import javax.swing.JOptionPane;
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
    private ArrayList<Integer> punteroArch;
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
        if(cantSectLibres == 0)
            JOptionPane.showMessageDialog(null, "El disco esta lleno.", "Error",JOptionPane.ERROR_MESSAGE);
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
    
    public void eliminarElemento(int numDocument, boolean isBusqueda){
        ArrayList<Integer> punteros;
        if(isBusqueda){
            int pos = listDocuments.get(numDocument).lastIndexOf("/");
            punteros = mem.eliminarElemento(listDocuments.get(numDocument).substring(0,pos),listDocuments.get(numDocument).substring(pos+1));
        }
        else
            punteros = mem.eliminarElemento(dirActual, listDocuments.get(numDocument));
        limpiarSectores(punteros);
        listDocuments.remove(numDocument);
    }
    
    public void eliminarEnDir(String pDirPadre, String pNombre){
        mem.eliminarElemento(pDirPadre, pNombre);
    }
    
    public String[] abrirArchivo(int numDocument, boolean isBusqueda){
        String nombre = "";
        if(isBusqueda){
            int pos = listDocuments.get(numDocument).lastIndexOf("/");
            nombre = listDocuments.get(numDocument).substring(pos+1);
            punteroArch = mem.abrirArchivo(listDocuments.get(numDocument));
        }
        else{
            nombre = listDocuments.get(numDocument);
            punteroArch = mem.abrirArchivo(dirActual.concat("/"+listDocuments.get(numDocument)));
        }
        String[] datosArch = {nombre, leerDisco(punteroArch)};
        return datosArch;
    }
    
    public int getPos(String pNombre){
        for (int i = 0; i < listDocuments.size(); i++) {
            if(listDocuments.get(i).compareToIgnoreCase(pNombre) == 0)
                return i;
        }
        return -1;
    }
    
    public String abrirDirectorio(int numDocument, boolean isBusqueda){
        if(isBusqueda)
            dirActual = listDocuments.get(numDocument);
        else
            dirActual = dirActual.concat("/"+listDocuments.get(numDocument));
        return dirActual;
    }
    
    public String irAtras(){
        if(dirActual.compareToIgnoreCase("root") != 0){
            int pos = dirActual.lastIndexOf("/");
            dirActual = dirActual.substring(0, pos);
        }
        return dirActual;
    }
    
    public boolean modificarArchivo(int numDocument, String pCont, boolean isBusqueda){
        int size = pCont.length();
        if(modSectores(pCont)){
            if(isBusqueda)
                mem.modificarArchivo(listDocuments.get(numDocument), size, punteroArch);
            else
                mem.modificarArchivo(dirActual.concat("/"+listDocuments.get(numDocument)), size, punteroArch);
            if(cantSectLibres == 0)
                JOptionPane.showMessageDialog(null, "El disco esta lleno.", "Error",JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
    
    public ArrayList<String[]> buscarDocument(String pNombre){
        ArrayList<String[]> list = mem.buscarDocument(pNombre);
        listDocuments.clear();
        for (int i = 0; i < list.size(); i++) {
            listDocuments.add(list.get(i)[0]);
        }
        return list;
    }
    
    public ArrayList<String[]> getCarpetaDocuments(){
        ArrayList<String[]> list = mem.getCarpetaDocuments(dirActual);
        listDocuments.clear();
        for (int i = 0; i < list.size(); i++) {
            listDocuments.add(list.get(i)[0]);
        }
        return list;
    }
    
    public ArrayList<String[]> getCarpetaDocuments(String pDir){
        ArrayList<String[]> list = mem.getCarpetaDocuments(pDir);
        return list;
    }
    
    public boolean moverElemento(String pDireccion, String pDireccionDestino, String pNombre){
        if(mem.moverDocument(pDireccion, pDireccionDestino, pNombre)){
            dirActual = pDireccionDestino;
            return true;
        }
        return false;
    }
    
    public String getDir(int numDocument, boolean isBusqueda){
        if(isBusqueda)
            return listDocuments.get(numDocument);
        return dirActual.concat("/"+listDocuments.get(numDocument));
    }
    
    public void copiarElemento(){
        
    }
    
    public ArrayList<String> getPropiedades(int numDocument, boolean isBusqueda){
        ArrayList<String> propiedades;
        if(isBusqueda)
            propiedades = mem.getPropiedades(listDocuments.get(numDocument));
        else
            propiedades = mem.getPropiedades(dirActual.concat("/"+listDocuments.get(numDocument)));
        return propiedades;
    }
    
    public ArrayList<String> getPropiedades(String pDir){
        ArrayList<String> propiedades = mem.getPropiedades(pDir);
        return propiedades;
    }
    
    public boolean isDirectorio(int numDocument, boolean isBusqueda){
        if(isBusqueda)
            return mem.isDirectorio(listDocuments.get(numDocument));
        return mem.isDirectorio(dirActual.concat("/"+listDocuments.get(numDocument)));
    }
    
    public boolean isDirectorio(String pDir){
        return mem.isDirectorio(pDir);
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
            String infoDisco = sector.concat("|");
            sector = infoDisco;
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
    
    private String leerDisco(ArrayList<Integer> pPunteros){
        String cont = "";
        try {
            File f = new File("Disco.txt"); 
            RandomAccessFile r; 
            r = new RandomAccessFile(f,"rw");
            for (int i = 0; i < pPunteros.size(); i++) {
                r.seek(pPunteros.get(i));
                char charRead = (char)r.readByte();
                if(charRead == '*')
                    break;
                for (int j = pPunteros.get(i)+1; charRead != '0' && charRead != '|'; j++) {
                    cont += charRead;
                    r.seek(j);
                    charRead = (char)r.readByte();
                }
            }
            r.close();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cont;
    }
    
    private boolean modSectores(String pCont){
        int libres = cantSectLibres + punteroArch.size() - (pCont.length() / sizeSector);
        if((pCont.length()%sizeSector) != 0)
            libres--;
        if(libres < 0)
            return false;
        limpiarSectores(punteroArch);
        try {
            File f = new File("Disco.txt"); 
            RandomAccessFile r; 
            r = new RandomAccessFile(f,"rw");
            for (int i = 0; i < punteroArch.size();) {
                r.seek(punteroArch.get(i));
                if(pCont.length() > sizeSector){
                    r.write(pCont.substring(0, sizeSector).getBytes());
                    pCont = pCont.substring(sizeSector);
                }
                else if(pCont.length() > 0){
                    r.write(pCont.getBytes());
                    pCont = "";
                }
                else
                    r.write("*".getBytes());
                i++;
                if(punteroArch.size() == i && pCont.length() > 0){
                    r.close();
                    punteroArch.addAll(agregarContDisco(pCont));
                    cantSectLibres = libres;
                    return true;
                }
                else if(pCont.length() == 0 && punteroArch.size() > i){
                    for (; i == punteroArch.size();) {
                        punteroArch.remove(i);
                    }
                    punteroArch.remove(i);
                    r.close();
                    cantSectLibres = libres;
                    return true;
                }
            }
            r.close();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        cantSectLibres = libres;
        return true;
    }
}
