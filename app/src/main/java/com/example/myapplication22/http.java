package com.example.myapplication22;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class http {
    static OkHttpClient client = new OkHttpClient();

    public static String login(String txtUserID, String txtUserPwd) {
        String success = "error";
        try {
            Request request1 = new Request.Builder()
                    .url("http://credit.stu.edu.cn/portal/stulogin.aspx")
                    .get()
                    .addHeader("Origin", "http://credit.stu.edu.cn")
                    .addHeader("Host", "credit.stu.edu.cn")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Referer", "http://credit.stu.edu.cn/web/")
                    .build();

            Response response1 = client.newCall(request1).execute();
            Document doc = Jsoup.parse(response1.body().string());
            String viewState = doc.getElementById("__VIEWSTATE").attr("value");
            String viewStateGenerator = doc.getElementById("__VIEWSTATEGENERATOR").attr("value");
            String eventValidation = doc.getElementById("__EVENTVALIDATION").attr("value");
            String btn = doc.getElementById("btnLogon").attr("value");

            List<String> cookies = response1.headers().values("Set-Cookie");
            String cookie1 = cookies.get(0).split(";")[0];
            System.out.println(cookies);

            String path = "http://credit.stu.edu.cn/portal/stulogin.aspx";
            FormBody formBody = new FormBody.Builder()
                    .add("__EVENTTARGET", "")
                    .add("__EVENTARGUMENT", "")
                    .add("__VIEWSTATE", viewState)
                    .add("__VIEWSTATEGENERATOR", viewStateGenerator)
                    .add("__EVENTVALIDATION", eventValidation)
                    .add("txtUserID", txtUserID)
                    .add("txtUserPwd", txtUserPwd)
                    .add("btnLogon", btn)
                    .build();
            Request request2 = new Request.Builder()
                    .url(path)
                    .post(formBody)
                    .addHeader("Cookie", cookie1)
                    .addHeader("Origin", "http://credit.stu.edu.cn")
                    .addHeader("Host", "credit.stu.edu.cn")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Referer", "http://credit.stu.edu.cn/portal/stulogin.aspx")
                    .build();
            Response response2 = client.newCall(request2).execute();
            List<String> cookies2 = response2.headers().values("Set-Cookie");
            System.out.println(cookies2);
            if (cookies2.size() > 0) {
                success = "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }
        return success;
    }

    public static ArrayList<Less> getTimeTable(String txtUserID, String txtUserPwd, String xn, String xq) {
        ArrayList<Less> lessons = new ArrayList<>();
        try {
            //1
            Request request1 = new Request.Builder()
                    .url("http://credit.stu.edu.cn/portal/stulogin.aspx")
                    .get()
                    .addHeader("Origin", "http://credit.stu.edu.cn")
                    .addHeader("Host", "credit.stu.edu.cn")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Referer", "http://credit.stu.edu.cn/web/")
                    .build();

            //2
            Response response1 = client.newCall(request1).execute();
            Document doc = Jsoup.parse(response1.body().string());
            String viewState = doc.getElementById("__VIEWSTATE").attr("value");
            String viewStateGenerator = doc.getElementById("__VIEWSTATEGENERATOR").attr("value");
            String eventValidation = doc.getElementById("__EVENTVALIDATION").attr("value");
            String btn = doc.getElementById("btnLogon").attr("value");

            List<String> cookies1 = response1.headers().values("Set-Cookie");
            String cookie1 = cookies1.get(0).split(";")[0];
            //System.out.println(cookies1);

            String path = "http://credit.stu.edu.cn/portal/stulogin.aspx";
            FormBody formBody1 = new FormBody.Builder()
                    .add("__EVENTTARGET", "")
                    .add("__EVENTARGUMENT", "")
                    .add("__VIEWSTATE", viewState)
                    .add("__VIEWSTATEGENERATOR", viewStateGenerator)
                    .add("__EVENTVALIDATION", eventValidation)
                    .add("txtUserID", txtUserID)
                    .add("txtUserPwd", txtUserPwd)
                    .add("btnLogon", btn)
                    .build();
            Request request2 = new Request.Builder()
                    .url(path)
                    .post(formBody1)
                    .addHeader("Cookie", cookie1)
                    .addHeader("Origin", "http://credit.stu.edu.cn")
                    .addHeader("Host", "credit.stu.edu.cn")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Referer", "http://credit.stu.edu.cn/portal/stulogin.aspx")
                    .build();


            //3
            Response response2 = client.newCall(request2).execute();
            List<String> cookies2 = response2.headers().values("Set-Cookie");
            String cookie2 = cookies2.get(0).split(";")[0];
            //System.out.println(cookie2);

            //System.out.println(response.body().string());
            Request request3 = new Request.Builder()
                    .url("http://credit.stu.edu.cn/Elective/MyCurriculumSchedule.aspx")
                    .get()
                    .addHeader("Origin", "http://credit.stu.edu.cn")
                    .addHeader("Host", "credit.stu.edu.cn")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Referer", "http://credit.stu.edu.cn/portal/stumenu.aspx")
                    .addHeader("Cookie", cookie1 + "; " + cookie2)
                    .build();


            //4
            Response response3 = client.newCall(request3).execute();
            System.out.println(response3.toString());
            String[] ss = response3.toString().split("&Locks=");
            String location = ss[1].substring(0, ss[1].length() - 1);
            String objID = ss[0].split("ObjID=")[1];

            System.out.println(objID);
            //System.out.println(response.body().string());

            Request request4 = new Request.Builder()
                    .url("http://credit.stu.edu.cn/Student/StudentTimeTable.aspx?ObjID=" + objID + "&Locks=" + location)
                    .get()
                    .addHeader("Origin", "http://credit.stu.edu.cn")
                    .addHeader("Host", "credit.stu.edu.cn")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Referer", "http://credit.stu.edu.cn/portal/stumenu.aspx")
                    .addHeader("Cookie", cookie1 + "; " + cookie2)
                    .build();


            //5
            Response response4 = client.newCall(request4).execute();
            String html = response4.body().string();
            Document document = Jsoup.parse(html);
            //System.out.println(document);
            String VIEWSTATEGENERATOR = document.getElementById("__VIEWSTATEGENERATOR").attr("value");
            String EVENTVALIDATION = document.getElementById("__EVENTVALIDATION").attr("value");
            String VIEWSTATE = document.getElementById("__VIEWSTATE").attr("value");


            FormBody formBody2 = new FormBody.Builder()
                    .add("__EVENTTARGET", "")
                    .add("__EVENTARGUMENT", "")
                    .add("__VIEWSTATE", VIEWSTATE)
                    .add("__VIEWSTATEGENERATOR", VIEWSTATEGENERATOR)
                    .add("__EVENTVALIDATION", EVENTVALIDATION)
                    .add("ucsYS$XN$Text", xn)
                    .add("ucsYS$XQ", xq)
                    .add("ucsYS$hfXN", xn)
                    .add("btnSearch.x", "14")
                    .add("btnSearch.y", "12")
                    .build();

            Request request5 = new Request.Builder()
                    .url("http://credit.stu.edu.cn/Student/StudentTimeTable.aspx?ObjID=" + objID + "&amp;Locks=" + location)
                    .post(formBody2)
                    .addHeader("Origin", "http://credit.stu.edu.cn")
                    .addHeader("Host", "credit.stu.edu.cn")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Referer", "http://credit.stu.edu.cn/Student/StudentTimeTable.aspx?ObjID=" + objID + "&Locks=" + location)
                    .addHeader("Cookie", cookie1 + "; " + cookie2)
                    .build();


            //6
            Response response5 = client.newCall(request5).execute();
            //System.out.println(response.toString());
            String body = response5.body().string();
            //System.out.println(body);

            Document document1 = Jsoup.parse(body);
            Elements[] items = new Elements[2];
            items[0] = document1.select(".DG").get(0).select(".DGItemStyle");
            items[1] = document1.select(".DG").get(0).select(".DGAlternatingItemStyle");
            for (int h = 0; h < 2; h++) {
                for (int i = 0; i < items[h].size(); i++) {
                    Elements tds = items[h].get(i).getElementsByTag("td");
                    Less lesson = new Less();
                    lesson.no = tds.get(0).text();
                    lesson.name = tds.get(1).text().split("]", 2)[1];
                    lesson.credit = tds.get(2).text();
                    lesson.teacher = tds.get(3).text();
                    lesson.classroom = tds.get(4).text();
                    if (tds.get(5).text().contains(" ")) {
                        lesson.during[0] = Integer.parseInt(tds.get(5).text().split(" -")[0]);
                        lesson.during[1] = Integer.parseInt(tds.get(5).text().split(" -")[1]);
                    } else {
                        lesson.during[0] = Integer.parseInt(tds.get(5).text().split("-")[0]);
                        lesson.during[1] = Integer.parseInt(tds.get(5).text().split("-")[1]);
                    }
                    for (int j = 6; j < tds.size(); j++) {
                        if (!tds.get(j).text().equals(""))
                            lesson.time[j - 6] = tds.get(j).text();
                        else lesson.time[j - 6] = "null";
                    }
                    lessons.add(lesson);

                }
            }
            for (int j = 0; j < lessons.size(); j++) {
                Less lesson = lessons.get(j);
                System.out.println("课程名：" + lesson.name);
                System.out.println("课程号：" + lesson.no);
                System.out.println("教师：" + lesson.teacher);
                System.out.println("教室：" + lesson.classroom);
                System.out.println("起始周：" + lesson.during[0] + "-->" + lesson.during[1]);
                System.out.println("上课时间：周日 |周一 |周二 |周三 |周四 |周五 |周六");
                String s = "";
                for (int k = 0; k < lesson.time.length; k++) {
                    s += lesson.time[k] + "|";
                }
                System.out.println("上课时间：" + s);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessons;
    }

}
