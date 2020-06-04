## [Problem](https://www.hackerrank.com/challenges/time-conversion/problem)
Given a time in 12-hour AM/PM format, convert it to military (24-hour) time.

Note: Midnight is 12:00:00AM on a 12-hour clock, and 00:00:00 on a 24-hour clock. Noon is 12:00:00PM on a 12-hour clock, and 12:00:00 on a 24-hour clock.

## Function Description

Complete the timeConversion function in the editor below. It should return a new string representing the input time in 24 hour format.

timeConversion has the following parameter(s):

s: a string representing time in 12 hour format

## Sample Input 0
```
07:05:45PM
```
## Sample Output 0
```
19:05:45
```
## Solution (Ruby)
```
def timeConversion(s)
    is_afternoon = s.include?("PM")
    if is_afternoon && s[0,2] != '12'
        conv = s[0,2].to_i + 12
        clock = conv.to_s + s[2,6]
    elsif !is_afternoon && s[0,2] == '12'
        clock = '00' + s[2,6]
    else
        clock = s[0,8]
    end
    clock
end
```

## Review