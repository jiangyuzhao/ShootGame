/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootgame;

/**
 *
 * @author qinyuxuan
 */
import java.io.*;
import java.io.File;
public class FileTest {
    public static void main(String args[])throws IOException{
 
      File file = new File("/Users/qinyuxuan/Desktop/a1.jpg");
      System.out.println(file.length());
   }
}
