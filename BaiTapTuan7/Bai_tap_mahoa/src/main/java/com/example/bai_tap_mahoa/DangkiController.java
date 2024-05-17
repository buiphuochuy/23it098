package com.example.bai_tap_mahoa;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class DangkiController implements Initializable {

        @FXML
        private Button dangki;

        @FXML
        private Button dangnhap;

        @FXML
        private TextField matkhau;

        @FXML
        private TextField nhaplaimk;

        @FXML
        private TextField tendangnhap;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dangnhap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new DBUntils().changeScene(actionEvent, "dangnhap.fxml", "Dang nhap", 400,600);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        dangki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tendangnhap.getText().isEmpty()||matkhau.getText().isEmpty()||nhaplaimk.getText().isEmpty()){
                    // Tạo một hộp thoại thông báo
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Vui lòng nhập đủ!");
                    // Hiển thị hộp thoại thông báo
                    alert.setWidth(400); // Đặt chiều rộng
                    alert.setHeight(200); // Đặt chiều cao
                    alert.showAndWait();
                }else{
                    if(!matkhau.getText().equals(nhaplaimk.getText())){
                        // Tạo một hộp thoại thông báo
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText(null);
                        alert.setContentText("Vui lòng đúng mật khẩu!");
                        // Hiển thị hộp thoại thông báo
                        alert.setWidth(400); // Đặt chiều rộng
                        alert.setHeight(200); // Đặt chiều cao
                        alert.showAndWait();
                    }else{
                        String matkhau_DaMaHoa= Data_Encryption.encryptString(matkhau.getText());
                        Cl_toSV_dangki cl_toSV_dangki= new Cl_toSV_dangki(matkhau_DaMaHoa, tendangnhap.getText());
                        Gson gson = new Gson();
                        Socket socket= ConnectServer.getSoket();
                        Request request = new Request("/getSignUp");
                        String json = gson.toJson(request);
                        String value = gson.toJson(cl_toSV_dangki);
                        try {//gửi dữ liệu JSon từ client cho Server
                            DataOutputStream toSever = new DataOutputStream(socket.getOutputStream());
                            toSever.writeBytes(json + "\n");

                            // xaác nhận Server đã nhận được yêu cầu
                            ConnectServer.receiveStatus(socket);
                            // gửi lần 2
                            toSever.writeBytes( value + "\n");

                            //đọc dữ liệu JSon
                            BufferedReader fromServe = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String datajson_of_serve = fromServe.readLine();
                            if(datajson_of_serve.equals("F")){
                                // Tạo một hộp thoại thông báo
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Dang ki that bai!");
                                alert.setHeaderText(null);

                                // Hiển thị hộp thoại thông báo
                                alert.setWidth(400); // Đặt chiều rộng
                                alert.setHeight(200); // Đặt chiều cao
                                alert.showAndWait();
                            }else{
                                try {
                                    new DBUntils().changeScene(actionEvent, "dangnhap.fxml", "Dang nhap", 400,600);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            toSever.close();
                            fromServe.close();
                            socket.close();
                        } catch (IOException e) {
                            // Xử lý nếu có lỗi xảy ra trong quá trình ghi dữ liệu vào file
                            e.printStackTrace();
                        }
                    }


                }
            }
        });
    }
}
