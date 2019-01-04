<%--
  Created by IntelliJ IDEA.
  User: aleoa
  Date: 04/01/2019
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<button class="m-aside-left-close  m-aside-left-close--skin-dark " id="m_aside_left_close_btn">
    <i class="la la-close"></i>
</button>
<div id="m_aside_left" class="m-grid__item	m-aside-left  m-aside-left--skin-dark ">
    <!-- BEGIN: Aside Menu -->
    <div
            id="m_ver_menu"
            class="m-aside-menu  m-aside-menu--skin-dark m-aside-menu--submenu-skin-dark "
            m-menu-vertical="1"
            m-menu-scrollable="0" m-menu-dropdown-timeout="500"
    >
        <ul class="m-menu__nav  m-menu__nav--dropdown-submenu-arrow ">
            <li class="m-menu__section ">
                <h4 class="m-menu__section-text">
                    Sezioni
                </h4>
                <i class="m-menu__section-icon flaticon-more-v3"></i>
            </li>
            <li class="m-menu__item m-menu__item--submenu" aria-haspopup="true"  m-menu-submenu-toggle="hover">
                <a href="${pageContext.request.contextPath}/AccountServlet?action=doRetrieveAll" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-layers"></i>
                    <span class="m-menu__link-text">
                                Gestione Utente
                            </span>
                </a>
                <!-- GESTIONE UTENTE
                <div class="m-menu__submenu ">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">
                        <li class="m-menu__item  m-menu__item--parent" aria-haspopup="true" >
                                                <span class="m-menu__link">
                                                    <span class="m-menu__link-text">
                                                        Gestione Utente
                                                    </span>
                                                </span>
                        </li>
                        <li class="m-menu__item " aria-haspopup="true" >
                            <a  href="/erasmustracking/profile.jsp" class="m-menu__link ">
                                <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                    <span></span>
                                </i>
                                <span class="m-menu__link-text">
                                                        Cerca utente
                                                    </span>
                            </a>
                        </li>
                        <li class="m-menu__item " aria-haspopup="true" >
                            <a  href="/erasmustracking/profile.jsp class="m-menu__link ">
                            <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                <span></span>
                            </i>
                            <span class="m-menu__link-text">
                                                        Aggiungi Utente
                                                    </span>
                            </a>
                        </li>
                        <li class="m-menu__item " aria-haspopup="true" >
                            <a  href="/erasmustracking/profile.jsp class="m-menu__link ">
                            <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                <span></span>
                            </i>
                            <span class="m-menu__link-text">
                                                        Elimina Utente
                                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>
                -->
            </li>
            <!-- FINE GESTIONE UTENTE -->

            <!-- GESTIONE TICKET -->
            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  m-menu-submenu-toggle="hover">
                <a  href="/erasmustracking/tickets.jsp" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-share"></i>
                    <span class="m-menu__link-text">
                                Ticket
                            </span>
                </a>
                <%--<div class="m-menu__submenu ">
                    <span class="m-menu__arrow"></span>
                    <ul class="m-menu__subnav">
                        <li class="m-menu__item " aria-haspopup="true" >
                            <a  href="/erasmustracking/tickets.jsp" class="m-menu__link ">
                                <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                    <span></span>
                                </i>
                                <span class="m-menu__link-text">
                                                        Apri Ticket
                                                    </span>
                            </a>
                        </li>
                        <li class="m-menu__item " aria-haspopup="true" >
                            <a  href="/erasmustracking/tickets.jsp" class="m-menu__link ">
                                <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                    <span></span>
                                </i>
                                <span class="m-menu__link-text">
                                                        Cerca Ticket
                                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>--%>
            </li>

            <!-- FINE TICKET -->
            <!-- GESTIONE LOCALITÀ -->

            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  m-menu-submenu-toggle="hover">
                <a  href="/erasmustracking/localita.jsp" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-multimedia-1"></i>
                    <span class="m-menu__link-text">
                                Localit&agrave;
                            </span>
                    <%--<i class="m-menu__ver-arrow la la-angle-right"></i>--%>
                </a>
                <%--  <div class="m-menu__submenu ">
                      <span class="m-menu__arrow"></span>
                      <ul class="m-menu__subnav">

                          <li class="m-menu__item " aria-haspopup="true" >
                              <a  href="components/icons/fontawesome.html" class="m-menu__link ">
                                  <i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                      <span></span>
                                  </i>
                                  <span class="m-menu__link-text">
                                                          Visualizza localit&agrave;

                                                      </span>
                              </a>
                          </li>
                          <li class="m-menu__item " aria-haspopup="true" >
                              <a  href="components/icons/fontawesome.html" class="m-menu__link ">
                                  <!--<i class="m-menu__link-bullet m-menu__link-bullet--dot">
                                      <span></span>
                                  </i>-->
                                  <span class="m-menu__link-text">
                                                          Aggiungi localit&agrave;
                                                      </span>
                              </a>
                          </li>
                      </ul>
                  </div>--%>
            </li>
            <!-- GESTIONE Learning -->
            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  m-menu-submenu-toggle="hover">
                <a  href="/erasmustracking/learning-agreement.jsp" class="m-menu__link m-menu__toggle">
                    <i class="m-menu__link-icon flaticon-file-1"></i>
                    <span class="m-menu__link-text">
														Learning Agreement
													</span>
                    <%--<i class="m-menu__ver-arrow la la-angle-right"></i>--%>
                </a>

            </li>

            </li>
        </ul>
    </div>
    <!-- END: Aside Menu -->
</div>