[문제링크](https://www.acmicpc.net/problem/2959)

## Problem

Kornislav the turtle never has anything interesting to do. Since he's going to
live for three hundred years, he keeps trying to find way to kill time.
This weekend he started playing "enclose the largest rectangle".

To start with, Kornislav needs four positive integers. He tries to
enclose a rectangle by moving in one direction, then turning 90 degrees, then walking in the new direction etc. Kornislav makes a total of three 90-degree turns and walks four segments.

When walking in some direction, the number of steps he takes must be
equal to one of the four chosen integers and each integer must be used
exactly once. Depending on the order in which Kornislav uses the
integers, his walk will make various shapes, some of which don't contain enclosed rectangles.

Write a program that calculates the largest rectangle the turtle can enclose with its walk.

## Input

The first line contains four positive integers A, B, C and D (0 < A, B, C, D < 100), the four chosen integers.

## Output

Output the largest area.

## Example Input 1

1 2 3 4

## Example Output 1

3

## Hint

In the first example, one possible way for Kornislav to enclose a rectangle of area 3:

- Make 4 steps forward;
- Turn right;
- Make 1 step forward;
- Turn right;
- Make 3 steps forward;
- Turn right;
- Make 2 steps forward.

## Solution (Java)
```
using System;

class MainClass {
  public static void Main (string[] args) {
    String[] s = Console.ReadLine().Split(' ');
    int[] arr = Array.ConvertAll(s, int.Parse);
    Array.Sort(arr);
    Console.WriteLine(arr[0] * arr[arr.Length-2]);
  }
}
```