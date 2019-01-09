<%--
  Created by IntelliJ IDEA.
  User: aleoa
  Date: 04/01/2019
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ruolo = (String) session.getAttribute("ruolo");
    System.out.println(ruolo);
%>
<button class="m-aside-left-close  m-aside-left-close--skin-dark " id="m_aside_left_close_btn">
    <i class="la la-close"></i>
</button>
<div id="m_aside_left" class="m-grid__item	m-aside-left  m-aside-left--skin-dark ">

    <!-- BEGIN: Aside Menu -->
    <div id="m_ver_menu"
         class="m-aside-menu  m-aside-menu--skin-dark m-aside-menu--submenu-skin-dark "
         m-menu-vertical="1"
         m-menu-scrollable="0" m-menu-dropdown-timeout="500" >
        <ul class="m-menu__nav  m-menu__nav--dropdown-submenu-arrow ">

            <li class="m-menu__section ">
                <h4 class="m-menu__section-text">
                    Sezioni
                </h4>
                <i class="m-menu__section-icon flaticon-more-v3"></i>
            </li>




                <% if(!ruolo.equals("studente")) { %>
                 <li class="m-menu__item m-menu__item--submenu" aria-haspopup="true"  m-menu-submenu-toggle="hover">
                    <% if(ruolo.equals("coordinatore")) { %>
                        <a href="${pageContext.request.contextPath}/StudenteServlet?action=doRetrieveByCoordinatore" class="m-menu__link m-menu__toggle">
                    <% } else { %>
                        <a href="${pageContext.request.contextPath}/AccountServlet?action=doRetrieveAll" class="m-menu__link m-menu__toggle">
                    <% } %>
                    <i class="m-menu__link-icon flaticon-layers"></i>
                    <span class="m-menu__link-text">
                        Gestione Utente
                    </span>
                    </a>
                 </li>
            <% } %>

            <!-- FINE GESTIONE UTENTE -->

            <!-- GESTIONE TICKET -->
            <% if(!ruolo.equals("amministratore")) { %>

            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  m-menu-submenu-toggle="hover">
                <a  href="${pageContext.request.contextPath}/TicketServlet?action=doRetrieveByIdCoordinatore" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-chat-1"></i>
                    <span class="m-menu__link-text">
                        Ticket
                    </span>
                </a>
            </li>

            <% } %>
            <!-- FINE TICKET -->

            <!-- GESTIONE LOCALITÀ -->

            <% if(!ruolo.equals("amministratore") && !ruolo.equals("studente")) { %>
            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  m-menu-submenu-toggle="hover">
                <a href="${pageContext.request.contextPath}/LocalitaServlet?action=doRetrieveAll" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-pin"></i>
                    <span class="m-menu__link-text">
                        Localit&agrave
                    </span>
                </a>
            </li>
            <% } %>

            <!-- GESTIONE Learning -->
            <% if(!ruolo.equals("amministratore") && !ruolo.equals("coordinatore")) { %>
            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  m-menu-submenu-toggle="hover">
                <a  href="${pageContext.request.contextPath}/learning-agreement.jsp" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-list-3"></i>
                    <span class="m-menu__link-text">
                        Learning Agreement
                    </span>
                </a>
            </li>
            <% } %>

            <% if(!ruolo.equals("amministratore")) { %>
            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  m-menu-submenu-toggle="hover">
                <a  href="${pageContext.request.contextPath}/DocumentiServlet?action=doRetrieveAll" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-folder"></i>
                    <span class="m-menu__link-text">
                        Documenti
                    </span>
                </a>
            </li>
            <% } %>


            </li>
        </ul>
    </div>
    <!-- END: Aside Menu -->
</div>