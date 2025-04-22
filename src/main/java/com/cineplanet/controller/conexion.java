/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cineplanet.controller;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author wilme
 */
public class conexion {
    
    public static final String username = "root";
    public static final String password = "root";
    public static final String database = "cineplanet_web";
    public static final String url = "jdbc:mysql://localhost:3306/"+database;
    
    public static Connection getConnection(){
        Connection cn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url,username,password);
            System.out.println("Conexion establecida");
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return cn;
    }
}