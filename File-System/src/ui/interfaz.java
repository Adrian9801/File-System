/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controlador;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author adri-
 */
public class interfaz extends javax.swing.JFrame {
    
    private Controlador controlador;
    private JPopupMenu popupMenu;
    private DefaultTableModel model;
    private DefaultTreeModel modelTree;
    private DefaultMutableTreeNode rootTree;
    private boolean busqueda;
    
    public interfaz() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        popupTable();
        eventosMouse();
        crearDisco();
        editarElementos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("Agregar carpeta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar archivo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("root");

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("File System");

        jTextField1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jButton4)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(317, 317, 317))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(154, 154, 154))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String title = controlador.irAtras();
        jLabel1.setText(title);
        cargarCarpeta();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String nombre = jTextField1.getText();
        if(nombre.isEmpty())
            JOptionPane.showMessageDialog(this, "Ingrese un documento que buscar", "Error",JOptionPane.ERROR_MESSAGE);
        else{
            while(model.getRowCount() > 0){
                model.removeRow(0);
            }
            ArrayList<String[]> listDocument = controlador.buscarDocument(nombre);
            for (int i = 0; i < listDocument.size(); i++) {
                model.addRow(new Object[]{listDocument.get(i)[0], listDocument.get(i)[1]});
            }
            busqueda = true;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        addFiles addFile = new addFiles(this,true);
        addFile.setLocationRelativeTo(null);
        addFile.setVisible(true);     
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addDirectory addDirect = new addDirectory(this,true);
        addDirect.setLocationRelativeTo(null);
        addDirect.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void popupTable(){
        popupMenu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("Abrir");
        JMenuItem item2 = new JMenuItem("Copiar");
        JMenuItem item3 = new JMenuItem("Mover");
        JMenuItem item4 = new JMenuItem("Ver propiedades");
        JMenuItem item5 = new JMenuItem("Eliminar");
        
        item1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                abrirDocument(jTable1.getSelectedRow());
            }
        });
        
        item2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
                TreeNode[] a = node.getPath();
                for (int i = 0; i < a.length; i++) {
                    TreeNode treeNode = a[i];
                    System.out.println(a[i].toString());
                }
            }
        });
        
        item3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mover(jTable1.getSelectedRow());
            }
        });
        
        item4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                verPropiedades(jTable1.getSelectedRow());
            }
        });
        
        item5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                eliminar(jTable1.getSelectedRow());
            }
        });
        
        popupMenu.add(item1);
        popupMenu.add(item2);
        popupMenu.add(item3);
        popupMenu.add(item4);
        popupMenu.add(item5);
    }
    
    private void editarElementos(){
        Image img = new ImageIcon("data/rehacer.png").getImage();
        ImageIcon icBtn = new ImageIcon(img.getScaledInstance(jButton3.getWidth(), jButton3.getHeight(), Image.SCALE_SMOOTH));
        jButton3.setIcon(icBtn);
        model = (DefaultTableModel) jTable1.getModel();
        modelTree = (DefaultTreeModel) jTree1.getModel();
        rootTree = (DefaultMutableTreeNode) jTree1.getModel().getRoot();
        busqueda = false;
    }
    
    private void eliminar(int pSelectDocument){
        controlador.eliminarElemento(pSelectDocument, busqueda);
        model.removeRow(pSelectDocument);
        limpiarArbol();
    }
    
    public void eliminarNombre(String pNombre) {
        eliminar(controlador.getPos(pNombre));
        limpiarArbol();
    }
    
    private void mover(int pSelectDocument){
        moveDocuments moveDocument = new moveDocuments(this,true);
        moveDocument.setLocationRelativeTo(null);
        moveDocument.mostrarCarpetas("root", controlador, controlador.getDir(pSelectDocument, busqueda));
        moveDocument.setVisible(true);
    }
    
    public boolean moverArchivo(String pDireccion, String pDireccionDestino, String pNombre){
        if(controlador.moverElemento(pDireccion, pDireccionDestino, pNombre)){
            limpiarArbol();
            cargarCarpeta();
            return true;
        }
        return false;
    }
    
    public void eliminarDir(String pDirPadre, String pNombre){
        controlador.eliminarEnDir(pDirPadre, pNombre);
    }
    
    public boolean crearCarpeta(String pNombre) {
        if(busqueda)
            cargarCarpeta();
        if(!controlador.crearCarpeta(pNombre))
            return false;
        model.addRow(new Object[]{pNombre, "Carpeta"});
        limpiarArbol();
        return true;
    }
    
    private void cargarCarpeta(){
        while(model.getRowCount() > 0){
            model.removeRow(0);
        }
        ArrayList<String[]> listDocument = controlador.getCarpetaDocuments();
        for (int i = 0; i < listDocument.size(); i++) {
            model.addRow(new Object[]{listDocument.get(i)[0], listDocument.get(i)[1]});
        }
        jTextField1.setText("");
        busqueda = false;
    }
    
    private void limpiarArbol(){
        rootTree.removeAllChildren();
        modelTree.reload();
        recargarArbol(rootTree, "root");
        
    }
    
    private void recargarArbol(DefaultMutableTreeNode pPadre, String pDir){
        ArrayList<String[]> childs = controlador.getCarpetaDocuments(pDir);
        for (int i = 0; i < childs.size(); i++) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(childs.get(i)[0]);
            modelTree.insertNodeInto(child, pPadre, pPadre.getChildCount());
            jTree1.scrollPathToVisible(new TreePath(child.getPath()));
            String pNewDir = pDir + "/"+ childs.get(i)[0];
            if(controlador.isDirectorio(pNewDir))
                recargarArbol(child, pNewDir);
        }
    }
    
    private void verPropiedades(int pSelectDocument){
        viewProperties viewPropertiesWindow = new viewProperties(this,true);
        viewPropertiesWindow.setLocationRelativeTo(null);
        viewPropertiesWindow.setPropiedades(controlador.getPropiedades(pSelectDocument, busqueda));
        viewPropertiesWindow.setVisible(true);
    }
    
    private void abrirDocument(int pSelectDocument){
        if(controlador.isDirectorio(pSelectDocument, busqueda)){
            String title = controlador.abrirDirectorio(pSelectDocument, busqueda);
            jLabel1.setText(title);
            cargarCarpeta();
        }
        else{
            String[] datos = controlador.abrirArchivo(pSelectDocument, busqueda);
            viewFiles viewFile = new viewFiles(this,true);
            viewFile.setLocationRelativeTo(null);
            viewFile.setFile(datos[0], datos[1]);
            viewFile.setVisible(true);
        }
    }
    
    public boolean modificarArchivo(String pCont){
        return controlador.modificarArchivo(jTable1.getSelectedRow(), pCont, busqueda);
    }
    
    public int crearArchivo(String pNombre, String pExtension, String pCont) {
        if(busqueda)
            cargarCarpeta();
        int num = controlador.crearArchivo(pNombre, pExtension, pCont);
        if(num == 2){
            model.addRow(new Object[]{pNombre.concat(pExtension), "Archivo"});
            limpiarArbol();
        }
        return num;
    }
    
    private void crearDisco(){
        try{
            int numSectores = Integer.valueOf(JOptionPane.showInputDialog("Numero de sectores:"));
            int sizeSector = Integer.valueOf(JOptionPane.showInputDialog("Tamaño de sector:"));
            controlador = new Controlador(numSectores, sizeSector);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ingrese numero enteros positivos", "Error",JOptionPane.ERROR_MESSAGE);
            crearDisco();
        }
    }
    
    private void eventosMouse(){
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int r = jTable1.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < jTable1.getRowCount()) {
                        jTable1.setRowSelectionInterval(r, r);
                        popupMenu.show(jTable1, e.getX(), e.getY());
                    } else
                        jTable1.clearSelection();
                }
                else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int r = jTable1.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < jTable1.getRowCount()) {
                        jTable1.setRowSelectionInterval(r, r);
                        abrirDocument(jTable1.getSelectedRow());
                    } else
                        jTable1.clearSelection();
                }
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

}
