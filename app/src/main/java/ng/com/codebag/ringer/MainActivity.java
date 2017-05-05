package ng.com.codebag.ringer;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1;
    private AudioManager mAudioManager;
    private boolean mPhoneIsSilent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

        checkIfPhoneIsSIlent();
        setButtonClick();
    }

    private void setButtonClick() {

        b1 = (Button) findViewById(R.id.toggle_button);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (mPhoneIsSilent)
                {
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    mPhoneIsSilent = false;
                }else
                {
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    mPhoneIsSilent = true;
                }

                toggleUi();
            }
        });
    }

    //checking if phone is silent
    private void checkIfPhoneIsSIlent()
    {
        int ringerMode = mAudioManager.getRingerMode();
        if (ringerMode == AudioManager.RINGER_MODE_SILENT)
        {
                mPhoneIsSilent = true;
        }else
        {
                mPhoneIsSilent = false;
        }
    }

    private void toggleUi()
    {
        ImageView imageView = (ImageView) findViewById(R.id.image);
        Drawable newImage;

        if (mPhoneIsSilent)
        {
            newImage = getResources().getDrawable(R.drawable.volume_off);
        }else
        {
            newImage = getResources().getDrawable(R.drawable.ic_action_name);
        }

        imageView.setImageDrawable(newImage);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        checkIfPhoneIsSIlent();
        toggleUi();
    }
}
