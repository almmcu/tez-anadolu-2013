package tr.anadolu.edu.ceng.bim444.tez.client;

import java.util.Comparator;

/**
 * Created by ali on 5/8/2014.
 */
public class Word  implements java.io.Serializable , Comparable<Word>{
    private String accelerometer ;
    private  String timeInterval  ;

    public Word() {
    }

    public String getAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(String accelerometer) {
        this.accelerometer = accelerometer;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public int compareTo(Word another) {
        String compareIngilizce = ((Word) another).getAccelerometer();

        //ascending order
        return this.accelerometer.compareToIgnoreCase(compareIngilizce );
    }
    public static Comparator<Word> FruitNameComparator = new Comparator<Word>() {

        public int compare(Word fruit1, Word fruit2) {

            String fruitName1 = fruit1.accelerometer.toUpperCase();
            String fruitName2 = fruit2.getTimeInterval().toUpperCase();

            //ascending order
            return fruitName1.compareToIgnoreCase(fruitName2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };
}
