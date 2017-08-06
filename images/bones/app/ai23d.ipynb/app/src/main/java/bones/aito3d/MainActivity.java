package bones.aito3d;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edt_name)
    EditText mName;

    @BindView(R.id.edt_genre)
    EditText mGenre;

    @BindView(R.id.edt_age)
    EditText mAge;

    private String urlImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mGenre.setKeyListener(null);
        mGenre.setFocusable(false);

    }

    @OnClick(R.id.edt_genre)
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
