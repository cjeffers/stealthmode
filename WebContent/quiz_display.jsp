<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.*, java.util.List, java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Quiz quiz = (Quiz) request.getAttribute("quiz");
int numQuestions = (Integer) request.getAttribute("num_questions");
User creator = (User) request.getAttribute("creator");
String creatorURL = "/stealthmode/user?id=" + creator.getID();
User me = (User) request.getSession().getAttribute("user");
boolean loggedIn = (me == null ? false : true);

List<Result> myResults = (List<Result>) request.getAttribute("my_results");
List<Result> topResults = (List<Result>) request.getAttribute("top_results");
List<Result> recentResults = (List<Result>) request.getAttribute("recent_results");
SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");

double averageScore = (Double) request.getAttribute("average_score");
long averageTime = ((Long) request.getAttribute("average_time"));
long totalSeconds = averageTime / 1000;
long minutes = totalSeconds / 60;
long seconds = totalSeconds % 60;
long millis = averageTime % 1000;

%>
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= quiz.getName() %></title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <% List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes"); %>
            <h1><%= quiz.getName() %></h1>

            <%--creator name and link--%>
            <h3>created by <a href="<%= creatorURL %>"><%= creator.getFullname() %></a></h3>

            <p><%= quiz.getDescription() %></p>

            <%--summary stats, average score, average time--%>
            <p>Number of questions: <%= numQuestions %></p>
            <p>Average score: <%= String.format("%.2f", averageScore) %></p>
            <p>Average percentage: <%= String.format("%.1f", (averageScore / numQuestions) * 100.0)%>%</p>
            <p>Average time taken: <%= minutes + "m, " + seconds + "." + millis + "s" %></p>

            <% request.setAttribute("id", quiz.getID()); %>

            <%--logged in user's scores on quiz--%>
            <% if (loggedIn) { %>
                <h4>Your scores</h4>
                <table id="your_scores">
                    <tr class="header">
                        <td class="date">Date</td>
                        <td class="num_correct"># correct</td>
                        <td class="percentage">Percentage</td>
                        <td class="time">Time</td>
                    </tr>
                    <% for (Result res : myResults) {
                         totalSeconds = res.getDuration() / 1000;
                         minutes = totalSeconds / 60;
                         seconds = totalSeconds % 60;
                         millis = res.getDuration() % 1000;
                    %>
                        <tr class="data">
                            <td class="date"><%= sdf.format(res.getTakenAt()) %></td>
                            <td class="num_correct"><%= res.getScore() %></td>
                            <td class="percentage"><%=String.format("%.1f", (((double) res.getScore()) / numQuestions) * 100.0) %></td>
                            <td class="time"><%= minutes + "m, " + seconds + "." + millis + "s" %></td>
                        </tr>
                    <% } %>
                </table>
            <% } else { %>
                <h4><a href="/stealthmode/user/login">Log in</a> to see your scores on this quiz</h4>
            <% } %>

            <%--top scores, link to all--%>
            <h4>Top scores</h4>
            <table id="top_scores">
                <tr class="header">
                    <td class="username">User</td>
                    <td class="date">Date</td>
                    <td class="num_correct"># correct</td>
                    <td class="percentage">Percentage</td>
                    <td class="time">Time</td>
                </tr>
                <% for (Result res : topResults) {
                     totalSeconds = res.getDuration() / 1000;
                     minutes = totalSeconds / 60;
                     seconds = totalSeconds % 60;
                     millis = res.getDuration() % 1000;
                %>
                    <tr class="data">
                        <td class="username"><%= User.findByID(res.getUserID()).getUserName() %></td>
                        <td class="date"><%= sdf.format(res.getTakenAt()) %></td>
                        <td class="num_correct"><%= res.getScore() %></td>
                        <td class="percentage"><%=String.format("%.1f", (((double) res.getScore()) / numQuestions) * 100.0) %></td>
                        <td class="time"><%= minutes + "m, " + seconds + "." + millis + "s" %></td>
                    </tr>
                <% } %>
            </table>

            <%--recent scores, link to all--%>
            <h4>Recent scores</h4>
            <table id="recent_scores">
                <tr class="header">
                    <td class="username">User</td>
                    <td class="date">Date</td>
                    <td class="num_correct"># correct</td>
                    <td class="percentage">Percentage</td>
                    <td class="time">Time</td>
                </tr>
                <% for (Result res : recentResults) {
                     totalSeconds = res.getDuration() / 1000;
                     minutes = totalSeconds / 60;
                     seconds = totalSeconds % 60;
                     millis = res.getDuration() % 1000;
                %>
                    <tr class="data">
                        <td class="username"><%= User.findByID(res.getUserID()).getUserName() %></td>
                        <td class="date"><%= sdf.format(res.getTakenAt()) %></td>
                        <td class="num_correct"><%= res.getScore() %></td>
                        <td class="percentage"><%=String.format("%.1f", (((double) res.getScore()) / numQuestions) * 100.0) %></td>
                        <td class="time"><%= minutes + "m, " + seconds + "." + millis + "s" %></td>
                    </tr>
                <% } %>
            </table>

            <form action="/stealthmode/quiz/take" method="get">
                <input type="submit" value="Take Quiz" />
            </form>
            <a href="/stealthmode/quizzes">Browse quizzes</a>
        </div>
    </body>
</html>
