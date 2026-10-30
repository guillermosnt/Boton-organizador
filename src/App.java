import estilos.EstiloBoton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame ventana = new JFrame("Ordenador de archivos");
            JLabel mensaje = new JLabel("Pulsa el botón para ordenar los archivos.");
            JButton boton = new JButton("Ordenar archivos");

            File carpetaDescargas = new File(System.getProperty("user.home"), "Downloads");
            boton.addActionListener(event -> ordenarArchivos(carpetaDescargas, mensaje));

            EstiloBoton.aplicar(boton);
            JPanel contenido = new JPanel(new BorderLayout(12, 12));
            contenido.setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));
            contenido.add(mensaje, BorderLayout.NORTH);
            contenido.add(boton, BorderLayout.CENTER);

            ventana.add(contenido);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setPreferredSize(new Dimension(380, 180));
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }

    private static void ordenarArchivos(File carpetaDescargas, JLabel mensaje) {
        File[] archivos = carpetaDescargas.listFiles();

        if (archivos == null) {
            mensaje.setText("No se puede acceder a la carpeta Downloads.");
            return;
        }

        int ordenados = 0;
        int errores = 0;

        for (File archivo : archivos) {
            if (!archivo.isFile()) {
                continue;
            }

            String categoria = obtenerCategoria(archivo.getName());
            File carpetaDestino = new File(carpetaDescargas, categoria);

            try {
                Files.createDirectories(carpetaDestino.toPath());
                Path destino = new File(carpetaDestino, archivo.getName()).toPath();
                Files.move(archivo.toPath(), destino);
                ordenados++;
            } catch (IOException error) {
                System.err.println("No se pudo mover " + archivo.getName() + ": " + error.getMessage());
                errores++;
            }
        }

        mensaje.setText("Archivos ordenados: " + ordenados + ". Errores: " + errores + ".");
    }

    private static String obtenerCategoria(String nombreArchivo) {
        String nombre = nombreArchivo.toLowerCase(Locale.ROOT);

        if (nombre.endsWith(".pdf") || nombre.endsWith(".docx") || nombre.endsWith(".txt")) {
            return "Documentos";
        } else if (nombre.endsWith(".jpg") || nombre.endsWith(".png") || nombre.endsWith(".gif")) {
            return "Imagenes";
        } else if (nombre.endsWith(".mp3") || nombre.endsWith(".wav") || nombre.endsWith(".flac")) {
            return "Musica";
        } else if (nombre.endsWith(".mp4") || nombre.endsWith(".avi") || nombre.endsWith(".mkv")) {
            return "Videos";
        } else {
            return "Otros";
        }
    }
}
