package com.example.learningplatform.CertificateGenerator;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class CertificateGeneratorTest {

    // path to certificate image file.
    private final String CERTIFICATE_PATH = "/home/alharbi/IdeaProjects/learning-platform/src/main/resources/static/certificate.png";
    private final String WRITE_TO = "/home/alharbi/IdeaProjects/learning-platform/src/main/resources/static/";

    @Test
    void GenerateCustomCertificateWithStudentName() throws IOException {
        // open image

        final BufferedImage image = ImageIO.read(new File(CERTIFICATE_PATH));
        Color color = new Color(1, 4, 73);
        Graphics g = image.getGraphics();
        g.setColor(color);

        Font font = new Font("Arial", Font.BOLD, 70);
        g.setFont(font);
        g.drawString("Abdullah Alharbi", 100, 880);
        g.drawString("Python Programming", 100, 1324);

        g.dispose();

        ImageIO.write(image, "png", new File(WRITE_TO + "abdullah_certificate.png"));

//        final BufferedImage image = ImageIO.read(new URL(
//                "http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));
    }
}
