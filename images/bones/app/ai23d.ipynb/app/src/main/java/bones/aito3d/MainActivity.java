package bones.aito3d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {


     EditText mName;

     EditText mGenre;

     EditText mAge;

    private int age;
    private String urlImage;

    //private MaterialDialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = (EditText) findViewById(R.id.edt_name);
        mGenre = (EditText) findViewById(R.id.edt_genre);
        mAge = (EditText) findViewById(R.id.edt_age);

        mGenre.setKeyListener(null);
        mGenre.setFocusable(false);

    }


    public void oClickGenre(View view) {

        new MaterialDialog.Builder(this)
                .title("Genre")
                .items(R.array.genre_array)
                .itemsCallback((dialog, view1, which, text) -> {
                    mGenre.setText(text);
                })
                .show();

    }


}
