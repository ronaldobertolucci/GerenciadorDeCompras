package io.github.ronaldobertolucci.gerenciadordecompras;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProdutoActivity extends AppCompatActivity {

    private EditText editTextNome;
    private Spinner spinnerUnidade;
    private RadioGroup radioGroupPrioridade;
    private CheckBox checkBoxFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        editTextNome = findViewById(R.id.editTextNome);
        spinnerUnidade = findViewById(R.id.spinnerUnidade);
        radioGroupPrioridade = findViewById(R.id.radioGroupPrioridade);
        checkBoxFavorito = findViewById(R.id.checkBoxFavorito);
    }

    public void limparCampos(View view){

        editTextNome.setText(null);
        spinnerUnidade.setSelection(0);
        radioGroupPrioridade.clearCheck();
        checkBoxFavorito.setChecked(false);

        editTextNome.requestFocus();

        Toast.makeText(this, R.string.campos_limpos, Toast.LENGTH_LONG).show();
    }

    public void salvarValores(View view){

        String nome = editTextNome.getText().toString();

        if (nome.trim().isEmpty()){
            Toast.makeText(this, R.string.nome_obrigatorio, Toast.LENGTH_LONG).show();
            editTextNome.requestFocus();
            return;
        }

        nome = nome.trim();

        int radioButtonId = radioGroupPrioridade.getCheckedRadioButtonId();

        String unidade = (String) spinnerUnidade.getSelectedItem();

        if (unidade == null){
            Toast.makeText(this, R.string.unidade_obrigatoria, Toast.LENGTH_LONG).show();
            return;
        }

        String prioridade;

        if (radioButtonId == R.id.radioButtonUrgente){
            prioridade = getString(R.string.urgente);
        } else if (radioButtonId == R.id.radioButtonOpcional){
            prioridade = getString(R.string.opcional);
        } else{
            Toast.makeText(this, R.string.prioridade_obrigatoria, Toast.LENGTH_LONG).show();
            return;
        }

        boolean favorito = checkBoxFavorito.isChecked();

        Toast.makeText(this,
                    getString(R.string.nome_produto) + ": "  + nome  + "\n" +
                    getString(R.string.unidade)+ ": " + unidade + "\n" +
                    getString(R.string.prioridade)+ ": " + prioridade + "\n" +
                    (favorito ? getString(R.string.favorito) : getString(R.string.nao_favorito)),
                    Toast.LENGTH_LONG)
                .show();
    }
}