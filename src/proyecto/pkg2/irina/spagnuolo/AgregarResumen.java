/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.irina.spagnuolo;
import javax.swing.*;
import java.io.*;

/**
 *
 * 
 */
public class AgregarResumen {
    private HashTable tablaHash;

    public AgregarResumen(HashTable tablaHash) {
        this.tablaHash = tablaHash;
    }
    
    public void cargarArchivo(){
        JFileChooser fileC = new JFileChooser();
        fileC.setDialogTitle("Seleccione un archivo de resumen (que sea un .txt)");
        int resultado = fileC.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileC.getSelectedFile();
            procesarArchivo(archivo);
        }
    }
    
    
    private void procesarArchivo(File archivo){
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;
            String titulo = null;
            String[] autores = new String[10];
            int contadorA = 0;
            StringBuilder cuerpo = new StringBuilder();
            String[] palabrasClave = null;
            
            titulo = br.readLine();
            
            if (titulo == null || titulo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Formato inválido: falta el titulo");
                return;
            }
            
            String tituloNormal = titulo.trim().toLowerCase();

            if (tablaHash.contiene(tituloNormal)) {
                JOptionPane.showMessageDialog(null, "El resumen con el título \"" + titulo + "\" ya ha sido guardado previamente");
                return;
            }
            
            boolean seccionAutores = false;
            while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("Resumen")) {
                if (linea.equalsIgnoreCase("Autores")) {
                    seccionAutores = true;
                    continue;
                }
                if (!linea.trim().isEmpty()) {
                    if (contadorA < autores.length) {
                        autores[contadorA++] = linea.trim();
                    }
                }
            }
            if (!seccionAutores || contadorA == 0) {
                JOptionPane.showMessageDialog(null, "Formato inválido: falta la sección de autores");
                return;
            }
            
            boolean seccionResumen = (linea != null && linea.equalsIgnoreCase("Resumen"));
            while ((linea = br.readLine()) != null && !linea.startsWith("Palabras claves:")) {
                cuerpo.append(linea).append("\n");
            }
            if (!seccionResumen || cuerpo.length() == 0) {
                JOptionPane.showMessageDialog(null, "Formato inválido: falta la sección de resumen");
                return;
            }
            
            if (linea != null && linea.startsWith("Palabras claves:")) {
                String palabras = linea.substring("Palabras claves:".length()).trim();
                palabrasClave = palabras.split(",");
                for (int i = 0; i < palabrasClave.length; i++) {
                    palabrasClave[i] = palabrasClave[i].trim();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Formato inválido: falta la sección de palabras claves");
                return;
            }
            
            String[] autoresFinales = new String[contadorA];
            for (int i = 0; i < contadorA; i++) {
                autoresFinales[i] = autores[i];
            }
            
            Resumen resumen = new Resumen(tituloNormal, autoresFinales, cuerpo.toString().trim(), palabrasClave);
            tablaHash.agregarElem(resumen);

            JOptionPane.showMessageDialog(null, "Resumen agregado correctamente:\n" + titulo);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
    }
}
