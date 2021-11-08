package no3ratii.mohammad.dev.app.berooz.base.helper.RSS;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * parse rss feed and return list form items and set A list for images
 * becose this feed hase proble in parse images
 */
public class RssParser extends DefaultHandler {

    private StringBuilder content;
    private String vlaue;
    private boolean inItem;
    private boolean inChanel;
    private boolean inImage;
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private ArrayList<String> imageList = new ArrayList<String>();
    private Chanel chanel = new Chanel();
    private Item lastItem;


    public class Item {
        public String title;
        public String descrption;
        public String link;
        public String category;
        public String pubData;
        public String guid;
        public String enclosure;
    }


    public class Chanel {
        public String title;
        public String description;
        public String link;
        public String lastBuilderData;
        public String genetator;
        public String imageUrl;
        public String imageLink;
        public String imageWidth;
        public String imageHright;
        public String imageDescrption;
        public String language;
        public String copyRight;
        public String pubData;
        public String category;
        public String ttl;
    }


    public RssParser(String url) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            URL sourceUrl = new URL(url);
            xr.setContentHandler(this);
            xr.parse(new InputSource(sourceUrl.openStream()));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        content = new StringBuilder();
        if (localName.equalsIgnoreCase("image")) {
            inImage = true;
        }

        if (localName.equalsIgnoreCase("channel")) {
            inChanel = true;
        }

        if (localName.equalsIgnoreCase("item")) {
            lastItem = new Item();
            itemList.add(lastItem);
            inItem = true;
        }

        if ("enclosure".equals(qName)) {
            for (int i = 0; i < atts.getLength(); i++) {
                if (atts.getQName(i).equals("url")) {
                    String url = atts.getValue(i);
                    imageList.add(url);
                }
            }
        }


    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (content != null) {
            content.append(ch, start, length);
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equalsIgnoreCase("image")) {
            inImage = false;
        } else if (localName.equalsIgnoreCase("channel")) {
            inChanel = false;
        } else if (localName.equalsIgnoreCase("item")) {
            inItem = false;
        }

        if (inItem) {
            if (localName.equalsIgnoreCase("title")) {
                lastItem.title = content.toString();
                // Log.i("LOG", "title In Item" + content.toString());
            }
            if (localName.equalsIgnoreCase("description")) {
                lastItem.descrption = content.toString();
                //Log.i("LOG", "description In Item" + content.toString());
            }
            if (localName.equalsIgnoreCase("link")) {
                lastItem.link = content.toString();
                // Log.i("LOG", "link In Item" + content.toString());
            }
            if (localName.equalsIgnoreCase("category")) {
                lastItem.category = content.toString();
            }
            if (localName.equalsIgnoreCase("pubDate")) {
                lastItem.pubData = content.toString();
            }
            if (localName.equalsIgnoreCase("guid")) {
                lastItem.guid = content.toString();
            }
            if (localName.equalsIgnoreCase("enclosure")) {
                lastItem.enclosure = content.toString();
            }
        } else if (inImage) {
            if (localName.equalsIgnoreCase("url")) {
                chanel.imageUrl = content.toString();
            }
        } else if (inChanel) {
            if (localName.equalsIgnoreCase("title")) {
                chanel.title = content.toString();
                // Log.i("LOG", "inChanel title:" + content.toString());
            }
            if (localName.equalsIgnoreCase("description")) {
                chanel.description = content.toString();
                // Log.i("LOG", "description" + content.toString());
            }
            if (localName.equalsIgnoreCase("category")) {
                chanel.category = content.toString();
            }
        }
        content = null;
    }


    public Item getItem(int index) {
        return itemList.get(index);
    }


    public ArrayList<Item> getItems() {
        return itemList;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public Chanel getChanel() {
        return chanel;
    }
}
