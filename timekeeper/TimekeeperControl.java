/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.dao.DAOTimekeeper;
import controller.utils.ConnectionUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Timekeeper;
import view.TimekeeperView;

/**
 *
 * @author DELL
 */
public class TimekeeperControl {

    private TimekeeperView view;
    private DAOTimekeeper dao;
    public TimekeeperControl(TimekeeperView view) {
        this.view = view;
        try {
            dao = new DAOTimekeeper(ConnectionUtils.getMyConnection());
            this.view.addInsertListerner(new listener1());
            this.view.addUpdateListener(new listener2());
            this.view.addDeleteListener(new listener3());
            this.view.addShowListener(new listener4());
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        }

    }
    class listener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Timekeeper t=view.getTK();
                view.inserttable(t);
                
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        }
    class listener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Timekeeper t=view.getTK();
                view.updatetable();;
                
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        }
    class listener3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Timekeeper t=view.getTK();
                view.addDeleteListener(null);;
                
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        }
    class listener4 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Timekeeper t=view.getTK();
                view.showtable(null);;
                
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        }
    }



