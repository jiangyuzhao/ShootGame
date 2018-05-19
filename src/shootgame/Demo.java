/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootgame;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.util.ArrayList;  
import org.json.JSONException;  
import org.json.JSONObject;  
import org.json.JSONArray;  
import java.io.*;
import java.lang.*;
import java.util.Timer;  
import java.util.TimerTask;  
/**
 *
 * @author qinyuxuan
 */

public class Demo {
    private Timer hero_timer;//主角的定时器
    private static int intervel =10; // 时间间隔(毫秒)  
    public static String readString3()    
    {    
        String str="";  
        File file=new File("/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/result.json");    
        try {    
            FileInputStream in=new FileInputStream(file);    
            // size  为字串的长度 ，这里一次性读完    
            int size=in.available();   
            byte[] buffer=new byte[size];   
            in.read(buffer);  
            in.close();  
            str=new String(buffer,"GB2312");  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return null;  
        }  
    return str;  
   }  
    public static String readString()    
    {    
        String str="";  
        File file=new File("/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/result1.txt");    
        try {    
            FileInputStream in=new FileInputStream(file);    
            // size  为字串的长度 ，这里一次性读完    
            int size=in.available();   
            byte[] buffer=new byte[size];   
            in.read(buffer);  
            in.close();  
            str=new String(buffer,"GB2312");  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return null;  
        }  
    return str;  
   }  
    public static String readString2()    
    {    
        String str="";  
        File file=new File("/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/result2.json");    
        try {    
            FileInputStream in=new FileInputStream(file);    
            // size  为字串的长度 ，这里一次性读完    
            int size=in.available();   
            byte[] buffer=new byte[size];   
            in.read(buffer);  
            in.close();  
            str=new String(buffer,"GB2312");  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return null;  
  }  
    return str;  
}
    public void start(){
        hero_timer = new Timer();
        hero_timer.schedule(new TimerTask() {  
            @Override  
            public void run() {  
                String content = readString();
                if(!content.isEmpty()){
                String []strs = content.split(" ");
                int top = Integer.parseInt(strs[0]);
                int right = Integer.parseInt(strs[1]);
                int bottom = Integer.parseInt(strs[2]);
                int left = Integer.parseInt(strs[3]);
                System.out.println("");
                System.out.println(top);
                System.out.println(right);
                System.out.println(bottom);
                System.out.println(left);
                }
            }  
  
        }, intervel, intervel); 
    }
    public static void main(String args[]) throws IOException {

        //String _ = readString2().replaceAll("\\\\", "");
        //String content = _.substring(1,_.length()-1);
        //System.out.println(content);
        //try{
            //JSONObject jsonObject=new JSONObject(content);
            //System.out.println(jsonObject.length());
            //System.out.println(jsonObject.getJSONArray("hands").getJSONObject(0).getJSONObject("gesture").getDouble("heart_c"));
            //if(jsonObject.getJSONArray("hands").getJSONObject(0).getJSONObject("gesture").getDouble("thanks") > 50)
                //System.out.println("haha");
            //System.out.println("y: " + jsonObject.getJSONArray("faces").getJSONObject(0).getJSONObject("landmark").getJSONObject("left_eye_upper_left_quarter").getInt("y"));
            //System.out.println("y: " + jsonObject.getJSONArray("faces").getJSONObject(0).getJSONObject("face_rectangle"));
            //System.out.println("y: " + jsonObject.getJSONArray("faces").getJSONObject(0).getJSONObject("face_rectangle"));
            
            
//System.out.println("y: " + jsonObject.getJSONArray("faces").getJSONObject(0).getJSONObject("left_eye_upper_left_quarter").getInt("y"));
            //System.out.println("x: " + jsonObject.getJSONArray("faces").getJSONObject(0).getJSONObject("left_eye_upper_left_quarter").getInt("x"));
            
        //}catch(JSONException e){}
        /*
        String content = readString();
                String []strs = content.split(" ");
                int top = Integer.parseInt(strs[0]);
                int right = Integer.parseInt(strs[1]);
                int bottom = Integer.parseInt(strs[2]);
                int left = Integer.parseInt(strs[3]);
                System.out.println(top);
                System.out.println(right);
                System.out.println(bottom);
                System.out.println(left);
        */
        
        Demo d = new Demo();
        d.start();

        
    }
}
