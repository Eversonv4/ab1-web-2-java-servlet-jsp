package com.escola.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import com.escola.database.InitDB;

@WebListener
public class AppInitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciando banco...");
        InitDB.init();
    }
}