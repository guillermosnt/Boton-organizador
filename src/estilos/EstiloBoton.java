package estilos;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class EstiloBoton {

    private static final Color AZUL = new Color(37, 99, 235);
    private static final Color AZUL_HOVER = new Color(29, 78, 216);

    private EstiloBoton() {
    }

    public static void aplicar(JButton boton) {
        boton.setFont(new Font("SansSerif", Font.BOLD, 15));
        boton.setForeground(Color.WHITE);
        boton.setBackground(AZUL);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(13, 24, 13, 24));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                boton.setBackground(AZUL_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                boton.setBackground(AZUL);
            }
        });
    }
}
