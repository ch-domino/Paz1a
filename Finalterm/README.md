# Final Term Project Summary

This document provides a summary of the scores obtained in the final term project for the PAZ1a subject.

## Total Scores

| Project Component  | Score Obtained | Total Possible Score |
| ------------------ | -------------- | -------------------- |
| Final Term Project | 34.00          | 70.00                |

# Assesment Report

```
N/A
```

## Summary

- **Total Score Obtained:** 34.00
- **Total Possible Score:** 70.00

## Project Description

The project consists of a skiing diary application that tracks various skiing runs. Below is a brief description of the main components of the project:

### Run.java

This class represents a skiing run with attributes such as:

- `day`: The day of the run.
- `length`: The length of the run.
- `lonelySkier`: Whether the skier was alone.
- `skiType`: The type of ski used.
- `numberOfRuns`: The number of runs.
- `slopeName`: The name of the slope (optional).

It includes methods to:

- Create a `Run` object from a string.
- Convert a `Run` object to a string.

### Diary.java

This class manages a collection of `Run` objects and provides methods to:

- Add a run to the diary.
- Save runs to a file.
- Load runs from a file.
- Calculate the total distance skied.
- Find the longest lonely run.
- List slopes skied on a specific day.
- Count the number of skiing days.
- Determine the favorite slope.
- Get the number of runs on each slope.
- Find the best day based on the total distance skied.
- Check if multiple types of skis were used on a specific day.

### Launcher.java

This class contains the `main` method and demonstrates the usage of the `Diary` and `Run` classes by:

- Creating and adding `Run` objects to the diary.
- Saving and loading runs from a file.
- Printing various statistics about the runs.
