package com.example.eventsapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.eventsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonShowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyFormatting();
            }
        });

        binding.rdgCor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                applyFormatting();
            }
        });
    }

    private void applyFormatting() {
        String text = binding.editText.getText().toString();

        if (!TextUtils.isEmpty(text)) {
            // Aplicar tamanho da fonte
            int textSize = binding.skbTextSize.getProgress();
            binding.editText.setTextSize(textSize);

            // Aplicar estilo
            int fontStyle = Typeface.NORMAL;
            if (binding.chkNegrito.isChecked()) {
                fontStyle |= Typeface.BOLD;
            }
            if (binding.chkItalico.isChecked()) {
                fontStyle |= Typeface.ITALIC;
            }
            binding.editText.setTypeface(null, fontStyle);

            // Aplicar maiúsculas
            if (binding.chkMaiuscula.isChecked()) {
                text = text.toUpperCase();
            }

            // Aplicar cor
            int color = getColorSelection();
            binding.editText.setTextColor(color);
        }
    }

    private int getColorSelection() {
        int color;
        int checkedRadioButtonId = binding.rdgCor.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.rdbVermelho) {
            color = ContextCompat.getColor(this, R.color.vermelho);
        } else if (checkedRadioButtonId == R.id.rdbVerde) {
            color = ContextCompat.getColor(this, R.color.verde);
        } else if (checkedRadioButtonId == R.id.rdbAzul) {
            color = ContextCompat.getColor(this, R.color.azul);
        } else {
            color = Color.BLACK;
        }
        return color;
    }
}
