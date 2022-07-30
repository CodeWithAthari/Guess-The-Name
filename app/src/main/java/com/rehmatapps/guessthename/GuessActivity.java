package com.rehmatapps.guessthename;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rehmatapps.guessthename.databinding.ActivityGuessBinding;

public class GuessActivity extends AppCompatActivity {
    ActivityGuessBinding binding;
    String strMyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonGuess.setOnClickListener(view -> {
            strMyText = binding.textFF.getText().toString();
            if (!(strMyText.length() > 2)) {
                binding.textFF.setError("Please Guess a Name");

                Toast.makeText(this, "Please Guess a Name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (strMyText.equalsIgnoreCase(GuessList.guess_LIST) && GuessList.guess_LIST != null) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GuessActivity.this);
                builder.setTitle("Congratulation!\uD83E\uDD73\uD83E\uDD73");
                builder.setCancelable(false);
                builder.setMessage("You Guessed it right \nThe Real Guess is: " + GuessList.guess_LIST);
                builder.setPositiveButton("Okay", (dialog, which) -> finish());
                builder.show();
                return;
            }

            Toast.makeText(this, "Your Guess is Wrong", Toast.LENGTH_SHORT).show();


        });

        binding.buttonGiveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GuessActivity.this);
                builder.setTitle("You Loose! \uD83E\uDD7A \uD83E\uDD7A");
                builder.setCancelable(false);
                builder.setMessage("You Lose \nThe Real Guess is: " + GuessList.guess_LIST +"\nTry Again!!");
                builder.setPositiveButton("Okay", (dialog, which) -> finish());
                builder.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GuessActivity.this);
        builder.setTitle("By Quiting you will lose \uD83E\uDD7A \uD83E\uDD7A.");
        builder.setMessage("If you quit this then you will lose.");
        builder.setPositiveButton("No", (dialog, which) -> dialog.dismiss());
        builder.setNegativeButton("Okay", (dialog, which) -> finish());
        builder.show();
    }
}