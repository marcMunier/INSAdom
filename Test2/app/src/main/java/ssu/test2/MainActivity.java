package ssu.test2;


        import android.app.Activity;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button g= (Button)findViewById(R.id.http);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv = (TextView) findViewById(R.id.tView);
                new GetData(tv).execute("");
            }
        });
    }

    private class GetData extends AsyncTask<String, Void, String> {
        private TextView display;

        GetData(TextView view){
            this.display = view;
        }

        @Override
        protected String doInBackground(String... message) {
            HttpClient httpclient;
            HttpGet request;
            HttpResponse response = null;
            String result = " ";

            try {
                httpclient = new DefaultHttpClient();
                request = new HttpGet("http://api.wipmania.com/json");
                response = httpclient.execute(request);
            }
            catch (Exception e) {
                result = "error";
            }

            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null)
                {
                    result = result + line ;
                }
            } catch (Exception e) {
                result = "error";
            }
            return result;
        }

        protected void onPostExecute(String result) {
            this.display.setText(result);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
