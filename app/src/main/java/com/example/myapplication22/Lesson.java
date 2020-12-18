package com.example.myapplication22;

public class Lesson {
    public String name;
    public String no;
    public String teacher;
    public String classroom;
    public Time time=new Time();
    public String credit;
}

class Time{
    public int[] during=new int[2]; //起始周，终止周
    public int isOddWeek; //1是单周，0是双周，3是单双周
    public int[]begin=new int[7]; //下标是星期，0是周日，1是周一；值是课程开始时间，如34课程开始时间为3
    public int last;   //课程连续节数，如34为2
    public int count; //课程的时间数，比如周一、周二都有课，就是2
}
