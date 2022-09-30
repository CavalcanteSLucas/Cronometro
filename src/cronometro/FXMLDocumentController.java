package cronometro;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class FXMLDocumentController implements Initializable {
    // Elementos de tela
    @FXML private Button btnIniciar;
    @FXML private Button btnParar;
    @FXML private Text txtMin;
    @FXML private Text txtSeg;
    
    // Variáveis
    Timer cronometro;
    TimerTask tarefa;
    int segundo = 0;
    int minuto = 0;

    // Botão iniciar
    @FXML
    void clicouIniciar(ActionEvent event) {
        if(btnParar.getText().equals("Zerar")) btnParar.setText("Parar");
        
        btnIniciar.setDisable(true); // Desabilita o botão iniciar
        btnParar.setDisable(false); // Habilita o botão parar / zerar
            
        cronometro = new Timer();
        tarefa = new TimerTask(){
            @Override
            public void run() {
                segundo++;
                if(segundo == 60){ // Acrescenta um minuto
                    minuto = Integer.parseInt(txtMin.getText()) + 1;
                    if(minuto < 10){
                        txtMin.setText("0" + minuto);
                    }else{
                        txtMin.setText(Integer.toString(minuto));
                    }
                    segundo = 0;
                }
                if(segundo < 10){
                    txtSeg.setText("0" + Integer.toString(segundo));
                }else{
                    txtSeg.setText(Integer.toString(segundo));
                }
            }  
        };
        int milissegundos = 1000;
        int intervalo = 1000;
        cronometro.schedule(tarefa, milissegundos, intervalo);
    }// Fim botão iniciar

    // Botão parar / zerar
    @FXML
    void clicouParar(ActionEvent event) {
        btnIniciar.setDisable(false);
        
        if(btnParar.getText().equals("Parar")){
            btnParar.setText("Zerar"); // Tansforma o botão parar em botão zerar
            cronometro.cancel();
            cronometro = null;
        }else if(btnParar.getText().equals("Zerar")){
            btnParar.setText("Parar"); // Tansforma o notão zerar em botão parar
            txtSeg.setText("00");
            txtMin.setText("00");
            btnParar.setDisable(true);
            segundo = 0;
            minuto = 0;
            cronometro = null;
        }
    }// Fim botão parar / zerar
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    }    
    
}
