package run;

import control.ServerControl;
import view.ServerView;


public class ServerMain {
    public static void main(String[] args) {
        ServerView view = new ServerView();
        ServerControl control = new ServerControl(view);
    }   
}