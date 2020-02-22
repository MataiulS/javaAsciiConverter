package br.com.matheus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.*;

public class AsciiConverter {

    private int height;
    private int width;

    public AsciiConverter(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public AsciiConverter() {
    }

    public void generateAscii(String imagePath, String destinyPath, boolean resized) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
            if (resized) {
                bufferedImage = resize(bufferedImage, height, width);
            } else {
                width = bufferedImage.getWidth();
                height = bufferedImage.getHeight();
            }
            //convert image to gray
            ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp colorConvertOp = new ColorConvertOp(colorSpace, null);
            bufferedImage = colorConvertOp.filter(bufferedImage, null);

            StringBuilder sb = new StringBuilder((width+1) * height);
            for (int y = 0; y < width; y++) {
                if (sb.length() != 0) sb.append("\n");
                for (int x = 0; x < height; x++) {
                    int grayVal = bufferedImage.getRGB(x, y) & 0xFF;
                    checkScale(sb, grayVal);
                }
            }

            if (destinyPath != null) {
                File newFile = new File(destinyPath);
                if (newFile.createNewFile()) {
                    PrintWriter printWriter = new PrintWriter(new FileWriter(newFile));
                    printWriter.append(sb);
                    printWriter.close();
                }
            } else {
                System.out.print(sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkScale(StringBuilder sb, int grayVal) {
        if (grayVal >= 230) {
            sb.append(' ');
        } else if (grayVal >= 200 && grayVal < 230) {
            sb.append('.');
        } else if (grayVal >= 180 && grayVal < 200) {
            sb.append('*');
        } else if (grayVal >= 160 && grayVal < 180) {
            sb.append(':');
        } else if (grayVal >= 130 && grayVal < 160) {
            sb.append('o');
        } else if (grayVal >= 100 && grayVal < 130) {
            sb.append('&');
        } else if (grayVal >= 70 && grayVal < 100) {
            sb.append('8');
        } else if (grayVal >= 50 && grayVal < 70) {
            sb.append('#');
        } else {
            sb.append('@');
        }
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }



}
