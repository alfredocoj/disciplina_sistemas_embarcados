package com.example.ayrton.hihome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayrton.hihome.bancoDeDados.clienteBanco;
import com.example.ayrton.hihome.fragments.acFragment;
import com.example.ayrton.hihome.fragments.lampadaFragment;
import com.example.ayrton.hihome.fragments.portaoFragment;
import com.example.ayrton.hihome.fragments.statusFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HiHome extends AppCompatActivity {


    private Fragment fragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView botNavView;
    private Toolbar toolbar;

    private DatabaseReference fbdb = FirebaseDatabase.getInstance().getReference("usuarios");
    private FirebaseAuth fbauth;
    private FirebaseUser user = fbauth.getInstance().getCurrentUser();
    int ac = 0, lampada = 0, portao = 0;
    View[] vFragAc, vFragLampada, vFragPortao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onStart() {
        super.onStart();

        botNavView = (BottomNavigationView) findViewById(R.id.navigation);

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame_list, statusFragment.newInstance(lampada, ac, portao)).commit();

        botNavView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.Home:
                        fragment = statusFragment.newInstance(lampada, ac, portao);
                        break;
                    case R.id.AC:
                        fragment = acFragment.newInstance(ac);
                        break;
                    case R.id.Lampada:
                        fragment = lampadaFragment.newInstance(lampada);
                        break;
                    case R.id.Portao:
                        fragment = portaoFragment.newInstance(portao);
                        break;
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_list, fragment).commit();

                return true;
            }
        });

        fbdb.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clienteBanco clienteBanco = dataSnapshot.getValue(clienteBanco.class);
                ac = clienteBanco.getAc();
                lampada = clienteBanco.getLampada();
                portao = clienteBanco.getPortao();

                botNavView.setSelectedItemId(R.id.Home);
                fragment = statusFragment.newInstance(lampada, ac, portao);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_list, fragment).commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hi_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            fbauth = FirebaseAuth.getInstance();
            fbauth.signOut();
            Intent i = new Intent(HiHome.this, MainActivity_Login.class);
            startActivity(i);
            finishAffinity();
            Toast.makeText(this, "Usuário Deslogado", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
