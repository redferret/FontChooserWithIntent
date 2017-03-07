package apackage.richard.com.fontchooser;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FontChooserActivity extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private int red = 0, green = 0, blue = 0, alpha = 255, style = Typeface.NORMAL;
    private Typeface typeface = Typeface.DEFAULT;
    private TextView sampleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_chooser);
        setupFontSpinners();

        SeekBarListener sbl = new SeekBarListener();
        ((SeekBar) findViewById(R.id.redSeek)).setOnSeekBarChangeListener(sbl);
        ((SeekBar) findViewById(R.id.greenSeek)).setOnSeekBarChangeListener(sbl);
        ((SeekBar) findViewById(R.id.blueSeek)).setOnSeekBarChangeListener(sbl);
        ((SeekBar) findViewById(R.id.alphaSeek)).setOnSeekBarChangeListener(sbl);
        ((SeekBar) findViewById(R.id.textSizeSeek)).setOnSeekBarChangeListener(sbl);

        sampleText = (TextView) findViewById(R.id.sampleText);
        setTextColor(red, green, blue, alpha, sampleText);

        Spinner spinner = (Spinner) findViewById(R.id.fontFamily);
        spinner.setOnItemSelectedListener(this);

        spinner = (Spinner) findViewById(R.id.fontStyles);
        spinner.setOnItemSelectedListener(this);
    }


    public void setupFontSpinners(){
        Spinner spinner = (Spinner) findViewById(R.id.fontStyles);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fontStyles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.fontFamily);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.fontFamily, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Spinner) {
            Spinner selection = (Spinner) v;
            int pos = selection.getSelectedItemPosition();
            Toast.makeText(this.getApplicationContext(), "Pos is " + pos, Toast.LENGTH_LONG);
            switch (v.getId()) {
                case R.id.fontFamily:

                    break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getSelectedItem().toString();

        switch(item){
            case "Bold":
                sampleText.setTypeface(typeface, style = Typeface.BOLD);
                break;
            case "Bold Italic":
                sampleText.setTypeface(typeface, style = Typeface.BOLD_ITALIC);
                break;
            case "Italic":
                sampleText.setTypeface(typeface, style = Typeface.ITALIC);
                break;
            case "Normal":
                sampleText.setTypeface(typeface, style = Typeface.NORMAL);
                break;
            case "Monospaced":
                sampleText.setTypeface(typeface = Typeface.MONOSPACE, style);
                break;
            case "Serif":
                sampleText.setTypeface(typeface = Typeface.SERIF, style);
                break;
            case "Sans Serif":
                sampleText.setTypeface(typeface = Typeface.SANS_SERIF, style);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            int red, green, blue, alpha;

            switch (seekBar.getId()) {
                case R.id.redSeek:
                    FontChooserActivity.this.red = red = progress;
                    green = FontChooserActivity.this.green;
                    blue = FontChooserActivity.this.blue;
                    alpha = FontChooserActivity.this.alpha;
                    setTextColor(red, green, blue, alpha, sampleText);
                    break;
                case R.id.greenSeek:
                    red = FontChooserActivity.this.red;
                    FontChooserActivity.this.green = green = progress;
                    blue = FontChooserActivity.this.blue;
                    alpha = FontChooserActivity.this.alpha;
                    setTextColor(red, green, blue, alpha, sampleText);
                    break;
                case R.id.blueSeek:
                    red = FontChooserActivity.this.red;
                    green = FontChooserActivity.this.green;
                    FontChooserActivity.this.blue = blue = progress;
                    alpha = FontChooserActivity.this.alpha;
                    setTextColor(red, green, blue, alpha, sampleText);
                    break;
                case R.id.alphaSeek:
                    red = FontChooserActivity.this.red;
                    green = FontChooserActivity.this.green;
                    blue = FontChooserActivity.this.blue;
                    FontChooserActivity.this.alpha = alpha = progress;
                    setTextColor(red, green, blue, alpha, sampleText);
                    break;
                case R.id.textSizeSeek:
                    sampleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress + 10);
                    break;
            }
        }


    }

    
    private void setTextColor(int red, int green, int blue, int alpha, TextView textView) {
        int color = alpha;
        color <<= 8;  color += red;
        color <<= 8;  color += green;
        color <<= 8;  color += blue;
        textView.setTextColor(color);
    }
}
