package parse;

import com.sihun.apimaketest.MainActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import VO.BookVO;

public class Parser {
    BookVO vo;
    String myQuery = "";

    public ArrayList<BookVO>connectNaver(ArrayList<BookVO> list){

       try{
            myQuery = URLEncoder.encode( MainActivity.search.getText().toString(), "UTF8");

            int count = 100;
            String urlStr = "https://openapi.naver.com/v1/search/book.xml?query=" + myQuery + "&display="+count;
            URL url = new URL(urlStr);
           HttpURLConnection connection = (HttpURLConnection)url.openConnection();
           connection.setRequestProperty("X-Naver-Client-Id", "CPdbdRXnX0_TZOR7kEeR");
           connection.setRequestProperty("X-Naver-Client-Secret", "UeoNuc8jEV");
           XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
           XmlPullParser parser = factory.newPullParser();
           parser.setInput( connection.getInputStream(), null );
           int parserEvent = parser.getEventType();
           while( parserEvent != XmlPullParser.END_DOCUMENT){
               if (parserEvent == XmlPullParser.START_TAG){
                   String tagName = parser.getName();
                   if (tagName.equalsIgnoreCase("title")){
                       vo = new BookVO();
                       String title = parser.nextText();
                       vo.setB_title(title);

                   }else if(tagName.equalsIgnoreCase("image")){
                       String img = parser.nextText();
                       vo.setB_img(img);
                   }else if(tagName.equalsIgnoreCase("author")){
                        String author = parser.nextText();
                        vo.setB_author(author);
                   }else if(tagName.equalsIgnoreCase("price")){
                       String price = parser.nextText();
                       vo.setB_price(price);
                       list.add(vo);
                   }
               }
                parserEvent = parser.next();

           }


       }catch (Exception e){

       }
        return list;
    }
}
