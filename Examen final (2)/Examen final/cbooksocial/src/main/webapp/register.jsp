<%-- 
    Document   : login.jsp
    Created on : Jan 21, 2019, 3:28:39 PM
    Author     : learnbyproject.net
--%>
<%@page import="net.learnbyproject.helper.Keys"%>
<%@page import="java.util.Objects"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/head_tag.jsp">
        <jsp:param name="title" value="Register new account" />
    </jsp:include>
    <body>
        <nav class="container-fluid" id="main-nav-login">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <a href="./news-feed.jsp" id="logo-large">CBOOKSOCIAL</a>
                    </div>
                    <div class="col-md-6">
                        <form action="/" method="post" class="login-form-top">
                            <div class="login-form-input">
                                <label>Email or phone</label>
                                <input type="text" name="user-login-id" />
                            </div>
                            <div class="login-form-input">
                                <label>Password</label>
                                <input type="password" name="user-login-password" />
                                <button type="button" id="btn-login">Login</button>
                                <a href="/">Forgotten password?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </nav>

        <section class="container-fluid" id="body-register-form">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h3>CBOOKSOCIAL helps you connect and share with the people in your life.</h3>
                        <img src="./img/user-map.png">
                    </div>
                    <div class="col-md-6">
                        <h3>Create an account</h3>
                        <p>It's free and always will be.</p>
                        <div class="alert-danger">
                            <%--<%= Objects.toString(request.getAttribute(Keys.ERROR), "")%>--%>
                            ${requestScope[Keys.ERROR]}
                        </div>
                        <form action="register" method="post" class="register-form">
                            <div class="reg-input-full-name">
                                <div class="reg-input" id="first-name">
                                    <input type="text" name="first-name" 
                                           value="${param["first-name"]}" placeholder="First name">
                                </div>
                                <div class="reg-input" id="last-name">
                                    <input type="text" name="last-name" 
                                           value="${param["last-name"]}" placeholder="Last name">
                                </div>
                            </div>
                            <div class="reg-input" id="mobile-or-email">
                                <input type="text" name="mobile-or-email" 
                                       value="${param["mobile-or-email"]}" placeholder="Mobile phone or email address">
                            </div>
                            <div class="reg-input" id="user-password">
                                <input type="password" name="user-password" placeholder="New password">
                            </div>
                            <label style="display:block;">Birthday</label>
                            <div class="reg-input">
                                <select name="day" id="days">
                                    <option>Day</option>
                                </select>
                                <select name="month" id="months">
                                    <option>Month</option>
                                </select>
                                <select name="year" id="years">
                                    <option>Year</option>
                                </select>
                            </div>
                            <div class="reg-input">
                                <input type="radio" checked name="sex" value="female"><label>Female</label>
                                <input type="radio" name="sex" value="male"><label>Male</label>
                            </div>
                            <p class="help-block">
                                By clicking Create an account, you agree to our Terms and that you have read our Data Policy, including our Cookie Use.
                            </p>
                            <button type="submit" id="reg-button">Create an account</button>
                            <a href="./login.jsp">Have account? login here</a>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <footer class="container">
            <ul id="lans">
                <li>English (UK)</li>
                <li>
                    <a href="#">Tiáº¿ng viá»t</a>
                </li>
                <li>
                    <a href="#">ä¸­æ(å°ç£)</a>
                </li>
                <li>
                    <a href="#">íêµ­ì´</a>
                </li>
                <li>
                    <a href="#">æ¥æ¬èª</a>
                </li>
                <li>
                    <a href="#">FranÃ§ais (France)</a>
                </li>
                <li>
                    <a href="#">à¸ à¸²à¸©à¸²à¹à¸à¸¢</a>
                </li>
                <li>
                    <a href="#">EspaÃ±ol</a>
                </li>
                <li>
                    <a href="#">PortuguÃªs (Brasil)</a>
                </li>
                <li>
                    <a href="#">Deutsch</a>
                </li>
                <li>
                    <a href="#">Italiano</a>
                </li>
                <li>+</li>
            </ul>
            <ul id="footer-tools">
                <li><a href="/">Sign Up</a></li>
                <li>
                    <a href="#">Log In</a>
                </li>
                <li>
                    <a href="#">Messenger</a>
                </li>
                <li>
                    <a href="#">Facebook Lite</a>
                </li>
                <li>
                    <a href="#">Mobile</a>
                </li>
                <li>
                    <a href="#">Find Friends</a>
                </li>
                <li>
                    <a href="#">Badges</a>
                </li>
                <li>
                    <a href="#">People</a>
                </li>
                <li>
                    <a href="#">Pages</a>
                </li>
                <li>
                    <a href="#">Places</a>
                </li>
                <li>
                    <a href="#">Games</a>
                </li>
                <li><a href="/">Locations</a></li>
                <li>
                    <a href="#">Celebrities</a>
                </li>
                <li>
                    <a href="#">Groups</a>
                </li>
                <li>
                    <a href="#">Facebook Lite</a>
                </li>
                <li>
                    <a href="#">Mobile</a>
                </li>
                <li>
                    <a href="#">Find Friends</a>
                </li>
                <li>
                    <a href="#">Badges</a>
                </li>
                <li>
                    <a href="#">People</a>
                </li>
                <li>
                    <a href="#">Pages</a>
                </li>
                <li>
                    <a href="#">Places</a>
                </li>
                <li>
                    <a href="#">Games</a>
                </li>
            </ul>
            CBOOKSOCIAL &copy; 2016
        </footer>

        <script src="./js/app.js"></script>

        <script>
            $(function () {
                let day = "${param["day"]}";
                let month = "${param["month"]}";
                let year = "${param["year"]}";
                for (var i = 1; i <= 31; i++) {
                    if (i == day)
                        $("#days").append("<option selected>" + i + "</option>");
                    else
                        $("#days").append("<option>" + i + "</option>");
                }

                for (var i = 1; i <= 12; i++) {
                    if (i == month)
                        $("#months").append("<option selected>" + i + "</option>");
                    else
                        $("#months").append("<option>" + i + "</option>");
                }

                for (var i = 2016; i >= 1905; i--) {
                    if (i == year)
                        $("#years").append("<option selected>" + i + "</option>");
                    else
                        $("#years").append("<option>" + i + "</option>");
                }
                var selectedSex = "${param["sex"]}";
                $("input[name=sex][value=" + selectedSex + "]").prop('checked', true);
            });
        </script>
    </body>

</html>
