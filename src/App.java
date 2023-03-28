import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        JsonParser parser = new JsonParser();
        List<Map<String,String>> filmes = parser.parse(body);
        
        GeradorDeFigurinhas gerador = new GeradorDeFigurinhas();

        for(Map<String,String> filme : filmes) {
            System.out.println("\u001b[1m \u001b[34m Title: \u001b[m" + filme.get("title"));
            
            System.out.println("\u001b[1m \u001b[34m Poster: \u001b[m" + filme.get("image"));

            InputStream inputStream = new URL(filme.get("image")).openStream();
            String titleImage = filme.get("title").replace(":","-") + ".png";
            String path = gerador.criar(inputStream, titleImage.replace(" ",""));
            
            System.out.println("\u001b[1m \u001b[34m Figurinha: \u001b[m" + path);
            
            int stars = (int) Double.parseDouble(filme.get("imDbRating"));
            String color = changeColor(stars);

            System.out.print("\u001b[1m " +color+ "Rating: " + filme.get("imDbRating")+" (");
            for(int i=0; i<stars ;i++) {
                System.out.print("\u2B50");
            }
            System.out.println(")\u001b[m\n");
        } 

    }

    public static String changeColor(int number) {
        switch(number) {
            case 9:
                return "\u001b[42m";
            case 8:
                return "\u001b[43m";
            case 7:
                return "\u001b[41m";
            default:
                return "\u001b[47m";
        }
    }
}