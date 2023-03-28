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
            InputStream inputStream = new URL(filme.get("image")).openStream();
            String titleImage = filme.get("title").replace(":","-") + ".png";
            gerador.criar(inputStream, titleImage);
        } 

    }
}
