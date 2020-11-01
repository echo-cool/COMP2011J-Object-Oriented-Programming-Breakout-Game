package com.echo.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

public class TestReactant {
    public static void main(String[] args) throws Exception {
        BufferedImage bimg = new BufferedImage(120, 60,
                BufferedImage.TYPE_INT_RGB);
        // 得到Graphics2D 对象
        Graphics2D g2d = (Graphics2D) bimg.getGraphics();
        // 设置颜色和画笔粗细
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.RED);
        // g2d.setStroke(new BasicStroke(3));
        BasicStroke stroke = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 3.5f, new float[] { 10, 5 }, 0f);
        g2d.setStroke(stroke);
        g2d.clearRect(0, 0, 120, 60);// 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
        g2d.drawRect(0, 0, 120, 60);
        // 绘制图案或文字
        g2d.drawString("文本你好", 20, 30);
        g2d.drawString("文本你好", 20, 50);
        ImageIO.write(bimg, "JPG", new FileOutputStream("cavas.jpg"));
    }
}