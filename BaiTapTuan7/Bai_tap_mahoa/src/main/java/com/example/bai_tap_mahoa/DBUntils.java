package com.example.bai_tap_mahoa;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
//import org.example.appcomics.ObjectGson.IdUser;

import java.io.IOException;

public class DBUntils {
    // Định nghĩa phương thức changeScene để chuyển đổi giao diện
    public void
    changeScene(ActionEvent event, String fxmlFile, String title, int H, int W) throws IOException {
        // Tạo một đối tượng FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root = null;
        try {
            // Tải giao diện mới từ tệp FXML
            root = loader.load();
        }catch (Exception e){
            System.out.println("Load fxml error");
        }


        // Lấy đối tượng Stage từ sự kiện (MouseEvent)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(H);
        stage.setWidth(W);
        // Đặt giao diện mới làm giao diện chính
        stage.setScene(new Scene(root));

        // Đặt tiêu đề cho cửa sổ
        stage.setTitle(title);

        // Hiển thị cửa sổ
        stage.show();
    }
}
