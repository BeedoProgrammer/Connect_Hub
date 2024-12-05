/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.awt.Image;
import java.awt.image.*;

/**
 *
 * @author Abdel
 */
public class ImageFunctions {
    
        public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
            if(targetHeight == 0 && targetWidth == 0) return originalImage;
            else if(targetHeight == 0){
                float scale = (float) originalImage.getWidth() / targetWidth;
                targetHeight = (int) (originalImage.getHeight() / scale);
            }else if(targetWidth == 0){
                float scale = targetHeight / originalImage.getHeight();
                targetWidth = (int) (originalImage.getWidth()* scale);            
            }
            
            Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
            BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
        }
}
