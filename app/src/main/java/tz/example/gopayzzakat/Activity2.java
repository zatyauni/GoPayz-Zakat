package tz.example.gopayzzakat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {
    private Button button2;
    private Button button3;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        button2 = (Button) findViewById(R.id.buttonAbout);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout();
            }
        });
        button3 = (Button) findViewById(R.id.buttonCal);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openZakatCal();
            }
        });
    }
    public void openAbout() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
    public void openZakatCal() {
        Intent intent = new Intent(this, ZakatCal.class);
        startActivity(intent);
    }


    /*for menu of action bar created*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Search) {
            return true;
        } else if (id == R.id.Setting) {
            return true;
        } else if (id == R.id.Help) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Activity2.this);
        builder.setMessage("Do You Want To Exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}


