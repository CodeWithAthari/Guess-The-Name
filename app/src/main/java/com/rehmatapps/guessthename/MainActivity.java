package com.rehmatapps.guessthename;

import static com.rehmatapps.guessthename.GuessList.guess_LIST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rehmatapps.guessthename.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        String fisttime = "";
        fisttime =  sharedPreferences.getString("1stTime", "False");






        binding.button.setOnClickListener(view -> {
            if (binding.textFF.getText().toString().length() > 2) {
                guess_LIST = binding.textFF.getText().toString();
                startActivity(intent.setClass(getApplicationContext(), GuessActivity.class));
                binding.textFF.setText("");
            } else {
                binding.textFF.setError("Please Enter Guess Name");
                Toast.makeText(MainActivity.this, "Please Enter Guess Name", Toast.LENGTH_SHORT).show();
            }
        });



        if (fisttime.equals("True")){
            return;
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
        builder.setTitle("How to play?");
        builder.setMessage(Html.fromHtml("This app can help you to play Guess the Name Game<br><b>1-</b> Enter the name you want to guess<br><b>2-</b> Click on Add to Guess List button <br><b>3-</b> Then Enter the Guess Name <br><b>4-</b> Click on Please Guess or I Lose button <br><b>5-</b> Boom! The Result will appear. <br><br><b>Hope you like my little effort</b>"));
        builder.setCancelable(false);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("1stTime", "True");
                editor.apply();
            }
        });
        builder.show();

    }





    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
        builder.setTitle("Do You Want to Exit this App?");
        builder.setPositiveButton("Yes", (dialog, which) -> finish());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.setNeutralButton("Rate App", (dialogInterface, i) -> {
            Intent ii = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
            startActivity(ii);
        });
        builder.show();
    }
}