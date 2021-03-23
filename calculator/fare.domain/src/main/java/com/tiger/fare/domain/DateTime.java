package com.tiger.fare.domain;

import static com.tiger.fare.domain.util.Validate.notNull;

import com.tiger.fare.domain.exception.InvalidArgumentException;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Objects;

/** Contains the dateTime of the journey. */
public final class DateTime {

  private LocalDateTime dateTime;
  private Date date;

  private DateTime(Date date, int hour, int minute) {
    notNull(date, "Date is invalid");
    try {
      this.date = date;
      this.dateTime = LocalDateTime.of(date.localDate(), LocalTime.of(hour, minute));
    } catch (DateTimeException dateTimeException) {
      throw new InvalidArgumentException("Date/Time of travel are invalid.");
    }
  }

  public LocalDateTime dateTime() {
    return dateTime;
  }

  public Date date() {
    return this.date;
  }

  public int weekNumber() {
    long epoch = dateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    double floor = Math.floor(epoch / (1000 * 60 * 60 * 24 * 7));
    return (int) floor;
  }

  public LocalTime time() {
    return dateTime.toLocalTime();
  }

  public static class Builder {

    private Date date;
    private int hour;
    private int minute;

    public Builder withDate(Date date) {
      this.date = date;
      return this;
    }

    public Builder withHour(int hour) {
      this.hour = hour;
      return this;
    }

    public Builder withMinute(int minute) {
      this.minute = minute;
      return this;
    }

    public DateTime build() {
      return new DateTime(this.date, this.hour, this.minute);
    }
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("DateTime{");
    sb.append("dateTime=").append(dateTime);
    sb.append('}');
    return sb.toString();
  }
}
