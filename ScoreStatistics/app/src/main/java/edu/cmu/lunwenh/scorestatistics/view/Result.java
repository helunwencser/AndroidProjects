package edu.cmu.lunwenh.scorestatistics.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import edu.cmu.lunwenh.scorestatistics.R;

/**
 * Created by lunwenh on 3/31/2016.
 */
public class Result extends Fragment {
    /**
     * generate a TableRow
     * @param columns
     *        array contains contents for all columns
     *
     * @return generated TableRow
     * */
    private TableRow getRow(String[] columns) {
        TableRow tableRow = new TableRow(getActivity());
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));

        for (int i = 0; i < columns.length; i++) {

            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
            );
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(18);
            textView.setPadding(0, 5, 0, 5);
            textView.setText(columns[i]);

            tableRow.addView(textView);
        }
        return tableRow;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.calculate_result, container, false);
        int[] highScores = getArguments().getIntArray("high");
        int[] lowScores = getArguments().getIntArray("low");
        double[] averageScores = getArguments().getDoubleArray("average");

        TableLayout tableLayout = (TableLayout)fragmentView.findViewById(R.id.tableLayoutForResult);

        tableLayout.addView(getRow(new String[]{
                "High",
                new Integer(highScores[0]).toString(),
                new Integer(highScores[1]).toString(),
                new Integer(highScores[2]).toString(),
                new Integer(highScores[3]).toString(),
                new Integer(highScores[4]).toString(),
        }));

        tableLayout.addView(getRow(new String[]{
                "Low",
                new Integer(lowScores[0]).toString(),
                new Integer(lowScores[1]).toString(),
                new Integer(lowScores[2]).toString(),
                new Integer(lowScores[3]).toString(),
                new Integer(lowScores[4]).toString()
        }));

        tableLayout.addView(getRow(new String[]{
                "Ave",
                new Double(averageScores[0]).toString(),
                new Double(averageScores[1]).toString(),
                new Double(averageScores[2]).toString(),
                new Double(averageScores[3]).toString(),
                new Double(averageScores[4]).toString(),
        }));

        return fragmentView;
    }
}
