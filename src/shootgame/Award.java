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
//奖励
public interface Award {
    int DOUBLE_FIRE = 0;  //双倍火力  
    int LIFE = 1;   //1条命  
    /** 获得奖励类型(上面的0或1) */  
    int getType();  
}
