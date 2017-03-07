package apackage.richard.com.fontchooser;

import android.content.Intent;
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

public class FontChooserActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    private FontItem fontItem;
    private TextView sampleText;
    private Intent intent;

    public void sendFontWithIntent(View view) {

        int typeId =
                fontItem.getType() == Typeface.DEFAULT ? 0 :
                fontItem.getType() == Typeface.MONOSPACE ? 1 :
                fontItem.getType() == Typeface.SERIF ? 2 :
                fontItem.getType() == Typeface.SANS_SERIF ? 3 : -1;

        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra("FontStyle", fontItem.getStyle());
        intent.putExtra("FontTypeface", typeId);
        intent.putExtra("FontColor", fontItem.getTextColor().getColor());
        intent.putExtra("FontSize", fontItem.getTextSize());
        setResult(RESULT_OK, intent);
        finish();
    }

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

        fontItem = new FontItem();

        sampleText = (TextView) findViewById(R.id.sampleText);

        sampleText.setTextColor(fontItem.getTextColor().getColor());

        Spinner spinner = (Spinner) findViewById(R.id.fontFamily);
        spinner.setOnItemSelectedListener(this);

        spinner = (Spinner) findViewById(R.id.fontStyles);
        spinner.setOnItemSelectedListener(this);

        intent = getIntent();
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getSelectedItem().toString();

        switch(item){
            case "Bold":
                fontItem.setStyle(Typeface.BOLD);
                break;
            case "Bold Italic":
                fontItem.setStyle(Typeface.BOLD_ITALIC);
                break;
            case "Italic":
                fontItem.setStyle(Typeface.ITALIC);
                break;
            case "Normal":
                fontItem.setStyle(Typeface.NORMAL);
                break;
            case "Monospaced":
                fontItem.setType(Typeface.MONOSPACE);
                break;
            case "Serif":
                fontItem.setType(Typeface.SERIF);
                break;
            case "Sans Serif":
                fontItem.setType(Typeface.SANS_SERIF);
                break;
        }
        sampleText.setTypeface(fontItem.getType(), fontItem.getStyle());
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

            switch (seekBar.getId()) {
                case R.id.redSeek:
                    fontItem.getTextColor().setRed(progress);
                    break;
                case R.id.greenSeek:
                    fontItem.getTextColor().setGreen(progress);
                    break;
                case R.id.blueSeek:
                    fontItem.getTextColor().setBlue(progress);
                    break;
                case R.id.alphaSeek:
                    fontItem.getTextColor().setAlpha(progress);
                    break;
                case R.id.textSizeSeek:
                    fontItem.setTextSize(progress + 10);
                    sampleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontItem.getTextSize());
                    break;
            }

            sampleText.setTextColor(fontItem.getTextColor().getColor());
        }


    }

}
