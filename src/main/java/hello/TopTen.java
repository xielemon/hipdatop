package hello;

import java.util.Date;
import java.util.PriorityQueue;

public class TopTen {


    private PriorityQueue topTen;

    private Date time;


    public PriorityQueue getTopTen() {
        return topTen;
    }

    public void setTopTen(PriorityQueue topTen) {
        this.topTen = topTen;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
