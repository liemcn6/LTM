package run;

import control.ClientControl;
import view.ClientView;


public class ClientMain {
    public static void main(String[] args) {
        ClientView view = new ClientView();
        ClientControl control = new ClientControl(view);
        control.run();
    }
}