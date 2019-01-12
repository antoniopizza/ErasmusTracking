<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String url = (String) request.getParameter("link");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- <meta http-equiv="Refresh" content="2;url=login.jsp?link=<%=url %>">  Reindirizza alla pagina di login dopo 2 secondi -->
    <meta http-equiv="Refresh" content="0.1;url=login.jsp">

    <link rel="stylesheet" type="text/css" href="css/page_style.css">
    <link rel="stylesheet" type="text/css" href="css/footer.css">
    <link rel="stylesheet" href="css/slides.css" type="text/css">
    <link rel="stylesheet" href="css/divStyle.css" type="text/css">


    <title>ErasmusTracking - Logout</title>

</head>
<body>
<div class = "navbar">

    <!--<div id="main_menu">
        <nav>
            <ul id="menu">
                <li class="current"><a href="index.jsp"><img src="img/logo.jpg" alt="Home" class="icon" id="home"></a></li>
            </ul>
        </nav>

    </div>-->


</div>


<%
    //HttpSession session=request.getSession();
    session.invalidate(); //Invalidando la sessione, le altre pagine non vedono l'account collegato.

%>
<br/>
<div class="divContorno">
    <div id="contenitore">
        <p>Disconnessione. Verrai reindirizzato alla pagina home in 1 secondi.</p>
    </div>
</div>
<%


%>

</body>
</html>