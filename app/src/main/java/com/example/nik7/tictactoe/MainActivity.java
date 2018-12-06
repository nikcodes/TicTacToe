package com.example.nik7.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.InvalidMarkException;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 1;
    int activeGame = 1;
    int [][]winningP = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int []playerAt = {2,2,2,2,2,2,2,2,2};
    int pressedTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkWon(){
        for(int[] wp:winningP){
            if(playerAt[wp[0]] == playerAt[wp[1]] && playerAt[wp[1]] == playerAt[wp[2]] && playerAt[wp[0]] != 2){
                activeGame=0;
                Button but = (Button) findViewById(R.id.button);
                TextView text = (TextView) findViewById(R.id.text);

                but.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                text.setText((activePlayer==1)?"red has won":"black has won");
            }
        }

    }

    public void drop(View view){
        ImageView block = (ImageView) view;
        int currentPosition = Integer.parseInt(block.getTag().toString());

        if(activeGame==0)
            return;

        if(playerAt[currentPosition]==1 || playerAt[currentPosition]==0)
            return;

        playerAt[currentPosition] = activePlayer;
        pressedTotal++;

        if(activePlayer==1) {
            block.setImageResource(R.drawable.black);
            activePlayer = 0;
        }

        else{
            block.setImageResource(R.drawable.red);
            activePlayer=1;
        }

        checkWon();

        if (pressedTotal == 9 && activeGame==1){
            Button but = (Button) findViewById(R.id.button);
            TextView text = (TextView) findViewById(R.id.text);

            but.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);

            text.setText("Match is draw");
        }
    }

    public void reset(View view){
        for(int i=0;i<playerAt.length;i++)
            playerAt[i]=2;
        activeGame=1;
        activePlayer=1;

        Button but = (Button) findViewById(R.id.button);
        TextView text = (TextView) findViewById(R.id.text);

        but.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);

        finish();
        startActivity(getIntent());

    }
}
