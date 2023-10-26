package br.ulbra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void selecionadoPedra(View view) {
        this.opcaoSelecionado("pedra");
    }


    public void selecionadoPapel(View view) {
        this.opcaoSelecionado("papel");
    }


    public void selecionadoTesoura(View view) {
        this.opcaoSelecionado("tesoura");
    }


    public void opcaoSelecionado(String opcaoSelecionada) {
        ImageView imageResultado = findViewById(R.id.imgApp);
        TextView txtResult = findViewById(R.id.txtResultado);
        String opcoes[] = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[new Random().nextInt(3)];
        switch (opcaoApp) {
            case "pedra":
                imageResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imageResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imageResultado.setImageResource(R.drawable.tesoura);
                break;
        }
        if ((opcaoApp.equals("tesoura") && opcaoSelecionada.equals("papel")) ||
                (opcaoApp.equals("papel") && opcaoSelecionada.equals("pedra")) ||
                (opcaoApp.equals("pedra") && opcaoSelecionada.equals("tesoura"))) {
            txtResult.setText("Resultado: Você PERDEU... :(");
            pontuacaoApp++;
            atualizarPlacar();

        } else if ((opcaoSelecionada.equals("tesoura") && opcaoApp.equals("papel")) ||
                (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra")) ||
                (opcaoSelecionada.equals("pedra") && opcaoApp.equals("tesoura"))) {
                txtResult.setText("Resultado: Você GANHOU... ;D");
                pontuacaoPlayer++;
                atualizarPlacar();
        } else {
            txtResult.setText("Resultado: Vocês EMPATARAM... :|");
        }
    }
    int pontuacaoApp, pontuacaoPlayer;

    public void atualizarPlacar(){
        TextView txtPlacarPlayer = findViewById(R.id.txtPlacarPlayer);
        TextView txtPlacarBot = findViewById(R.id.txtPlacarBot);
        TextView txtResultado = findViewById(R.id.txtResultado);

        if (pontuacaoApp >= 2 || pontuacaoPlayer >= 2){
            if (pontuacaoPlayer > pontuacaoApp){
                txtResultado.setText("FIM DE JOGO VOCÊ GANHOU!");
                reiniciarJogo(txtPlacarPlayer);
            }else{
                txtResultado.setText("FIM DE JOGO VOCÊ PERDEU!");
                reiniciarJogo(txtPlacarPlayer);
            }
        }
        txtPlacarPlayer.setText("Você: "+pontuacaoPlayer);
        txtPlacarBot.setText("Bot: "+pontuacaoApp);
    }
    public void reiniciarJogo(View view){
        pontuacaoPlayer = 0;
        pontuacaoApp = 0;
        atualizarPlacar();
        ImageView imageResultado = findViewById(R.id.imgApp);
        imageResultado.setImageDrawable(getDrawable(R.drawable.padrao));
    }
}

