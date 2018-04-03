package com.duanc.doorknob;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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





//    web 界面
    @FXML
    private WebView webView;





    @FXML
    protected void startButtonAction(ActionEvent event) throws IOException, InterruptedException {
        String command = "cmd /k start geth --datadir \"C:\\Users\\Administrator\\Desktop\\silian\\dev\"  --networkid  5777 --identity \"mydev\" --gcmode=archive --rpcapi \"eth,net,web3,admin,personal\" --rpc --rpcaddr \"127.0.0.1\" --rpcport \"7545\" --rpccorsdomain \"*\"  console";
        Runnable myRunnable = new Runnable(){
            public void run(){
                Process p = null;
                try {
                    p = Runtime.getRuntime().exec(command);
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

        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://localhost:9095/web3-test/index.html");
        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> event) {
                System.out.print(event.getData().toString());
            }
        });
    }


}
