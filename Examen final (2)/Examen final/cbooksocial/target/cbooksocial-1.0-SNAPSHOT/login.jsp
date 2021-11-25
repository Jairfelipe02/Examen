<%-- 
    Document   : login.jsp
    Created on : Jan 21, 2019, 3:28:39 PM
    Author     : learnbyproject.net
--%>
<%@page import="java.util.Objects"%>
<%@page import="net.learnbyproject.helper.Keys"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <jsp:include page="/WEB-INF/head_tag.jsp">
        <jsp:param name="title" value="Login" />
    </jsp:include>

    <body>
        <nav class="container-fluid" id="main-nav-login">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <a href="./news-feed.jsp" id="logo-large">CBOOKSOCIAL</a>
                    </div>
                </div>
            </div>
        </nav>

        <section class="container-fluid" style="background-color: #e9ebee; margin-top:-30px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <form action="login" method="post" id="body-login-form">
                            <label>Login to CBOOKSOCIAL</label>
                            <div class="alert-danger">
                                <%--<%= Objects.toString(session.getAttribute(Keys.ERROR), "")  %>--%>
                                ${sessionScope[Keys.ERROR]}
                            </div>
                            <input type="text" name="user-login-id" placeholder="Email address or phone number" />
                            <input type="password" name="user-login-password" placeholder="Password" />
                            <button type="submit" id="btn-login">Login</button>
                            <a href="/">Forgotten password?</a> <br>
                            <a href="./register.jsp">Signup for CBOOKSOCIAL?</a>
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
    </body>

</html>
