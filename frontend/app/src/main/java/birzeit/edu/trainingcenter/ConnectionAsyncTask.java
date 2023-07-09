package birzeit.edu.trainingcenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
public class ConnectionAsyncTask extends AsyncTask<String, String,
        String> {

    private final String httpMethod;
    private final String requestBody;
    public ConnectionAsyncTask(String httpMethod, String requestBody) {

        this.httpMethod = httpMethod;
        this.requestBody = requestBody;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected String doInBackground(String... params) {
        String data = "";

        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(httpMethod);

            if (httpMethod.equals("POST") || httpMethod.equals("PATCH")) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
                outputStream.close();
            }

            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line + '\n');
                line = bufferedReader.readLine();
            }
            data = stringBuilder.toString();
        } catch (Exception ex) {
            Log.d("HttpURLConnection", ex.toString());
        }

        return data;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
