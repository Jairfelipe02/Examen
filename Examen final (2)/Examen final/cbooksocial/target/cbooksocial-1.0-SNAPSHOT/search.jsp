<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/head_tag.jsp">
        <jsp:param name="title" value="Search friends" />
    </jsp:include>
    <body>
        <%@include file="/WEB-INF/top_nav.jsp" %>
        <section class="container-fluid" id="main-body">
            <div class="row no-pad">
                <div class="col-md-5 no-pad">
                    <div class="middle-section search-result-box">
                        <div class="search-result-box-header">
                            <i class="fa fa-users" aria-hidden="true"></i>
                            <span>PEOPLE</span>
                        </div>
                        <div class="search-result-box-content">
                            <%@include file="WEB-INF/search-result.jsp" %>
                        </div>
                        <div class="search-result-box-footer">
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script>
            $(function () {
                $('#search-friend').keypress(function () {
                    $.ajax({
                        url: 'search',
                        type: 'POST',
                        data: {
                            'action': 'ajax-search',
                            'search-friend': $(this).val()
                        },
                        success: function (data) {
                            $('.search-result-box-content').html(data);
                        },
                        error: function () {
                            alert('Cannot perform searching');
                        }
                    });
                });

                var viewportHeight = $(window).height();
                $("#online-list").css("max-height", viewportHeight);
            });
        </script>
        <script src="js/app.js"></script>
    </body>

</html>
