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
 * $Id: UraDateFormatSymbols.java$
 */
package org.uranoplums.typical.util.time;

import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ref.SoftReference;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.uranoplums.typical.log.UraLoggerFactory;
import org.uranoplums.typical.resource.UraJSONResource;

import sun.util.TimeZoneNameUtility;

/**
 * uranoplumsによるDateFormatSymbols拡張クラス。<br>
 *
 * @since 2015/04/07
 * @author syany
 */
public class UraDateFormatSymbols extends DateFormatSymbols {

    /**  */
    protected final Logger logger = UraLoggerFactory.getUraLog(this.getClass());
    /**  */
    private static final long serialVersionUID = -7525197365548875634L;
    /**  */
    //private static final String FORMAT_DATA = "org.uranoplums.typical.util.time.date-format-symbols";
    private static final String FORMAT_DATA = "org.uranoplums.typical.util.time.resource.date-format-symbols";
    // private static final String FORMAT_DATA = "date-format-symbols";
    /**  */
    private static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    /**  */
    protected Locale tmpLocale = null;
    /**  */
    protected String[] localeEra = null;
    /**  */
    protected long[] localeYear = null;
    /**
     * Cache to hold DateFormatSymbols instances per Locale.
     */
    private static final ConcurrentMap<Locale, SoftReference<UraDateFormatSymbols>> cachedInstances = newConcurrentHashMap(3);

    /**
     * デフォルトコンストラクタ。<br>
     */
    public UraDateFormatSymbols() {
        super(Locale.getDefault());
        initialize(Locale.getDefault());
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param locale
     */
    public UraDateFormatSymbols(Locale locale) {
        super(locale);
        initialize(locale);
    }

    /**
     * Returns a cached DateFormatSymbols if it's found in the
     * cache. Otherwise, this method returns a newly cached instance
     * for the given locale.
     */
    protected static UraDateFormatSymbols getCachedInstance(Locale locale) {
        SoftReference<UraDateFormatSymbols> ref = cachedInstances.get(locale);
        UraDateFormatSymbols dfs = null;
        if (ref == null || (dfs = ref.get()) == null) {
            dfs = new UraDateFormatSymbols(locale);
            ref = new SoftReference<UraDateFormatSymbols>(dfs);
            SoftReference<UraDateFormatSymbols> x = cachedInstances.putIfAbsent(locale, ref);
            if (x != null) {
                UraDateFormatSymbols y = x.get();
                if (y != null) {
                    dfs = y;
                } else {
                    // Replace the empty SoftReference with ref.
                    cachedInstances.put(locale, ref);
                }
            }
        }
        return dfs;
    }

    /**
     * Gets the <code>DateFormatSymbols</code> instance for the default
     * locale. This method provides access to <code>DateFormatSymbols</code> instances for locales supported by the Java
     * runtime itself as well
     * as for those supported by installed {@link java.text.spi.DateFormatSymbolsProvider DateFormatSymbolsProvider}
     * implementations.
     * @return a <code>DateFormatSymbols</code> instance.
     * @since 1.6
     */
    public static final UraDateFormatSymbols getUraInstance() {
        return getUraInstance(Locale.getDefault());
    }

    /**
     * Gets the <code>DateFormatSymbols</code> instance for the specified
     * locale. This method provides access to <code>DateFormatSymbols</code> instances for locales supported by the Java
     * runtime itself as well
     * as for those supported by installed {@link java.text.spi.DateFormatSymbolsProvider DateFormatSymbolsProvider}
     * implementations.
     * @param locale the given locale.
     * @return a <code>DateFormatSymbols</code> instance.
     * @exception NullPointerException if <code>locale</code> is null
     * @since 1.6
     */
    public static final UraDateFormatSymbols getUraInstance(Locale locale) {
        return getCachedInstance(locale).clone();
    }

    /**
     * Write out the default serializable data, after ensuring the <code>zoneStrings</code> field is initialized in
     * order to make
     * sure the backward compatibility.
     *
     * @since 1.6
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        logger.trace("> writeObject({})", stream);
        if (getZoneStrings() == null) {
            setZoneStrings(TimeZoneNameUtility.getZoneStrings(tmpLocale));
        }
        stream.defaultWriteObject();
        logger.trace("< return");
    }

    protected void _CopyMembers(UraDateFormatSymbols src, UraDateFormatSymbols dst) {
        logger.trace("> _CopyMembers({}, {})", src, dst);
        dst.setEras(Arrays.copyOf(src.getEras(), src.getEras().length));
        dst.setMonths(Arrays.copyOf(src.getMonths(), src.getMonths().length));
        dst.setShortMonths(Arrays.copyOf(src.getShortMonths(), src.getShortMonths().length));
        dst.setWeekdays(Arrays.copyOf(src.getWeekdays(), src.getWeekdays().length));
        dst.setShortWeekdays(Arrays.copyOf(src.getShortWeekdays(), src.getShortWeekdays().length));
        dst.setAmPmStrings(Arrays.copyOf(src.getAmPmStrings(), src.getAmPmStrings().length));
        if (src.getZoneStrings() != null) {
            dst.setZoneStrings(src.getZoneStrings());
        } else {
            dst.setZoneStrings(null);
        }
        dst.setLocalPatternChars(src.getLocalPatternChars());
        logger.trace("< return");
    }

    protected void fullCopyMembers(UraDateFormatSymbols src, UraDateFormatSymbols dst) {
        logger.trace("> fullCopyMembers({}, {})", src, dst);
        _CopyMembers(src, dst);
        subCopyMembers(src, dst);
        logger.trace("< return");
    }

    protected String getDateFormatResource() {
        logger.trace("> getDateFormatResource()");
        logger.trace("< return [{}]", FORMAT_DATA);
        return FORMAT_DATA;
    }

    protected void initialize(Locale desiredLocale) {
        logger.trace("> initialize({})", desiredLocale);
        tmpLocale = desiredLocale;
        // Copy values of a cached instance if any.
        SoftReference<UraDateFormatSymbols> ref = cachedInstances.get(tmpLocale);
        UraDateFormatSymbols dfs;
        if (ref != null && (dfs = ref.get()) != null) {
            fullCopyMembers(dfs, this);
            logger.trace("< return");
            return;
        }
        // Initialize the fields from the ResourceBundle for locale.
        UraJSONResource resource = new UraJSONResource(getDateFormatResource());
        this.setEras(resource.getResourceStringArray(tmpLocale, "Eras"));
        this.setMonths(resource.getResourceStringArray(tmpLocale, "MonthNames"));
        this.setShortMonths(resource.getResourceStringArray(tmpLocale, "MonthAbbreviations"));
        this.setAmPmStrings(resource.getResourceStringArray(tmpLocale, "AmPmMarkers"));
        this.setLocalPatternChars(resource.getResourceString(tmpLocale, "DateTimePatternChars"));
        this.setWeekdays(resource.getResourceStringArray(tmpLocale, "DayNames"));
        this.setShortWeekdays(resource.getResourceStringArray(tmpLocale, "DayAbbreviations"));
        this.setLocaleEra(resource.getResourceStringArray(tmpLocale, "LocaleEra"));
        List<Object> localeEraList = resource.getResourceList(tmpLocale, "LocaleYear");
        long[] tmpLocaleYear = new long[localeEraList.size()];
        for (int i = 0; i < localeEraList.size(); i++) {
            try {
                // まずLong型としてパース
                long millis = ((Double) localeEraList.get(i)).longValue();
                tmpLocaleYear[i] = millis;
            } catch (Exception e1) {
                try {
                    Date localeYearDate = FAST_DATE_FORMAT.parse(localeEraList.get(i).toString());
                    tmpLocaleYear[i] = localeYearDate.getTime();
                } catch (ParseException e2) {
                    e2.printStackTrace();
                    tmpLocaleYear[i] = 0;
                    logger.trace("< return");
                }
            }
        }
        this.setLocaleYear(tmpLocaleYear);
        logger.trace("< return");
    }

    /**
     * 。<br>
     * @param src
     * @param dst
     */
    protected void subCopyMembers(UraDateFormatSymbols src, UraDateFormatSymbols dst) {
        logger.trace("> subCopyMembers({}, {})", src, dst);
        dst.localeEra = Arrays.copyOf(src.localeEra, src.localeEra.length);
        dst.localeYear = Arrays.copyOf(src.localeYear, src.localeYear.length);
        logger.trace("< return");
    }

    /*
     * (非 Javadoc)
     *
     * @see java.text.DateFormatSymbols#clone()
     */
    @Override
    public UraDateFormatSymbols clone() {
        logger.trace("> clone()");
        UraDateFormatSymbols other = (UraDateFormatSymbols) super.clone();
        subCopyMembers(this, other);
        logger.trace("< return [{}]", other);
        return other;
    }

    /**
     * @return localeEra を返却します。
     */
    public String[] getLocaleEra() {
        logger.trace("> getLocaleEra()");
        logger.trace("< return [{}]", (Object[]) localeEra);
        return localeEra;
    }

    /**
     * @return localeYear を返却します。
     */
    public long[] getLocaleYear() {
        logger.trace("> getLocaleYear()");
        logger.trace("< return [{}]", localeYear);
        return localeYear;
    }

    /**
     * @param localeEra localeEraを設定します。
     */
    public void setLocaleEra(String[] localeEra) {
        logger.trace("> setLocaleEra({})", (Object[]) localeEra);
        this.localeEra = localeEra;
        logger.trace("< return");
    }

    /**
     * @param localeYear localeYearを設定します。
     */
    public void setLocaleYear(long[] localeYear) {
        logger.trace("> setLocaleYear({})", localeYear);
        this.localeYear = localeYear;
        logger.trace("< return");
    }
}
