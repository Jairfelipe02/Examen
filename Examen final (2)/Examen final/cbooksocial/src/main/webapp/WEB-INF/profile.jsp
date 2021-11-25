<%-- 
    Document   : login.jsp
    Created on : Jan 21, 2019, 3:28:39 PM
    Author     : learnbyproject.net
--%>
<%@page import="net.learnbyproject.helper.Keys"%>
<%@page import="java.util.Objects"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/custom.tld" prefix="dates" %>
<!DOCTYPE html>
<html>

    <jsp:include page="/WEB-INF/head_tag.jsp">
        <jsp:param name="title" value="your profile" />
    </jsp:include>

    <body>
        <%@include file="/WEB-INF/top_nav.jsp" %>
        <section class="container-fluid" id="main-body">
            <div class="row no-pad">
                <div class="col-md-8 no-pad">
                    <div class="profile-header">
                        <div class="profile-header-top">
                            <span id="profile-button-add-cover">
                                <form action="upload-image" id="frmUploadPhoto" enctype="multipart/form-data" method="post">
                                    <i class="fa fa-camera" aria-hidden="true"></i> Add Cover Photo
                                    <input class="input-cover-photo" type="file" id="userCoverPhoto" 
                                           name="userCoverPhoto" />
                                </form>
                            </span>
                            <img src="data:image/png;base64,${sessionScope[Keys.USER].avatar}">

                            <h3>
                                ${sessionScope[Keys.USER].firstName}
                                ${sessionScope[Keys.USER].lastName}
                            </h3>
                        </div>
                        <ul class="profile-header-nav">
                            <li></li>
                            <li><a href="/">Timeline</a></li>
                            <li><a href="/">About</a></li>
                            <li><a href="/">Friends</a></li>
                            <li><a href="/">Photos</a></li>
                            <li><a href="/">More <i class="fa fa-caret-down" aria-hidden="true"></i></a></li>
                        </ul>
                    </div>
                    <div class="profile-body">
                        <div class="profile-body-header">
                            <h3><i class="fa fa-user" aria-hidden="true"></i> About</h3>
                        </div>
                        <div class="profile-body-content">
                            <div class="row no-pad">
                                <div class="col-md-4 profile-body-content-tool no-pad">
                                    <a href="/" class="active">Overview</a>
                                    <a href="/">Work and education</a>
                                    <a href="/">Places you've lived</a>
                                    <a href="/">Contact information</a>
                                    <a href="/">Family and relationship</a>
                                    <a href="/">Detailed about you</a>
                                    <a href="/">Life events</a>
                                </div>
                                <div class="col-md-8 profile-body-content-editing">
                                    <h4>Overview</h4>
                                    <div class="overview-form">
                                        <div class="alert-danger">
                                            ${sessionScope[Keys.ERROR]}
                                        </div>
                                        <div class="alert-success">
                                            ${sessionScope[Keys.SUCCESS]}
                                        </div>
                                        <form action="profile" method="post">
                                            <label>First Name:</label>
                                            <input required type="text" name="first-name" 
                                                   value="${sessionScope[Keys.USER].firstName}" maxlength="30" />

                                            <label>Last Name:</label>
                                            <input required type="text" name="last-name" 
                                                   value="${sessionScope[Keys.USER].lastName}" maxlength="30" />

                                            <label>Email/Mobile:</label>
                                            <input required type="text" name="mobile-or-email" 
                                                   value="${sessionScope[Keys.USER].emailOrMobile}" />

                                            <label>Password:</label>
                                            <input required type="password" name="user-password" 
                                                   value="${sessionScope[Keys.USER].password}" />

                                            <label>Sex:</label>
                                            <input checked type="radio" name="sex" value="male"> <label class="light" for="male">Male</label>
                                            <input type="radio" name="sex" value="female"> <label class="light" for="female">Female</label>

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

                                            <button name="action" value="update-profile" 
                                                    type="submit">Save Changes</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>                                     
        </section>
        <script>
            $(function () {
                // handle upload file
                $('#userCoverPhoto').change(function() {
                    $("#frmUploadPhoto").submit();
                });
                
                let day = "${dates:getDateParts(sessionScope[Keys.USER].birthday)[0]}";
                let month = "${dates:getDateParts(sessionScope[Keys.USER].birthday)[1]}";
                let year = "${dates:getDateParts(sessionScope[Keys.USER].birthday)[2]}";
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
                var selectedSex = "${sessionScope[Keys.USER].sex}";
                $("input[name=sex][value=" + selectedSex + "]").prop('checked', true);


                var viewportHeight = $(window).height();
                $("#online-list").css("max-height", viewportHeight);
            });
        </script>
        <script src="js/app.js"></script>
    </body>

</html>
