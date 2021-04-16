package com.example.csc250_spring2021_mergesort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ListView listOfNumbersLV;
    private ArrayList<String> theListOfNumbersAsStrings;
    private ArrayAdapter<String> theListOfNumbersAdapter;

    private ListView theCallsToMergeSortLV;
    private ArrayList<String> theListOfMergeSortCalls;
    private ArrayAdapter<String> theCallsToMergeSortAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listOfNumbersLV = this.findViewById(R.id.listOfNumbersLV);

        //this guy will show the parts of the arraylist we are working on in mergesort
        this.theListOfMergeSortCalls = new ArrayList<String>();
        this.theCallsToMergeSortAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                this.theListOfMergeSortCalls);
        this.theCallsToMergeSortLV = this.findViewById(R.id.theCallsToMergeSortLV);
        this.theCallsToMergeSortLV.setAdapter(this.theCallsToMergeSortAdapter);


        this.theListOfNumbersAsStrings = new ArrayList<String>();
        this.theListOfNumbersAsStrings.add("5");
        this.theListOfNumbersAsStrings.add("2");
        this.theListOfNumbersAsStrings.add("8");
        this.theListOfNumbersAsStrings.add("3");
        this.theListOfNumbersAsStrings.add("13");
        this.theListOfNumbersAsStrings.add("5");
        this.theListOfNumbersAsStrings.add("5");
        this.theListOfNumbersAsStrings.add("8");
        this.theListOfNumbersAsStrings.add("1");

        this.theListOfNumbersAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                this.theListOfNumbersAsStrings);

        this.listOfNumbersLV.setAdapter(theListOfNumbersAdapter);
    }

    private String buildStringFromPartOfList(ArrayList<String> theList, int begin, int end)
    {
        String s = "";
        for(int i = begin; i <= end; i++)
        {
            s = s + theList.get(i) + " ";
        }
        return s;
    }

    private void merge(ArrayList<String> theList, int begin1, int end1, int begin2, int end2)
    {
        //creates a temp array big enough to hold all the value from begin1 to end2
        int[] temp = new int[end2 - begin1 + 1];
        int pos1 = begin1;
        int pos2 = begin2;
        for(int i = 0; i < temp.length; i++)
        {
            if(pos1 <= end1 && pos2 <= end2)
            {
                if(Integer.parseInt(theList.get(pos1)) < Integer.parseInt(theList.get(pos2)))
                {
                    temp[i] = Integer.parseInt(theList.get(pos1));
                    pos1++;
                }
                else
                {
                    temp[i] = Integer.parseInt(theList.get(pos2));
                    pos2++;
                }
            }
            else if(pos1 <= end1)
            {
                temp[i] = Integer.parseInt(theList.get(pos1));
                pos1++;
            }
            else
            {
                temp[i] = Integer.parseInt(theList.get(pos2));
                pos2++;
            }
        }
        //temp now holds our values in order, we need to copy them back into the list
        //between buckets begin1 and end2
        int theListPos = begin1;
        for(int i = 0; i < temp.length; i++)
        {
            theList.set(theListPos, "" + temp[i]);
            theListPos++;
        }
    }

    private void mergeSort(ArrayList<String> theList, int begin, int end)
    {
        //ask if this list is trivially sorted
        //I only want to do anything inside mergeSort if it is NOT trivially sorted
        String currentPartOfArray = this.buildStringFromPartOfList(theList, begin, end);
        this.theListOfMergeSortCalls.add(currentPartOfArray);
        this.theCallsToMergeSortAdapter.notifyDataSetChanged();

        if(begin != end)
        {
            //I do NOT have a 1-list, so I need to divide my list in half and
            //call mergesort on the right and the left
            //HW: update the theListOfMergeSortCalls to show all of the individual calls we
            //make to mergesort
            int begin1 = begin;
            int end1 = (begin + end)/2;
            int begin2 = end1 + 1;
            int end2 = end;
            this.mergeSort(theList, begin1, end1);
            this.mergeSort(theList, begin2, end2);

            //when we get here, we know that the first and second halves are both sorted
            this.merge(theList, begin1, end1, begin2, end2);
        }
    }

    public void onMergeSortButtonClicked(View v)
    {
        this.mergeSort(this.theListOfNumbersAsStrings,
                0, this.theListOfNumbersAsStrings.size()-1);
        //our ArrayList should now be sorted, so let him know to update his view
        this.theListOfNumbersAdapter.notifyDataSetChanged();
    }
}