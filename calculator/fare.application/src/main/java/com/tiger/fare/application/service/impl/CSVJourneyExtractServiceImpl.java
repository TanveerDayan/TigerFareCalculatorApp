package com.tiger.fare.application.service.impl;

import com.tiger.fare.application.service.JourneyExtractorService;
import com.tiger.fare.domain.Date;
import com.tiger.fare.domain.DateTime;
import com.tiger.fare.domain.Journey;
import com.tiger.fare.domain.Journey.Builder;
import com.tiger.fare.domain.ZoneTravelDetail;
import com.tiger.fare.domain.exception.InvalidArgumentException;
import com.tiger.fare.domain.exception.ServiceIOException;
import com.tiger.fare.domain.util.Validate;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static com.tiger.fare.domain.service.util.Validate.notNull;

/** This service extracts the data from CSV file and converts it into Journey objects. */
public class CSVJourneyExtractServiceImpl implements JourneyExtractorService {

  private String filePath;
  private String errorFilePath;
  private BufferedWriter errorWriter;

  static Logger logger = Logger.getLogger(CSVJourneyExtractServiceImpl.class.getName());

  public CSVJourneyExtractServiceImpl(String filePath, String errorFilePath) {
    notNull(filePath, "The csv filePath cannot be null.");
    notNull(errorFilePath, "The error filePath cannot be null.");
    logger.log(
        Level.INFO,
        String.format(
            "Starting CSV Journey Extract Service with filePath: %s and errorFilePath: %s",
            filePath, errorFilePath));
    this.filePath = filePath;
    this.errorFilePath = errorFilePath;
    this.errorWriter = errorWriter();
  }

  private BufferedWriter errorWriter() {
    try {
      return new BufferedWriter(new FileWriter(errorFilePath));
    } catch (IOException e) {
      throw new ServiceIOException(e.getMessage());
    }
  }

  private void closeErrorWriter() {
    logger.log(Level.INFO, "Closing error writer.");
    try {
      errorWriter.close();
    } catch (IOException e) {
      throw new ServiceIOException(e.getMessage());
    }
  }

  @Override
  public List<Journey> extract() {
    Path path = Paths.get(filePath);
    List<Journey> journeys = null;
    try {
      journeys =
          Files.readAllLines(path)
              .stream()
              .map(line -> lineToJourney(line))
              .filter(journey -> journey.isPresent())
              .map(journey -> journey.get())
              .collect(Collectors.toList());
    } catch (IOException e) {
      throw new ServiceIOException(e.getMessage());
    }
    logger.log(
        Level.INFO,
        String.format("Number of healthy lines read from CSV are : %s", journeys.size()));
    closeErrorWriter();
    return journeys;
  }

  private Optional<Journey> lineToJourney(String line) {
    logger.log(Level.INFO, String.format("Transforming line: %s to Journey", line));
    Optional<String> optionalLine =
        Optional.ofNullable(line)
            .flatMap(s -> s.isEmpty() ? Optional.empty() : Optional.ofNullable(s));
    if (!optionalLine.isPresent()) return Optional.empty();

    String[] columns = line.split(",");
    Optional<Journey> journey = null;
    try {
      journey =
          Optional.of(
              new Builder()
                  .withDateTime(
                      new DateTime.Builder()
                          .withDate(
                              new Date.Builder()
                                  .withDay(getInteger(columns, 2))
                                  .withMonth(getInteger(columns, 1))
                                  .withYear(getInteger(columns, 0))
                                  .build())
                          .withHour(getInteger(columns, 3))
                          .withMinute(getInteger(columns, 4))
                          .build())
                  .withZoneTravelDetail(
                      new ZoneTravelDetail.Builder()
                          .withFromZone(getInteger(columns, 5))
                          .withToZone(getInteger(columns, 6))
                          .build())
                  .build());
    } catch (InvalidArgumentException e) {
      writeErrorRecord(line, e);
      return Optional.empty();
    }

    return journey;
  }

  private int getInteger(String[] columns, int columnNumber) {
    Validate.notNull(
        columns[columnNumber],
        String.format("The column number %s is missing in input Line.", columnNumber));
    Validate.isNumber(
        columns[columnNumber],
        String.format("The column number %s should be in number format.", columnNumber));
    return Integer.parseInt(columns[columnNumber]);
  }

  private String getString(String[] columns, int columnNumber) {
    Validate.notNull(
        columns[columnNumber],
        String.format("The column number %s is missing in input Line.", columnNumber));
    return columns[columnNumber];
  }

  private void writeErrorRecord(String errorRecord, InvalidArgumentException exception) {
    logger.log(
        Level.INFO,
        String.format(
            "Logging error record %s with exception code %s", errorRecord, exception.getCode()));
    try {
      errorWriter.write(
          String.format(
              new StringBuilder()
                  .append(errorRecord)
                  .append(",")
                  .append(exception.getCode())
                  .append(",")
                  .append(exception.getMessage())
                  .append("\n")
                  .toString()));
    } catch (IOException e) {
      throw new ServiceIOException(e.getMessage());
    }
  }
}
