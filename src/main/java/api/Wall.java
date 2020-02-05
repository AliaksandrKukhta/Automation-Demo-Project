package api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Wall {
    protected final String BASE_URL = "https://api.vk.com/method/wall.%s?";
    protected final String ACCESS_TOKEN =
            "***********************************************";
    protected final String OWNER_ID = "*******";
    protected final String V = "5.103";

    public URIBuilder GetUriBuilder(String action, String access_token, String owner_id, String v)
            throws URISyntaxException {
        URIBuilder builder = new URIBuilder(String.format(BASE_URL, action));
        builder.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("v", V);
        return builder;
    }

    public HttpResponse getResponse(URIBuilder builder) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        return response;
    }

}
