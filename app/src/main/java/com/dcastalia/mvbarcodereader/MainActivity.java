package com.dcastalia.mvbarcodereader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import devliving.online.mvbarcodereader.BarcodeCaptureFragment;
import devliving.online.mvbarcodereader.MVBarcodeScanner;

public class MainActivity extends AppCompatActivity {
    BarcodeCaptureFragment fragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         MVBarcodeScanner.ScanningMode mode = null;
         @MVBarcodeScanner.BarCodeFormat int[] formats = null;
         fragment = BarcodeCaptureFragment.instantiate(mode, formats);
        fragmentTransaction();

    }

    private void fragmentTransaction() {


        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, fragment)
                .commitAllowingStateLoss();



        fragment.setListener(new BarcodeCaptureFragment.BarcodeScanningListener() {
            @Override
            public void onBarcodeScanned(Barcode barcode) {
                Toast.makeText(MainActivity.this, barcode.displayValue, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onBarcodesScanned(List<Barcode> barcodes) {

            }

            @Override
            public void onBarcodeScanningFailed(String reason) {
                Toast.makeText(MainActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void scanAgain(View view) {
        Toast.makeText(this, "Scanning Again", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager()
                .beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commitAllowingStateLoss();
    }
}
