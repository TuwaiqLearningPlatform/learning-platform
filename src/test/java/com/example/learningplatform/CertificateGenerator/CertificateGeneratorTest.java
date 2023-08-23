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

        Graphics g = image.getGraphics();
        g.setColor(Color.ORANGE);
        g.setFont(g.getFont().deriveFont(50f));
        g.drawString("Abdullah", 100, 100);
        g.dispose();

        ImageIO.write(image, "png", new File(WRITE_TO + "abdullah_certificate.png"));

//        final BufferedImage image = ImageIO.read(new URL(
//                "http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));
    }
}
