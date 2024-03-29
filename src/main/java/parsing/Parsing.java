package parsing;

import model.User;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parsing {
    private static final String USERS_XML = "Users.xml";
    public List<String> mails = new ArrayList<String>();

    public void parse() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(USERS_XML);
        List<User> users = new DomParserUser().parse(document);

        users.forEach(user -> System.out.println(user));
        users.forEach(user -> mails.add(user.getE_mail()));
    }
}
