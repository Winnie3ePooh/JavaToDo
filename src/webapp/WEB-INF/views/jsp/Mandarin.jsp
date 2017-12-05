<%@ page import="java.util.List" %>
<%@ page import="database.Task" %>
<%@ page import="database.TaskList" %>
<%@ page import="database.ObjectsDAO" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="ru-ru">
<head>
    <meta charset="utf-8">
    <title>Mandarin</title>
    <link rel="stylesheet" href="/resources/core/css/style.css">
    <link href='https://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,300,700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Mono:400,400italic,500,700' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="logo">
            <img alt="logo" src="/resources/core/img/mandarinni.png">
        </div>
        <div class="search">
            <i class="icon fa fa-search"></i>
            <input type="text" name="search" id="search" placeholder="Search">
        </div>
        <div class="user-block">
            <c:choose>
                <c:when test="${user.getUsername() ne null}">
                    <div class="username">${user.getUsername()}</div>
                    <a href="/log_out">
                        <i class="icon fa fa-sign-out"></i>
                    </a>
                </c:when>
                <c:otherwise>
                    <div class="username">Username</div>

                    <a class="sign-in-show">
                        <i class="icon fa fa-sign-in"></i>
                    </a>
                    <div class="sign-in-popup">
                        <form:form action="/log_in" modelAttribute="user" method="get">
                            <div class="table">
                                <div>
                                    <label>Name:</label>
                                    <form:input type="text" path="username"/>
                                </div>
                                <div>
                                    <label>Password:</label>
                                    <form:input type="password" path="password"/>
                                </div>
                                <div>
                                    <label>Repeat:</label>
                                    <input type="password">
                                </div>
                            </div>
                            <button type="submit">Войти</button>
                        </form:form>
                    </div>
                </c:otherwise>
            </c:choose>
            <!--<a class="logout" href="#"></a>-->
        </div>
    </div>

    <div class="main-content clearfix">
        <div class="menu">
            <ul class="menu-list">
                <li>
                    <a class="create" href="#"></a>

                    <p class="block-to-show">Create</p>
                </li>
                <li>
                    <a class="list active" href="#"></a>

                    <p class="block-to-show">All tasks</p>
                </li>
                <li>
                    <a class="done" href="#"></a>

                    <p class="block-to-show">Done</p>
                </li>
                <li>
                    <a class="invite" href="#"></a>

                    <p class="block-to-show">Add friends</p>
                </li>
            </ul>
        </div>

        <div class="list-window">
            <div class="top-container clearfix">
                <p class="top-container-title">All tasks</p>

                <div class="sort-block">
                    <p>Sort by</p>
                    <select>
                        <option value="dt">
                            date
                        </option>
                        <option value="imp">
                            importance
                        </option>
                    </select>
                </div>
            </div>
            <div class="task-container">
                <!--Обход всех списков-->
                <c:forEach items="${lists}" var="list">
                    <%
                        List<Task> tasks = ObjectsDAO.getListTasks((TaskList) pageContext.getAttribute("list"));
                        pageContext.setAttribute("tasks", tasks);
                    %>
                <div class="task-item">
                    <div class="task-title">${list.getTitle()}</div>
                    <div class="task-date">
                        11.11.2015
                    </div>
                    <!--Максимально 63 символа в таске для отображения -->
                    <div class="tasks"></div>

                    <div class="tasks-hidden">
                        <!--Активные задания -->
                        <div class="tasks">
                            <c:forEach items="${tasks}" var="task">
                                <c:if test="${!task.getIsDone()}">
                                    <div class="task" data-task-id="${task.getTaskId()}">
                                        <i class="icon fa fa-square-o"></i>

                                        <div contenteditable="true" class="task-descr" >${task.getTaskText()}</div>
                                        <i class="delete-icon fa fa-times-circle"></i>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                        <!--Выполненные задания -->
                        <div class="tasks-done">
                            <c:forEach items="${tasks}" var="task">
                                <c:if test="${task.getIsDone()}">
                                    <div class="task" data-task-id="${task.getTaskId()}">
                                        <i class="icon fa fa-check-square-o"></i>

                                        <div contenteditable="true" class="task-descr" >${task.getTaskText()}</div>
                                        <i class="delete-icon fa fa-times-circle"></i>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="author-and-icons">
                        <div class="task-author">${list.getAuthor().getUsername()}</div>
                        <div class="task-block">
                            <a class="icon task-remove" href="#"></a>
                        </div>
                    </div>

                </div>
                </c:forEach>
            </div>
        </div>

        <div class="edit-window">
            <div class="top-container clearfix">
                <div class="edit-head">
                    <input id="list-title" type="text" value="" placeholder="Название списка">

                    <div class="task-date">
                        11.11.2015
                    </div>
                </div>
            </div>

            <div class="edit-container">
                <div class="tasks"></div>
                <div class="add-task">
                    <i class="icon fa fa-plus"></i>
                    <span>Новое задание</span>
                </div>
                <div class="tasks-done"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/resources/core/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="/resources/core/js/tasks.js"></script>
</body>
</html>