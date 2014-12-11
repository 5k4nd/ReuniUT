package fr.baptabl.reuniut;

/**
 * Created by bat on 09/12/14.
 */
public class login {
    private String login;
    private String password;

    public login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String getTicket() {
        String ticket = null;

        ticket = "login";
        // connexionCAS('login', 'password');

    /*
    void connexionCAS() {
        // HttpClient httpclient = new DefaultHttpClient();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        String html = "";
        InputStream in = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder str = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null)
        {
            str.append(line);
        }
        in.close();
        html = str.toString();


    }
*/
        return ticket;
    }


}
