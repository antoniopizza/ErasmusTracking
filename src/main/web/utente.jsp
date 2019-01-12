<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.java.it.unisa.ErasmusTracking.bean.Localita" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="main.java.it.unisa.ErasmusTracking.bean.Account" %>
<!DOCTYPE html>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 4
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Dribbble: www.dribbble.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
Renew Support: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<html lang="en" >
<!-- begin::Head -->
<head>
    <%

        List<?> accounts = (ArrayList<?>) request.getAttribute("listaStudenti");
    %>

    <meta charset="utf-8" />
    <title>
        Erasmus Tracking - Utente
    </title>
    <meta name="description" content="Default form examples">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!--begin::Web font -->
    <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.16/webfont.js"></script>
    <script>
          WebFont.load({
            google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700"]},
            active: function() {
                sessionStorage.fonts = true;
            }
          });
		</script>
    <!--end::Web font -->
    <!--begin::Base Styles -->
    <link href="assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
    <link href="assets/demo/default/base/style.bundle.css" rel="stylesheet" type="text/css" />
    <!--end::Base Styles -->
    <link rel="shortcut icon" href="assets/demo/default/media/img/logo/aereo%20+mondo-%20senza%20scritta%20logo.png" />
</head>
<!-- end::Head -->
<!-- end::Body -->
<body  class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default"  >
<!-- begin:: Page -->
<div class="m-grid m-grid--hor m-grid--root m-page">
    <!-- BEGIN: Header -->
    <header id="m_header" class="m-grid__item    m-header "  m-minimize-offset="200" m-minimize-mobile-offset="200" >
        <div class="m-container m-container--fluid m-container--full-height">
            <div class="m-stack m-stack--ver m-stack--desktop">
                <!-- BEGIN: Brand -->
                <div class="m-stack__item m-brand  m-brand--skin-dark ">
                    <div class="m-stack m-stack--ver m-stack--general">
                        <div class="m-stack__item m-stack__item--middle m-brand__logo">
                            <a href="/erasmustracking/index.jsp" class="m-brand__logo-wrapper">
                                <svg xmlns="http://www.w3.org/2000/svg" width="160px" height="0">
                                    <img src="assets/demo/default/media/img/logo/ErasmusTrackingLogoPerSfondoScuro.svg"/>
                                </svg>                            </a>
                        </div>
                        <div class="m-stack__item m-stack__item--middle m-brand__tools">
                            <!-- BEGIN: Left Aside Minimize Toggle -->
                            <a href="javascript:;" id="m_aside_left_minimize_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-desktop-inline-block">
                                <span></span>
                            </a>
                            <!-- END -->
                            <!-- BEGIN: Responsive Aside Left Menu Toggler -->
                            <a href="javascript:;" id="m_aside_left_offcanvas_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-tablet-and-mobile-inline-block">
                                <span></span>
                            </a>
                            <!-- END -->
                            <!-- BEGIN: Responsive Header Menu Toggler -->
                            <a id="m_aside_header_menu_mobile_toggle" href="javascript:;" class="m-brand__icon m-brand__toggler m--visible-tablet-and-mobile-inline-block">
                                <span></span>
                            </a>
                            <!-- END -->
                            <!-- BEGIN: Topbar Toggler -->
                            <a id="m_aside_header_topbar_mobile_toggle" href="javascript:;" class="m-brand__icon m--visible-tablet-and-mobile-inline-block">
                                <i class="flaticon-more"></i>
                            </a>
                            <!-- BEGIN: Topbar Toggler -->
                        </div>
                    </div>
                </div>
                <!-- END: Brand -->
                <div class="m-stack__item m-stack__item--fluid m-header-head" id="m_header_nav">
                    <!-- BEGIN: Horizontal Menu -->
                    <button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-dark " id="m_aside_header_menu_mobile_close_btn">
                        <i class="la la-close"></i>
                    </button>

                    <!-- END: Horizontal Menu -->								<!-- BEGIN: Topbar -->
                    <div id="m_header_topbar" class="m-topbar  m-stack m-stack--ver m-stack--general m-stack--fluid">
                        <div class="m-stack__item m-topbar__nav-wrapper">
                            <ul class="m-topbar__nav m-nav m-nav--inline">
                                <li class="
	m-nav__item m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light"
                                    m-dropdown-toggle="click" id="m_quicksearch" m-quicksearch-mode="dropdown" m-dropdown-persistent="1">
                                    <a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-nav__link-icon">
													<i class="flaticon-search-1"></i>
												</span>
                                    </a>
                                    <div class="m-dropdown__wrapper">
                                        <span class="m-dropdown__arrow m-dropdown__arrow--right"></span>
                                        <div class="m-dropdown__inner ">
                                            <div class="m-dropdown__header">
                                                <form  class="m-list-search__form">
                                                    <div class="m-list-search__form-wrapper">
																<span class="m-list-search__form-input-wrapper">
																	<input id="m_quicksearch_input" autocomplete="off" type="text" name="q" class="m-list-search__form-input" value="" placeholder="Search...">
																</span>
                                                        <span class="m-list-search__form-icon-close" id="m_quicksearch_close">
																	<i class="la la-remove"></i>
																</span>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="m-dropdown__body">
                                                <div class="m-dropdown__scrollable m-scrollable" data-scrollable="true" data-max-height="300" data-mobile-max-height="200">
                                                    <div class="m-dropdown__content"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>

                                <!-- Mini menu -->
                                <li class="m-nav__item m-topbar__user-profile m-topbar__user-profile--img  m-dropdown m-dropdown--medium m-dropdown--arrow m-dropdown--header-bg-fill m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light" m-dropdown-toggle="click">
                                    <a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-topbar__userpic">
													<img src="assets/app/media/img/users/icons8-customer-96.png" class="m--img-rounded m--marginless m--img-centered" alt=""/>
												</span>
                                        <span class="m-topbar__username m--hide">
													Nick
												</span>
                                    </a>
                                    <div class="m-dropdown__wrapper">
                                        <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
                                        <div class="m-dropdown__inner">
                                            <div class="m-dropdown__header m--align-center" style="background: url(assets/app/media/img/misc/user_profile_bg.jpg); background-size: cover;">
                                                <div class="m-card-user m-card-user--skin-dark">
                                                    <div class="m-card-user__pic">
                                                        <img src="assets/app/media/img/users/icons8-customer-96.png" class="m--img-rounded m--marginless" alt=""/>
                                                    </div>
                                                    <div class="m-card-user__details">
																<span class="m-card-user__name m--font-weight-500">
																	Mark Andre
																</span>
                                                        <a href="" class="m-card-user__email m--font-weight-300 m-link">
                                                            mark.andre@gmail.com
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="m-dropdown__body">
                                                <div class="m-dropdown__content">
                                                    <ul class="m-nav m-nav--skin-light">
                                                        <li class="m-nav__section m--hide">
																	<span class="m-nav__section-text">
																		Section
																	</span>
                                                        </li>
                                                        <%Account account = (Account) session.getAttribute("utente");%>
                                                        <li class="m-nav__item">
                                                            <a href="${pageContext.request.contextPath}/AccountServlet?action=doRetrieveById&id=<%=account.getId()%>" class="m-nav__link">
                                                                <i class="m-nav__link-icon flaticon-profile-1"></i>
                                                                <span class="m-nav__link-title">
																			<span class="m-nav__link-wrap">
																				<span class="m-nav__link-text">
																					Il mio profilo
																				</span>

																			</span>
																		</span>
                                                            </a>
                                                        </li>

                                                        <li class="m-nav__item">
                                                            <a href="header/profile.html" class="m-nav__link">
                                                                <i class="m-nav__link-icon flaticon-chat-1"></i>
                                                                <span class="m-nav__link-text">
																			Tickets
																		</span>
                                                            </a>
                                                        </li>

                                                        <li class="m-nav__separator m-nav__separator--fit"></li>
                                                        <li class="m-nav__item">
                                                            <a href="/erasmustracking/logout.jsp" class="btn m-btn--pill    btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">
                                                                Logout
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>


                            </ul>
                        </div>
                    </div>
                    <!-- END: Topbar -->
                </div>
            </div>
        </div>
    </header>
    <!-- END: Header -->
    <!-- begin::Body -->
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
        <!-- BEGIN: Left Aside -->
        <%@include file="m_aside_left.jsp"%>
        <!-- END: Left Aside -->
        <div class="m-grid__item m-grid__item--fluid m-wrapper">
            <!-- BEGIN: Subheader -->
            <div class="m-subheader ">
                <div class="d-flex align-items-center">
                    <div class="mr-auto">
                        <h3 class="m-subheader__title m-subheader__title--separator">
                            Utente
                        </h3>
                    </div>

                </div>
            </div>
            <!-- END: Subheader -->
            <div class="m-content">
                <div class="row">
                    <div class="col-lg-12">
                        <!--begin::Modulo 2-->
                        <div class="m-portlet">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
												<span class="m-portlet__head-icon m--hide">
													<i class="la la-gear"></i>
												</span>
                                        <h3 class="m-portlet__head-text">
                                            Nuovo Utente
                                        </h3>
                                    </div>
                                </div>
                            </div>
                            <!--begin::Form-->
                            <%
                                if(ruolo.equalsIgnoreCase("amministratore")) {
                            %>
                                <form action="${pageContext.request.contextPath}/AddCoordinatore" method="post" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator">
                            <% } else { %>
                                <form action="${pageContext.request.contextPath}/AddStudente" method="post" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator">
                            <% } %>
                                    <div class="m-portlet__body">
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg-2 col-form-label">
                                            Nome:
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control m-input" placeholder="Nome" name="nome" >
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg-2 col-form-label">
                                            Cognome:
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control m-input" placeholder="Cogonome" name="cognome">
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg-2 col-form-label">
                                            Email:
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="email" class="form-control m-input" placeholder="e-mail" name="email" >
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg-2 col-form-label">
                                            Password:
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="password" class="form-control m-input" placeholder="password" name="password" >
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg-2 col-form-label">
                                            Matricola:
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control m-input" placeholder="matricola" name="matricola" >
                                        </div>
                                    </div>
                                </div>
                                    <input style="display:none;" name="update" value="0">
                                <div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
                                    <div class="m-form__actions m-form__actions--solid">
                                        <div class="row">
                                            <div class="col-lg-2"></div>
                                            <div class="col-lg-6">
                                                <%
                                                    if(ruolo.equalsIgnoreCase("amministratore")) {
                                                %>
                                                <button type="submit" class="btn btn-success">
                                                    Aggiungi Coordinatore
                                                </button>
                                                <button type="reset" class="btn btn-secondary">
                                                    Cancel
                                                </button>
                                                <%
                                                } else { System.out.println("334 ce stong");
                                                %>
                                                <button type="submit" class="btn btn-success">
                                                    Aggiungi Studente
                                                </button>
                                                <button type="reset" class="btn btn-secondary">
                                                    Annulla
                                                </button>

                                                <%
                                                    }
                                                %>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!--end::Form-->
                        </div>
                        <!--end::Portlet-->
                    </div>
                </div>
                <!-- BEGIN: Lista Utenti -->
                <div class="m-portlet m-portlet--mobile">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h3 class="m-portlet__head-text">
                                    Lista Studenti
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="m-portlet__body">
                        <!--begin: Datatable -->
                        <table class="table table-striped- table-bordered table-hover table-checkable" id="m_table_1">
                            <thead>
                            <tr>
                                <th>
                                    Nome
                                </th>

                                <th>
                                    Cognome
                                </th>

                                <th>
                                    Email
                                </th>
                                <th>
                                    Location
                                </th>
                                <th>
                                    Azioni
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                if (accounts != null && accounts.size() != 0) {
                                    Iterator<?> it = accounts.iterator();
                                    while (it.hasNext()) {
                                        Account bean = (Account) it.next();
                            %>
                            <tr>

                                <td>
                                    <a href="/erasmustracking/AccountServlet?action=doRetrieveById&id=<%=bean.getId()%>">
                                        <%=bean.getNome()%>
                                    </a>
                                </td>
                                <td>
                                    <a href="/erasmustracking/AccountServlet?action=doRetrieveById&id=<%=bean.getId()%>">
                                        <%=bean.getCognome()%>
                                    </a>
                                </td>
                                <td>
                                    <%=bean.getEmail()%>
                                </td>
                                <td>
                                    Lorem Ipsum
                                </td>

                                <td nowrap="" style="">
                                    <span class="dropdown">
                                        <a href="#" class="btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" data-toggle="dropdown" aria-expanded="true">
                                          <i class="la la-ellipsis-h"></i>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" data-toggle="modal" data-target="#m_scrollable_modal_1"><i class="la la-edit"></i>Richiesta eliminazione</a>
                                        </div>
                                    </span>
                                </td>
                            </tr>
                            <%
                                }
                            }
                            %>

                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- END: Lista Utenti -->
                <div class="modal fade" id="m_scrollable_modal_1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">
                                    Richiesta eliminazione studente
                                </h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												×
											</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="m-scrollable" data-scrollbar-shown="true" data-scrollable="true" data-height="200" style="height: 200px; overflow: auto;">
                                    <form>
                                        <div class="form-group">
                                            <label for="message-text" class="form-control-label">
                                                Motivazione:
                                            </label>
                                            <textarea class="form-control" id="message-text" rows="6"></textarea>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="m_blockui_4_1">
                                    Invia
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end:: Body -->
    <!-- begin::Footer -->
    <footer class="m-grid__item		m-footer ">
        <div class="m-container m-container--fluid m-container--full-height m-page__container">
            <div class="m-stack m-stack--flex-tablet-and-mobile m-stack--ver m-stack--desktop">
                <div class="m-stack__item m-stack__item--left m-stack__item--middle m-stack__item--last">
							<span class="m-footer__copyright">
								2017 &copy; Metronic theme by
								<a href="https://www.unisa.it/" class="m-link">
									Unisa
								</a>
							</span>
                </div>
            </div>
        </div>
    </footer>
    <!-- end::Footer -->
</div>
<!-- end:: Page -->
<!-- begin::Scroll Top -->
<div id="m_scroll_top" class="m-scroll-top">
    <i class="la la-arrow-up"></i>
</div>
<!--begin::Base Scripts -->
<script src="assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>
<script src="assets/demo/default/base/scripts.bundle.js" type="text/javascript"></script>
<!--end::Base Scripts -->
</body>
<!-- end::Body -->
</html>
