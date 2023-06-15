package com.example.mpdemo.utils;

import ai.djl.modality.cv.ImageFactory;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

import java.awt.*;

public class MyUtils {
    public static Image mat2Image(Mat mat) {
        return (Image) ImageFactory.getInstance().fromImage(HighGui.toBufferedImage(mat));
    }
}
