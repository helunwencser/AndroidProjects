package edu.cmu.lunwenh.scorestatistics.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import edu.cmu.lunwenh.scorestatistics.R;
import edu.cmu.lunwenh.scorestatistics.controller.DBController;
import edu.cmu.lunwenh.scorestatistics.model.Record;

public class ScoreStatistics extends AppCompatActivity {

    /* database controller for control database */
    DBController dbController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbController = new DBController(this);

        InputStream inputStream = this.getResources().openRawResource(R.raw.score);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        try {
            while((line = bufferedReader.readLine()) != null) {
                dbController.insertRecord(constructRecord(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* get all records stroed in database */
        List<Record> records = dbController.selectRecords();

        /* table title */
        TableLayout tableLayout = (TableLayout)this.findViewById(R.id.tableLayout);
        tableLayout.addView(getRow(new String[]{
                "Student",
                "Q1",
                "Q2",
                "Q3",
                "Q4",
                "Q5"
        }));

        /* students' scores */
        for(Record record : records) {
            tableLayout.addView(getRow(new String[]{
                    record.getId(),
                    new Integer(record.getQ1()).toString(),
                    new Integer(record.getQ2()).toString(),
                    new Integer(record.getQ3()).toString(),
                    new Integer(record.getQ4()).toString(),
                    new Integer(record.getQ5()).toString()
            }));
        }

        /* statistics information */
        int[] highScores = getHighScores(records);
        tableLayout.addView(getRow(new String[]{
                "High Score",
                new Integer(highScores[0]).toString(),
                new Integer(highScores[1]).toString(),
                new Integer(highScores[2]).toString(),
                new Integer(highScores[3]).toString(),
                new Integer(highScores[4]).toString(),
        }));
        int[] lowScores = getLowScors(records);
        tableLayout.addView(getRow(new String[]{
                "Low Score",
                new Integer(lowScores[0]).toString(),
                new Integer(lowScores[1]).toString(),
                new Integer(lowScores[2]).toString(),
                new Integer(lowScores[3]).toString(),
                new Integer(lowScores[4]).toString()
        }));
        double[] averageScores = getAverageScores(records);
        tableLayout.addView(getRow(new String[]{
                "Ave Score",
                new Double(averageScores[0]).toString(),
                new Double(averageScores[1]).toString(),
                new Double(averageScores[2]).toString(),
                new Double(averageScores[3]).toString(),
                new Double(averageScores[4]).toString(),
        }));
    }

    /**
     * get high scores from all records
     * @param records
     *        all records of all students' scores
     *
     * @return array contains high scores for each quiz
     * */
    private int[] getHighScores(List<Record> records) {
        int[] res = new int[5];
        for(Record record : records) {
            res[0] = res[0] < record.getQ1() ? record.getQ1() : res[0];
            res[1] = res[1] < record.getQ2() ? record.getQ2() : res[1];
            res[2] = res[2] < record.getQ3() ? record.getQ3() : res[2];
            res[3] = res[3] < record.getQ4() ? record.getQ4() : res[3];
            res[4] = res[4] < record.getQ5() ? record.getQ5() : res[4];
        }
        return res;
    }

    /**
     * get low scores for each quiz
     * @param records
     *        all records of all students' scores
     *
     * @return array contains low scores for each quiz
     * */
    private int[] getLowScors(List<Record> records) {
        int[] res = new int[5];
        for(int i = 0; i < 5; i++) {
            res[i] = Integer.MAX_VALUE;
        }
        for(Record record : records) {
            res[0] = res[0] < record.getQ1() ? res[0] : record.getQ1();
            res[1] = res[1] < record.getQ2() ? res[1] : record.getQ2();
            res[2] = res[2] < record.getQ3() ? res[2] : record.getQ3();
            res[3] = res[3] < record.getQ4() ? res[3] : record.getQ4();
            res[4] = res[4] < record.getQ5() ? res[4] : record.getQ5();
        }
        return res;
    }

    /**
     * get average scores for each quiz
     * @param records
     *        all records of all students' scores
     *
     * @return array contains average scores for each quiz
     * */
    private double[] getAverageScores(List<Record> records) {
        NumberFormat numberFormat = new DecimalFormat("#0.0");
        double[] res = new double[5];
        for(Record record : records) {
            res[0] += record.getQ1();
            res[1] += record.getQ2();
            res[2] += record.getQ3();
            res[3] += record.getQ4();
            res[4] += record.getQ5();
        }
        for(int i = 0; i < 5; i++) {
            res[i] /= records.size();
            res[i] = Double.parseDouble(numberFormat.format(res[i]));
        }
        return res;
    }

    /**
     * generate a TableRow
     * @param columns
     *        array contains contents for all columns
     *
     * @return generated TableRow
     * */
    private TableRow getRow(String[] columns) {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));

        for (int i = 0; i < columns.length; i++) {

            TextView textView = new TextView(this);
            textView.setLayoutParams(new LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT)
            );
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(18);
            textView.setPadding(0, 5, 0, 5);
            textView.setText(columns[i]);

            tableRow.addView(textView);
        }
        return tableRow;
    }

    /**
     * construct one record from a string
     * @param line
     *        string line to be constructed for one record
     *
     * @return the record constructed
     * */
    private Record constructRecord(String line) {
        String[] elements = line.split("\t");
        return new Record(
                elements[0],
                Integer.parseInt(elements[1]),
                Integer.parseInt(elements[2]),
                Integer.parseInt(elements[3]),
                Integer.parseInt(elements[4]),
                Integer.parseInt(elements[5])
                );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
