package com.example.mpdemo.service;

import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdService {
    public static void main() throws IOException, InterruptedException {
        Process pr;
        pr = Runtime.getRuntime().exec("E:\\python\\python.exe E:\\yolov5\\yolov5-master\\test.py");
        System.out.println(pr.waitFor());
    }
    //D:\miniconda\envs\pytorch_gpu\python.exe
//    public void cmdRecognition() throws IOException {
//        String pythonInterpreterPath = "D:\\miniconda\\envs\\pytorch_gpu\\python.exe";
//        String pythonFilePath = "E:\\yolov5\\yolov5-master\\test.py";
//
//        /**
//         * 传参并执行python脚本
//         * @param month 月份
//         * @param signature 签名规则
//         * @return 执行结果
//         */
//        try {
//            Process pr = Runtime.getRuntime().exec(pythonInterpreterPath + " " + pythonFilePath);
//            //获取python文件运行后的输出
//            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//            String line;
//            in.close();
//            pr.waitFor();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("执行完毕");
//    }
    //前面一半是本地环境下的python的启动文件地址，后面一半是要执行的python脚本地址
//        String[] arguments = new String[] {"D:\\miniconda\\envs\\pytorch_gpu\\python.exe", "E:\\yolov5\\yolov5-master\\test.py"};
//        Process proc;
//        try {
//            proc = Runtime.getRuntime().exec(arguments);// 执行py文件
//            //用输入输出流来截取结果
//            FileInputStream errorStream = (FileInputStream)proc.getErrorStream();
//            InputStreamReader isr = new InputStreamReader(errorStream,"gbk");//读取
//            System.out.println(isr.getEncoding());
//            BufferedReader in = new BufferedReader(isr);//缓冲
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            //waitFor是用来显示脚本是否运行成功，1表示失败，0表示成功，还有其他的表示其他错误
//            int re = proc.waitFor();
//            System.out.println(re);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
}
