package com.company;

import java.util.Comparator;

/**
 * Created by 15 on 20.01.2018.
 */
public class CalendarComparator implements Comparator<Pupil>
{
    public int compare(Pupil o1, Pupil o2)
    {
        return o1.getDateBirth().compareTo(o2.getDateBirth());
    }
}
