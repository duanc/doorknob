package com.duanc.doorknob;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Text actiontarget;

    @FXML
    private TextField datadir;

    @FXML
    private TextField identity;

    @FXML
    private TextField networkid;

    @FXML
    private TextField rpccorsdomain;

    @FXML
    private CheckBox isDev;

    @FXML
    private CheckBox isGcmode;

    @FXML
    private CheckBox isRpc;

    @FXML
    private TextField rpcaddr;

    @FXML
    private TextField rpcport;

    @FXML
    private TextField rpcapi;


    @FXML
    protected void startButtonAction(ActionEvent event) throws IOException, InterruptedException {


        String command = "cmd /k start geth --datadir " + datadir.getText() +
                "  --networkid " + networkid.getText() +
                "  --identity " + identity.getText();

        if (isDev.isSelected()) {
            command += " --dev";
        }

        if (isGcmode.isSelected()) {
            command += " --gcmode=archive";
        }

        if (isRpc.isSelected()) {
            command += " --rpc --rpcaddr " + rpcaddr.getText() +
                    " --rpcport " + rpcport.getText() +
                    " --rpcapi " + rpcapi.getText();
        }


        command += " console";
        System.out.print(command);


//                "\"mydev\" --gcmode=archive --rpcapi \"eth,net,web3,admin,personal\" --rpc --rpcaddr \"127.0.0.1\" --rpcport \"7545\" --rpccorsdomain \"*\"  console";


        String finalCommand = command;
        Runnable myRunnable = new Runnable() {
            public void run() {
                Process p = null;
                try {
                    p = Runtime.getRuntime().exec(finalCommand);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        Thread thread = new Thread(myRunnable);
        thread.start();
    }


    //初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datadir.setText("C:\\Users\\Administrator\\Desktop\\silian\\dev");
        identity.setText("mydev");
        networkid.setText("5777");
        rpccorsdomain.setText("*");
        isDev.setSelected(true);
        isGcmode.setSelected(true);
        isRpc.setSelected(true);

        rpcaddr.setText("127.0.0.1");
        rpcport.setText("7545");
        rpcapi.setText("eth,net,web3,admin,personal,miner");
    }


}
