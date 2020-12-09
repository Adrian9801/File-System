/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adri-
 */
public class Controlador {
    private int sizeSector;
    private int cantSectores;
    
    public Controlador(int pCantidadSectores, int pSizeSector){
        this.sizeSector = pSizeSector;
        this.cantSectores = pCantidadSectores;
        crearDisco();
    }
    
    public void crearArchivo(String pNombre, String pExtension, String pDireccion){
        
    }
    
    public void crearCarpeta(String pNombre, String pDireccion){
        
    }
    
    public void eliminarElemento(String pDireccion){
        
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
}
