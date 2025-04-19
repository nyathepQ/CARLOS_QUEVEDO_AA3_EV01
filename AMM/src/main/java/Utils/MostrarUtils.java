package Utils;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author nyath
 */
public class MostrarUtils {
    public int mostrarOpciones (Object[] opciones, String texto_interno, String titulo) {
        int selection = JOptionPane.showOptionDialog(null, texto_interno, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);        
        return selection;        
    }
    
    public void mostrarTabla (String[] nombre_columnas, Object[][] datos, int horizontal, int vertical, String nombre_tabla){
        // Crear JTable y JScrollPane
        JTable tabla = new JTable(datos, nombre_columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new java.awt.Dimension(horizontal, vertical));

        // Mostrar tabla en JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, nombre_tabla, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public String mostrarCombo (Object[] lista, String texto_interno){
        //crear combobox
        JComboBox<Object> combo = new JComboBox<>(lista);
        //mostrar el combobox
        int resultado = JOptionPane.showConfirmDialog(null, combo, texto_interno, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        // Obtener selección si se presionó OK
        if (resultado == JOptionPane.OK_OPTION) {
            String seleccion = (String) combo.getSelectedItem();

            return seleccion;
        } else {
            JOptionPane.showMessageDialog(null, "Cancelaste la selección.");
            return null;
        }
    }
    
    public Object crearTabla (String[] columnas, Object[][] datos, String texto_interno, int ancho, int alto) {
        //crear tabla
        JTable tabla = new JTable(datos, columnas);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //Scroll con tamaño fijo
        JScrollPane scroll_pane = new JScrollPane(tabla);
        scroll_pane.setPreferredSize(new java.awt.Dimension(ancho, alto));
        
        //mostrar tabla dentro de un optionpane
        JOptionPane.showMessageDialog(null, scroll_pane, texto_interno, JOptionPane.PLAIN_MESSAGE);
        
        //obtener fila seleccionada
        int fila_seleccionada = tabla.getSelectedRow();
        
        if (fila_seleccionada != -1){
            //obtener el valor de la columna 0 (id)
            Object idSeleccionado = tabla.getValueAt(fila_seleccionada, 0);
            return idSeleccionado;
        } else {
            return fila_seleccionada;
        }
    }
}
