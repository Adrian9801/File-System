/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controlador;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author adri-
 */
public class treeDocuments extends javax.swing.JDialog {

    private DefaultTreeModel modelTree;
    private DefaultMutableTreeNode rootTree;
    private String extension;
    private String dirAntigua;
    private int eleccion;
    
    public treeDocuments(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        modelTree = (DefaultTreeModel) jTree1.getModel();
        rootTree = (DefaultMutableTreeNode) jTree1.getModel().getRoot();
        extension = "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Seleccione la carpeta destino");

        jButton1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton1.setText("Mover");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Nombre:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addGap(50, 50, 50)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        if(node != null){
            switch(eleccion){
                case 0:
                    if(!jTextField1.getText().isEmpty()){
                        if(jTextField1.getText().lastIndexOf(".") == -1 && jTextField1.getText().lastIndexOf("/") == -1){
                            interfaz parent = (interfaz)this.getParent();
                            String dir = "root";
                            TreeNode[] dirNode = node.getPath();
                            for (int i = 1; i < dirNode.length; i++) {
                                dir += "/" + dirNode[i].toString();
                            }
                            if(parent.moverArchivo(dirAntigua, dir, jTextField1.getText() + extension))
                                dispose();
                            else{
                                int result = JOptionPane.showConfirmDialog(this,"El nombre del documento ya existe. ¿Desea que sea reemplazado?",null, JOptionPane.YES_NO_OPTION);
                                if(result == JOptionPane.YES_OPTION){
                                    parent.eliminarDir(dir, jTextField1.getText() + extension);
                                    parent.moverArchivo(dirAntigua, dir, jTextField1.getText() + extension);
                                    dispose();
                                }
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(this, "Ingrese un nombre valido.", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(this, "Ingrese un nombre.", "Error",JOptionPane.ERROR_MESSAGE);
                    break;
                case 1:
                    interfaz parent = (interfaz)this.getParent();
                    String dir = "root";
                    TreeNode[] dirNode = node.getPath();
                    for (int i = 1; i < dirNode.length; i++) {
                        dir += "/" + dirNode[i].toString();
                    }
                    if(parent.copiarAFS(dir, dirAntigua))
                        dispose();
                    else{
                        int result = JOptionPane.showConfirmDialog(this,"El nombre del documento ya existe. ¿Desea que sea reemplazado?",null, JOptionPane.YES_NO_OPTION);
                        if(result == JOptionPane.YES_OPTION){
                            int pos = dirAntigua.lastIndexOf("/");
                            String nombre = dirAntigua.substring(pos+1);
                            parent.eliminarDir(dir, nombre);
                            parent.copiarAFS(dir, dirAntigua);
                            dispose();
                        }
                    }
                    break;
                default:
                    interfaz parents = (interfaz)this.getParent();
                    String dirNew = "root";
                    TreeNode[] dirNodes = node.getPath();
                    for (int i = 1; i < dirNodes.length; i++) {
                        dirNew += "/" + dirNodes[i].toString();
                    }
                    if(parents.copiarToFS(dirNew, dirAntigua))
                        dispose();
                    else{
                        int result = JOptionPane.showConfirmDialog(this,"El nombre del documento ya existe. ¿Desea que sea reemplazado?",null, JOptionPane.YES_NO_OPTION);
                        if(result == JOptionPane.YES_OPTION){
                            int pos = dirAntigua.lastIndexOf("\\");
                            String nombre = dirAntigua.substring(pos+1);
                            parents.eliminarDir(dirNew, nombre);
                            parents.copiarToFS(dirNew, dirAntigua);
                            dispose();
                        }
                    }
                    break;
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Seleccione un directorio destino.", "Error",JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void mostrarCarpetas(String pDir, Controlador pControlador, String pDirMove){
        int pos = pDirMove.lastIndexOf("/");
        String nombre = pDirMove.substring(pos+1);
        if(!pControlador.isDirectorio(pDirMove)){
            pos = nombre.lastIndexOf(".");
            extension = nombre.substring(pos);
            nombre = nombre.substring(0, pos);
        }
        jTextField1.setText(nombre);
        dirAntigua = pDirMove;
        eleccion = 0;
        cargarCarpetas(rootTree, pDir, pControlador, pDirMove);
    }
    
    public void copiarCargar(String pDir, Controlador pControlador, String pDirCopy){
        jTextField1.setVisible(false);
        jLabel2.setVisible(false);
        dirAntigua = pDirCopy;
        jButton1.setText("Copiar");
        eleccion = 1;
        cargarCarpetas(rootTree, pDir, pControlador, pDirCopy);
    }
    
    public void copiarRRCargar(String pDir, Controlador pControlador, String pDirCopy){
        jTextField1.setVisible(false);
        jLabel2.setVisible(false);
        dirAntigua = pDirCopy;
        jButton1.setText("Copiar");
        eleccion = 2;
        cargarCarpetas(rootTree, pDir, pControlador, "");
    }
    
    private void cargarCarpetas(DefaultMutableTreeNode pPadre, String pDir, Controlador pControlador, String pDirNew){
        ArrayList<String[]> childs = pControlador.getCarpetaDocuments(pDir);
        for (int i = 0; i < childs.size(); i++) {
            String pNewDir = pDir + "/"+ childs.get(i)[0];
            if(!pControlador.isDirectorio(pNewDir) || (pDirNew.compareToIgnoreCase(pNewDir) == 0 && eleccion == 0))
                continue;
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(childs.get(i)[0]);
            modelTree.insertNodeInto(child, pPadre, pPadre.getChildCount());
            jTree1.scrollPathToVisible(new TreePath(child.getPath()));
            cargarCarpetas(child, pNewDir, pControlador, pDirNew);
        }
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
            java.util.logging.Logger.getLogger(treeDocuments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(treeDocuments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(treeDocuments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(treeDocuments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                treeDocuments dialog = new treeDocuments(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
