package com.tiger.fare.domain;

import static com.tiger.fare.domain.util.Validate.notZeroNonNegative;

import com.tiger.fare.domain.exception.InvalidArgumentException;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

/** Contains the dateTime of journey without time. */
public class Date {

  private LocalDate date;

  private Date(int year, int month, int day) {
    notZeroNonNegative(year, "Invalid year");
    notZeroNonNegative(month, "Invalid month");
    notZeroNonNegative(day, "Invalid day");
    try {
      this.date = LocalDate.of(year, month, day);
    } catch (DateTimeException dateTimeException) {
      throw new InvalidArgumentException("Date of travel is invalid.");
    }
  }

  public LocalDate localDate() {
    return date;
  }

  public static class Builder {

    int day;
    int month;
    int year;

    public Builder withDay(int day) {
      this.day = day;
      return this;
    }

    public Builder withMonth(int month) {
      this.month = month;
      return this;
    }

    public Builder withYear(int year) {
      this.year = year;
      return this;
    }

    public Date build() {
      return new Date(this.year, this.month, this.day);
    }
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Date{");
    sb.append("dateTime=").append(date);
    sb.append('}');
    return sb.toString();
  }

  public boolean isWeekDay() {
    return !(localDate().getDayOfWeek().equals(DayOfWeek.SATURDAY)
        && localDate().getDayOfWeek().equals(DayOfWeek.SUNDAY));
  }
}
