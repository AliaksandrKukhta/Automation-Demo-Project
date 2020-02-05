package api;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class WallDelete extends Wall {
    public String deleteMessage(String number) throws URISyntaxException, IOException {
        URIBuilder builder = GetUriBuilder("delete", ACCESS_TOKEN, OWNER_ID, V);
        builder.setParameter("post_id", number);
        HttpResponse response = getResponse(builder);
        System.out.println(EntityUtils.toString(response.getEntity()));
        return number;
    }
}
