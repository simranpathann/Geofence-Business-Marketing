package my.food.business_marketing;

import android.location.Location;

public class Diffbtnlatlong {


    public  Diffbtnlatlong(double startLat, double startLang, double endLat, double endLang) {

        Location locStart = new Location("");
        locStart.setLatitude(startLat);
        locStart.setLongitude(startLang);

        Location locEnd = new Location("");
        locEnd.setLatitude(endLat);
        locEnd.setLongitude(endLang);
        System.out.println(locStart.distanceTo(locEnd));

    }

    Diffbtnlatlong dbl = new Diffbtnlatlong(18.23144,75.67499,18.23247,75.67971);

}








