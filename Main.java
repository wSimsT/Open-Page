import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpResponse;
import java.util.Scanner; 

class GenerateRecommendation implements ActionListener{
    JTextField t1;
    JButton b;
    GenerateRecommendation() {
        JFrame f = new JFrame();
        t1=new JTextField("What genre Of book are you looking for?");  
        t1.setBounds(50,100, 200,30);  
        b=new JButton("Generate New Book Recommendation");
        b.setBounds(130,150,100, 40);
        b.addActionListener(this);
        f.add(t1);
        f.add(b);
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String genreType = t1.getText();
        String subjectURL = "https://openlibrary.org/subjects/"+genreType+".json?details=false";
        URL url = null;
        try {
            url = new URI(subjectURL).toURL();
        } catch (MalformedURLException | URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        HttpsURLConnection connection= null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        connection.setRequestProperty("accept", "application/json");
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        int responseCode = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if(responseCode == HttpsURLConnection.HTTP_OK){
            try {
                StringBuilder sb = new StringBuilder();
                Scanner scanner = new Scanner(connection.getInputStream());
                while(scanner.hasNext()){
                    sb.append(scanner.nextLine());
                }
                System.out.println(sb);
                
                scanner.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
       new GenerateRecommendation();
    }
}
