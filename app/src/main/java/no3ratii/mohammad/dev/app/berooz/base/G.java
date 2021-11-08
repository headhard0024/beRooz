package no3ratii.mohammad.dev.app.berooz.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import java.util.Date;

import saman.zamani.persiandate.PersianDate;


public class G extends Application {
    public static Context context;
    public static LayoutInflater inflater;
    public static Resources resources;
    public static Long currentTimeMillis;
    public static PersianDate currentPersianTime;


    private PersianDate nowCurrentTime() {
        Date resultdate = new Date(System.currentTimeMillis());
        Date myDate = new Date(String.valueOf(resultdate));
        return new PersianDate(myDate);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resources = getResources();
        currentTimeMillis = System.currentTimeMillis();
        currentPersianTime = nowCurrentTime();

        /**
         * بازی
         * "https://www.itna.ir/rsshi.c120z29cc0y3xu1go.t22t..zkdfafz2la.xml"

         * فناوری
         * "https://www.itna.ir/rssf0.47wrywq44r,6mp73f..gwagiy.winwy-n.xml"
         * Technology

         * موبايل
         * https://www.itna.ir/rssdx.gmyefy,ggeltshmci.2yy2..fz6avafy3v.xml
         *

         * روباتيك
         * https://www.itna.ir/rssew.skj1zjyss1rhx2k4m.bjjb..w7i9g9zjqg.xml
         * robotic

         * سخت افزار
         * https://www.itna.ir/rssb5.-er48r6--4qhfle2m.urru..f0pioi8rvo.xml

         * اينترنت
         * https://www.itna.ir/rssci.uy2-s2tuu-5b1,ydk.a22a..i48l3ls273.xml

         * شبكه اجتماعی
         * https://www.itna.ir/rssf0.47wrywq44r,6mp73f.iwwi..mxagngyw-n.xml
         * socialNetwork

         * دانش بنیان
         * https://www.itna.ir/rssew.skj1zjyss1rhx2k4m.bjjb..xoi9g9zjqg.xml
         * KnowledgeBase

         * فرصت های شغلی
         * https://www.itna.ir/rssdx.gmyefy,ggeltshmci.2yy2..sj6avafy3v.xml

         * شبكه و امنيت
         * https://www.itna.ir/rsshi.c120z29cc0y3xu1go.t22t..ixdfafz2la.xml

         * ارتباطات
         * https://www.itna.ir/rssgx.dja5qa6dd51kwvjoe..pa4prx.arlaq0l.xml

         * ماهواره و فضا
         * https://www.itna.ir/rssau.dg4t64qddth9e,gc7.k44k..ui15f564lf.xml
         * connections

         * برنامه نويسی
         * https://www.itna.ir/rssj8.12u9vuk119bqtw25g.fuuf..8izs4svul4.xml

         * مديريت ICT
         * https://www.itna.ir/rssb5.-er48r6--4qhfle2m..irpiuz.ruor8vo.xml

         * نرم افزار
         * https://www.itna.ir/rssir.,ltizt4,,ik1pxlne..bt2bc5.tcdtz7d.xml
         */
    }
}
