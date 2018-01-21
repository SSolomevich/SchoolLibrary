package com.company;

import java.util.Comparator;

/**
 * Created by 15 on 20.01.2018.
 */
public class NumberBooksReadComporator implements Comparator<Pupil>
{
    public int compare(Pupil o1, Pupil o2)
    {
        return o1.getNumberBooksRead().compareTo(o2.getNumberBooksRead());
    }
}