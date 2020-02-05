package api;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class WallEdit extends Wall {
    public String editMessage(String post) throws URISyntaxException, IOException {
        String anotherText = "I love Automation";
        URIBuilder builder = GetUriBuilder("edit", ACCESS_TOKEN, OWNER_ID, V);
        builder.setParameter("post_id", post);
        builder.setParameter("message", anotherText);
        HttpResponse response = getResponse(builder);
        String idPost = EntityUtils.toString(response.getEntity()).substring(23,27);
        System.out.println("send " + idPost);
        return idPost;
    }
}
