package com.bvlangen.kungfu.todolist.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public final class DateUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    private static final Locale LOCALE_DUCTH = Locale.forLanguageTag("nl");
    private static final String DATE_PATTERN = "dd MMM. yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN, LOCALE_DUCTH);
    private static final TemporalField WEEK_OF_WEEK_BASED_YEAR = WeekFields.of(LOCALE_DUCTH).weekOfWeekBasedYear();

    public static String formatFromToDate(final int year, final int weekNr) {
        if (!(year > 1950) || weekNr < 1 || weekNr > 53) {
            LOG.error("Unable to format from to dates of week. Invalid input, year: {}, weekNr: {}", year, weekNr);
            return null;
        }

        final LocalDate week = LocalDate.now()
                .withYear(year)
                .with(WEEK_OF_WEEK_BASED_YEAR, weekNr);

        final LocalDate start = week.with(DayOfWeek.MONDAY);
        final LocalDate end = start.plusDays(6);

        return start.format(FORMATTER) + " t/m " + end.format(FORMATTER);
    }

    public static int getWeekOfYear(final LocalDate localDate) {
        return localDate.get(WEEK_OF_WEEK_BASED_YEAR);
    }

    public static int getYear(final LocalDate localDate) {
        return localDate.getYear();
    }
}
