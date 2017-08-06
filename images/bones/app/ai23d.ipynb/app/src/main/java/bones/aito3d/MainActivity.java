package bones.aito3d;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edt_name)
    EditText mName;

    @BindView(R.id.edt_genre)
    EditText mGenre;

    @BindView(R.id.edt_age)
    EditText mAge;

    @BindView(R.id.text_view_field)
    TextView mResult;

    //Service service;

    private String tagFile;

    private OkHttpClient client;

    private MediaType JSON;

    private String information = "http://192.168.43.36:5000/predict?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mGenre.setKeyListener(null);
        mGenre.setFocusable(false);

        initializeRequest();

    }

    private void initializeRequest() {

        client = new OkHttpClient();

        JSON = MediaType.parse("application/json; charset=utf-8");

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


    public void onClickBroken(View view) {
        tagFile = (String) view.getTag();
    }


    public void onClickGood(View view) {
        tagFile = (String) view.getTag();
    }

    @OnClick(R.id.btn_send)
    public void onClickSend(View view) {
        sendData();
    }


    public class OkHttpHandler extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                okhttp3.Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                mResult.setText(s);
                mResult.setVisibility(View.VISIBLE);

            } catch (Exception error) {
                Log.d("EXCEPTION: ", error.getMessage());
            }

        }


    }


    private void sendData() {

        information += "name=" + mName.getText().toString() + "&genre=" + mGenre.getText().toString() + "&age=" + mAge.getText().toString() + "&image=" + tagFile;

        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute(information);

    }


}
