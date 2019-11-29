/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

/**
 *
 * @author zhangchuanqi
 */
public class Util {
    
    public static void setBorderBlack(JComponent ... components) {
        for (JComponent component : components) {
            component.setBorder(new LineBorder(new Color(128, 128, 128)));
        }
    }

    public static void setBorderRed(JComponent component) {
        component.setBorder(new LineBorder(Color.RED));
    }
}
