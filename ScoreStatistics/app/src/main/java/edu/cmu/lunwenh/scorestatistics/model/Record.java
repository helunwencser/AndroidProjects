package edu.cmu.lunwenh.scorestatistics.model;

/**
 * Created by lunwenh on 3/27/2016.
 */
public class Record {
    /* student id */
    private String id;
    /* score of q1 */
    private int q1;
    /* score of q2 */
    private int q2;
    /* score of q3 */
    private int q3;
    /* score of q4 */
    private int q4;
    /* score of q5 */
    private int q5;

    public Record(String id, int q1, int q2, int q3, int q4, int q5) {
        this.id = id;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public int getQ4() {
        return q4;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
    }

    public int getQ5() {
        return q5;
    }

    public void setQ5(int q5) {
        this.q5 = q5;
    }
}
