/*
 * Copyright 2013-2015 the Uranoplums Foundation and the Others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * $Id: RowCalendar.java$
 */
package org.uranoplums.typical.util.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * RowCalendarクラス。<br>
 * 
 * @since 2015/03/27
 * @author syany
 */
public interface RowCalendar {

    public void add(int field, int amount);

    public boolean after(Object when);

    public boolean before(Object when);

    public void clear();

    public void clear(int field);

    public Object clone();

    public int compareTo(Calendar anotherCalendar);

    @Override
    public boolean equals(Object obj);

    public int get(int field);

    public int getActualMaximum(int field);

    public int getActualMinimum(int field);

    public Calendar getCalendar();

    public String getDisplayName(int field, int style, Locale locale);

    public Map<String, Integer> getDisplayNames(int field, int style, Locale locale);

    public int getFirstDayOfWeek();

    public int getGreatestMinimum(int field);

    public int getLeastMaximum(int field);

    public int getMaximum(int field);

    public int getMinimalDaysInFirstWeek();

    public int getMinimum(int field);

    public Date getTime();

    public long getTimeInMillis();

    public TimeZone getTimeZone();

    @Override
    public int hashCode();

    public boolean isLenient();

    public boolean isSet(int field);

    public void roll(int field, boolean up);

    public void roll(int field, int amount);

    public void set(int field, int value);

    public void set(int year, int month, int date);

    public void set(int year, int month, int date, int hourOfDay, int minute);

    public void set(int year, int month, int date, int hourOfDay, int minute, int second);

    public void setFirstDayOfWeek(int value);

    public void setLenient(boolean lenient);

    public void setMinimalDaysInFirstWeek(int value);

    public void setTime(Date date);

    public void setTimeInMillis(long millis);

    public void setTimeZone(TimeZone value);

    @Override
    public String toString();
}
