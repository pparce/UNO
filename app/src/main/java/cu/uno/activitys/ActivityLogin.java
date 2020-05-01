package cu.uno.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cu.uno.R;
import cu.uno.utiles.PrefManager;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

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
                startActivity(new Intent(ActivityLogin.this, ActivityPrincipal.class));
                PrefManager prefManager = new PrefManager(ActivityLogin.this);
                prefManager.setIsLogin(true);
                finish();
                break;
            case R.id.mas_tarde:
                startActivity(new Intent(ActivityLogin.this, ActivityCategorias.class));
                break;
        }
    }
}
