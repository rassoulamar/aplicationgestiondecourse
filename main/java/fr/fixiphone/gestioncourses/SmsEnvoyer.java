package fr.fixiphone.gestioncourses;




import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

// --------------------
public class SmsEnvoyer extends Activity implements OnClickListener {

    private EditText editTextTelephone;
    private EditText editTextMessage;
    private Button buttonEnvoyer;
    private TextView textViewMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_envoyer);

        editTextTelephone = (EditText)findViewById(R.id.editTextTelephone);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonEnvoyer = findViewById(R.id.buttonEnvoyer);
        textViewMessage = findViewById(R.id.textViewMessage);

        buttonEnvoyer.setOnClickListener(this);
        textViewMessage.setText("");

        // EN TEST
        editTextTelephone.setText("1-555-521-5556");
        editTextMessage.setText("Bonne nuit les petits !!!");

    } /// onCreate

    @Override
    public void onClick(View v) {
        String numero = editTextTelephone.getText().toString();
        String message = editTextMessage.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numero, null, message, null, null);
        textViewMessage.setText("SMS envoyé !!!");
    } /// onClick

} /// SmsEnvoyer
