package LegendaryCardMaker.exporters;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JCheckList.java
 *
 * Created on 06/06/2011, 15:42:46
 */
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Rodrigo
 */
public class JCheckList<E> extends javax.swing.JPanel {

    private DefaultListModel listaModel;

    /** Creates new form JCheckList */
    public JCheckList() {
        initComponents();
        listaModel = new DefaultListModel();
        lista.setModel(listaModel);
        iniciar();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        scrollPane.setViewportView(lista);

        add(scrollPane);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList lista;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

    public void setData(List<E> items) {
        listaModel.removeAllElements();
        for (E algo : items) {
            listaModel.addElement(new CheckListItem(algo));
            
        }
    }

    public List<E> getSelectedItems() {
        ArrayList<E> salida=new ArrayList<E>();
        CheckListItem item;
        for(int i=0;i<listaModel.getSize();i++)
        {
            item=(CheckListItem) listaModel.get(i);
            if(item.isSelected)
                salida.add(item.getContenido());
        }
        return salida;
    }

    public void setSelectedItems(List<E> selection) {

        for (int i = 0; i < listaModel.getSize(); i++)
            ((CheckListItem) listaModel.get(i)).setSelected(false);

        for (E item : selection)
            for (int i = 0; i < listaModel.getSize(); i++)
                if (((CheckListItem) listaModel.get(i)).equals(item))
                {
                    ((CheckListItem) listaModel.get(i)).setSelected(true);
                    break;
                }

        this.repaint();
    }
    
    public void setSelectAll()
    {

        for (int i = 0; i < listaModel.getSize(); i++)
            ((CheckListItem) listaModel.get(i)).setSelected(true);

        this.repaint();
    }
    
    public void setDeselectAll() 
    {

        for (int i = 0; i < listaModel.getSize(); i++)
            ((CheckListItem) listaModel.get(i)).setSelected(false);

        this.repaint();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        lista.setEnabled(enabled);
    }



    private void iniciar() {
        // Use a CheckListRenderer (see below)
        // to renderer list cells

        lista.setCellRenderer(new CheckListRenderer());
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add a mouse listener to handle changing selection

        lista.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {

                if(lista.isEnabled()==false)
                    return;
                
               

                JList list = (JList) event.getSource();

                // Get index of item clicked
                int index = list.locationToIndex(event.getPoint());
                if(index<0)
                    return;
                CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);

                // Toggle selected state
                item.setSelected(!item.isSelected());

                // Repaint cell
                list.repaint(list.getCellBounds(index, index));
               
            }
        });

    }

    private class CheckListItem {

        private String label;
        private boolean isSelected = false;
        private E contenido = null;

        public CheckListItem(String label) {
            this.label = label;
        }

        public CheckListItem(E contenido) {
            this.contenido = contenido;
            this.label = contenido.toString();
        }

        public CheckListItem(String label, E contenido) {
            this.label = label;
            this.contenido = contenido;
        }

        private CheckListItem() {
        }

        public E getContenido() {
            return contenido;
        }

        public void setContenido(E contenido) {
            this.contenido = contenido;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String toString() {
            return label;
        }

        @Override
        public boolean equals(Object obj) {
            return contenido.equals(obj);
        }
    }

// Handles rendering cells in the list using a check box
    private class CheckListRenderer extends JCheckBox implements ListCellRenderer {

        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean hasFocus) {
            setEnabled(list.isEnabled());
            setSelected(((CheckListItem) value).isSelected());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setText(value.toString());
            return this;
        }
    }
    
    public void addCheckListItem(String label, E item)
    {
    	listaModel.addElement(new CheckListItem(label, item));
    }
}
