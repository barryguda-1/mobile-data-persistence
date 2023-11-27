package vistula.xy.lab08_guda_66443;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText inputData;
    private Button sendToMemory, readFrmMemory;
    private TextView textViewIn, readViewOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputData = findViewById(R.id.memoryEditTxt_id);
        sendToMemory = findViewById(R.id.send_to_memory_btn);
        textViewIn = findViewById(R.id.inputToBeSent_Vw);
        readViewOut = findViewById(R.id.read_from_memory_VW);
        readFrmMemory = findViewById(R.id.read_from_memoy_btn);

        sendToMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataInput = inputData.getText().toString();
                textViewIn.setText(dataInput);
                writeToFile(dataInput);
            }
        });

        readFrmMemory.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                readFromFile();
            }
        });

    }

    private void writeToFile(String data) {
        try {
            FileOutputStream fos = openFileOutput("user_Data.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(data);
            osw.flush();
            osw.close();
            //fos.close();
            Toast.makeText(this, "Data written to file successfully", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();


        }
    }

    private void readFromFile(){
        try {
            // Create a FileInputStream to read from the file
            FileInputStream fis = openFileInput("user_Data.txt");

            //Create an InputStreamReader to handle character encoding
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }

            fis.close();

            readViewOut.setText("Data read from file:\n" + stringBuilder.toString());

            Toast.makeText(this, "Data read from file successfully", Toast.LENGTH_SHORT).show();


        } catch (IOException e){
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    

}