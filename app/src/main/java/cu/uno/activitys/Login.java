package cu.uno.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cu.uno.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button entrar, masTarde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
    entrar = findViewById(R.id.entrar);
    masTarde = findViewById(R.id.mas_tarde);

    entrar.setOnClickListener(this);
    masTarde.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entrar:
                startActivity(new Intent(Login.this, Principal.class));
                finish();
                break;
            case R.id.mas_tarde:
                startActivity(new Intent(Login.this, Categorias.class));
                break;
        }
    }
}
