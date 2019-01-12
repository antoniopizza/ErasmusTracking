<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="main.java.it.unisa.ErasmusTracking.bean.*" %>
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
		Studente studente = (Studente) request.getAttribute("studente");
		Coordinatore coordinatore = (Coordinatore) request.getAttribute("coordinatore");
		Amministratore amministratore = (Amministratore) request.getAttribute("amministratore");
        List<?> tickets = (ArrayList<?>) request.getAttribute("tickets");
		//Account loggedAccount = (Account) session.getAttribute("account");
		//System.out.println("loggedAccount:" + loggedAccount.getId());
		//System.out.println("currentId:" + coordinatore.getId());
	%>
	<meta charset="utf-8" />
	<title>
		ErasmusTracking | Profile
	</title>
	<meta name="description" content="User profile example page">
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
								</svg>
							</a>
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

<%--
														<li class="m-nav__separator m-nav__separator--fit"></li>
														<li class="m-nav__item">
															<a href="header/profile.html" class="m-nav__link">
																<i class="m-nav__link-icon flaticon-info"></i>
																<span class="m-nav__link-text">
																			FAQ
																		</span>
															</a>
														</li>
														<li class="m-nav__item">
															<a href="header/profile.html" class="m-nav__link">
																<i class="m-nav__link-icon flaticon-lifebuoy"></i>
																<span class="m-nav__link-text">
																			Support
																		</span>
															</a>
--%>
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
						<h3 class="m-subheader__title ">
							Profilo
						</h3>
					</div>
				</div>
			</div>
			<!-- END: Subheader -->
			<div class="m-content">
				<div class="row">
					<div class="col-xl-3 col-lg-4">
						<div class="m-portlet m-portlet--full-height  ">
							<div class="m-portlet__body">
								<div class="m-card-profile">
									<div class="m-card-profile__title m--hide">
										Il tuo profilo
									</div>
									<div class="m-card-profile__pic">
										<div class="m-card-profile__pic-wrapper">
											<img src="assets/app/media/img/users/icons8-customer-96.png" alt=""/>
										</div>
									</div>
									<div class="m-card-profile__details">
										<span class="m-card-profile__name">
											<% if(coordinatore != null) { %>
												<%=coordinatore.getNome()%>
											<% } else if(studente != null) { %>
												<%=studente.getNome()%>
											<% } %>
										</span>
										<a href="" class="m-card-profile__email m-link">
											<% if(coordinatore != null) { %>
												<%=coordinatore.getEmail()%>
											<% } else if(studente != null) { %>
												<%=studente.getEmail()%>
											<% } %>
										</a>
									</div>
								</div>
								<ul class="m-nav m-nav--hover-bg m-portlet-fit--sides">
									<li class="m-nav__separator m-nav__separator--fit"></li>
									<li class="m-nav__section m--hide">
										<span class="m-nav__section-text">
											Section
										</span>
									</li>

									<li class="m-nav__item">
										<a href="header/profile&amp;demo=default.html" class="m-nav__link">
											<i class="m-nav__link-icon flaticon-chat-1"></i>
											<span class="m-nav__link-text">
												Tickets
											</span>
										</a>
									</li>

									<li class="m-nav__item">
										<a href="header/profile&amp;demo=default.html" class="m-nav__link">
											<i class="m-nav__link-icon flaticon-list-3"></i>
											<span class="m-nav__link-text">
												Learning Agreement
											</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-xl-9 col-lg-8">
						<div class="m-portlet m-portlet--full-height m-portlet--tabs  ">
							<div class="m-portlet__head">
								<div class="m-portlet__head-tools">
									<ul class="nav nav-tabs m-tabs m-tabs-line   m-tabs-line--left m-tabs-line--primary" role="tablist">
										<li class="nav-item m-tabs__item">
											<a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_user_profile_tab_1" role="tab">
												<i class="flaticon-share m--hide"></i>
												Aggiorna Profilo
											</a>
										</li>

									</ul>
								</div>
							</div>
							<div class="tab-content">
								<div class="tab-pane active" id="m_user_profile_tab_1">
									<%
										if(studente != null) { %>
										<form class="m-form m-form--fit m-form--label-align-right" action="${pageContext.request.contextPath}/AddStudente" method="post">
										<div class="m-portlet__body">
											<div class="form-group m-form__group m--margin-top-10 m--hide">

											</div>
											<div class="form-group m-form__group row">
												<div class="col-10 ml-auto">
													<h3 class="m-form__section">
														Dati Personali
													</h3>
												</div>
											</div>
											<div class="form-group m-form__group row">
												<label class="col-2 col-form-label">
													Nome
												</label>
												<div class="col-7">
													<%
														if(studente.getNome() != null) {
													%>
													<input class="form-control m-input" type="text" name= "nome" value="<%=studente.getNome()%>">
													<%
													} else {
													%>
													<input class="form-control m-input" type="text" name= "nome" value="">
													<%
														}
													%>
												</div>
											</div>
											<div class="form-group m-form__group row">
												<label class="col-2 col-form-label">
													Cognome
												</label>
												<div class="col-7">
													<%
														if(studente.getCognome() != null) {
													%>
													<input class="form-control m-input" type="text" name= "cognome" value="<%=studente.getCognome()%>">
													<%
													} else {
													%>
													<input class="form-control m-input" type="text" name= "cognome" value="">
													<%
														}
													%>
												</div>
											</div>
											<div class="form-group m-form__group row">
												<label class="col-2 col-form-label">
													Numero di telefono
												</label>
												<div class="col-7">
													<%
														if(studente.getTelefono() != null) {
													%>
													<input class="form-control m-input" type="text" name= "telefono" value="<%=studente.getTelefono()%>">
													<%
													} else {
													%>
													<input class="form-control m-input" type="text" name="telefono" value="">
													<%
														}
													%>
												</div>
											</div>
											<div class="form-group m-form__group row">
												<label for="example-text-input" class="col-2 col-form-label">
													Data di nascita
												</label>
												<div class="col-7">
													<%
														if(studente.getDataDiNascita() != null) {
													%>
													<input class="form-control m-input" type="text" name= "data_di_nascita" value="<%=studente.getDataDiNascita()%>">
													<%
													} else {
													%>
													<input class="form-control m-input" type="text" name= "data_di_nascita" value="">
													<%
														}
													%>
												</div>
											</div>
											<div class="form-group m-form__group row">
												<label for="example-text-input" class="col-2 col-form-label">
													Luogo di nascita
												</label>
												<div class="col-7">
													<%
														if(studente.getLuogoDiNascita() != null) {
													%>
														<input class="form-control m-input" type="text" name= "luogo_di_nascita" value="<%=studente.getLuogoDiNascita()%>">
													<%
														} else {
													%>
														<input class="form-control m-input" type="text" name= "luogo_di_nascita" value="">
													<%
														}
													%>
												</div>
											</div>
											<div class="form-group m-form__group row">
												<label for="example-text-input" class="col-2 col-form-label">
													Sesso
												</label>
												<div class="col-7">
													<div class="btn-group btn-group-toggle" data-toggle="buttons">
														<label class="btn btn-success active">
															<%
																if(studente.getSesso()!= null){
																	if(studente.getSesso().equalsIgnoreCase("M")){

																%> 	<input type="radio" name="sesso" id="optionM" autocomplete="off" value ="M" checked>
															M
														</label>
														<label class="btn btn-success">
															<input type="radio" name="sesso" id="optionF" autocomplete="off" value ="F">
															F<%	} else{

																%> 	<input type="radio" name="sesso" id="optionM" autocomplete="off" value ="M">
															M
														</label>
														<label class="btn btn-success">
															<input type="radio" name="sesso" id="optionF" autocomplete="off" value ="F" checked>
															F <%	}
																}
																else {
															%>
															<input type="radio" name="sesso" id="optionM" autocomplete="off" value ="M" checked>
															M
														</label>
														<label class="btn btn-success">
															<input type="radio" name="sesso" id="optionF" autocomplete="off" value ="F">
															F
															<% } %>
														</label>
													</div>
												</div>
											</div>
											<div class="form-group m-form__group row">
												<label class="col-2 col-form-label">
													Nazionalit&agrave;
												</label>
												<div class="col-7">
													<%
														if(studente.getNazionalita() != null) {
													%>
													<input class="form-control m-input" type="text" name= "nazionalita" value="<%=studente.getNazionalita()%>">
													<%
													} else {
													%>
													<input class="form-control m-input" type="text" name="nazionalita" value="">
													<%
														}
													%>
												</div>
											</div>
										</div>
                                            <input style="display:none;" name="update" value="1">
                                            <input style="display:none;" name="page" value="profile">
										<div class="m-portlet__foot m-portlet__foot--fit">
											<div class="m-form__actions">
												<div class="row">
													<div class="col-2"></div>
													<div class="col-7">
														<button type="submit" class="btn btn-accent m-btn m-btn--air m-btn--custom">
															Salva i cambiamenti
														</button>
														&nbsp;&nbsp;
														<button type="reset" class="btn btn-secondary m-btn m-btn--air m-btn--custom">
															Annulla
														</button>
													</div>
												</div>
											</div>
										</div>
									</form>
									<% 	} else if(coordinatore != null) { %>
										<form class="m-form m-form--fit m-form--label-align-right">
										<div class="m-portlet__body">
											<div class="form-group m-form__group m--margin-top-10 m--hide">

											</div>
											<div class="form-group m-form__group row">
												<div class="col-10 ml-auto">
													<h3 class="m-form__section">
														Dati Personali
													</h3>
												</div>
											</div>
											<div class="form-group m-form__group row">
												<label class="col-2 col-form-label">
													Nome
												</label>
												<div class="col-7">
													<%
														if(coordinatore.getNome() != null) {
													%>
													<input class="form-control m-input" type="text" value="<%=coordinatore.getNome()%>">
													<%
													} else {
													%>
													<input class="form-control m-input" type="text" value="">
													<%
														}
													%>
												</div>
											</div>
											<div class="form-group m-form__group row">
												<label class="col-2 col-form-label">
													Cognome
												</label>
												<div class="col-7">
													<%
														if(coordinatore.getCognome() != null) {
													%>
													<input class="form-control m-input" type="text" value="<%=coordinatore.getCognome()%>">
													<%
													} else {
													%>
													<input class="form-control m-input" type="text" value="">
													<%
														}
													%>
												</div>
											</div>
										</div>
										<div class="m-portlet__foot m-portlet__foot--fit">
											<div class="m-form__actions">
												<div class="row">
													<div class="col-2"></div>
													<div class="col-7">
														<button type="submit" class="btn btn-accent m-btn m-btn--air m-btn--custom">
															Salva i cambiamenti
														</button>
														&nbsp;&nbsp;
														<button type="reset" class="btn btn-secondary m-btn m-btn--air m-btn--custom">
															Annulla
														</button>
													</div>
												</div>
											</div>
										</div>
									</form>

									<% 	} %>
								</div>
								<div class="tab-pane " id="m_user_profile_tab_2"></div>
								<div class="tab-pane " id="m_user_profile_tab_3"></div>
							</div>
						</div>

					</div>
					<div class="col-xl-12 col-lg-8">
						<div class="m-portlet m-portlet--mobile">
							<div class="m-portlet__head">
								<div class="m-portlet__head-caption">
									<div class="m-portlet__head-title">
										<h3 class="m-portlet__head-text">
											Tickets
										</h3>
									</div>
								</div>
								<div class="m-portlet__head-tools">
									<button type="button" class="btn btn-success" data-toggle="modal" data-target="#m_scrollable_modal_1">
										New Ticket
									</button>
								</div>
							</div>
							<div class="m-portlet__body">
								<!--begin: Datatable -->
                                <div class="m-portlet__body">
                                    <div class="m-widget3">
                                        <%
                                            if (tickets != null && tickets.size() != 0) {
                                                String search = (String) request.getAttribute("search");
                                                Iterator<?> it = tickets.iterator();
                                                while (it.hasNext()) {
                                                    Ticket bean = (Ticket) it.next();
                                                    if ( bean.getObject().contains(search) || (bean.getMittente()+"").contains(search) || bean.getDataCreazione().contains(search)){
                                        %>


                                        <a href="ticket.jsp" class="m-menu__item">
                                            <div class="m-widget3__item">
                                                <div class="m-widget3__header">
                                                    <div class="m-widget3__user-img">
                                                        <img class="m-widget3__img" src="assets/app/media/img/users/user1.jpg" alt="">
                                                    </div>
                                                    <div class="m-widget3__info">
														<span class="m-widget3__username">
															<%=bean.getMittente()%>
														</span>
                                                        <br>
                                                        <span class="m-widget3__time">
															<%=bean.getDataCreazione()%>
														</span>
                                                    </div>
                                                    <span class="m-widget3__status m--font-info">
														<%=bean.getStato()%>
													</span>
                                                </div>
                                                <div class="m-widget3__body">
                                                    <p class="m-widget3__text">
                                                        <%=bean.getObject()%>
                                                    </p>
                                                </div>
                                            </div>
                                        </a>

                                        <%       }
                                        }
                                        }

                                        %>
                                    </div>
                                </div>
							</div>
						</div>
					</div>

				</div>
				<div class="modal fade" id="m_scrollable_modal_1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">
									New Ticket
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
										<input type="hidden" name="studente" value="<%=studente.getId()%>">
										<div class="form-group">
											<label for="recipient-name" class="form-control-label">
												Oggetto:
											</label>
											<input type="text" class="form-control" id="recipient-name" name="oggetto" >
										</div>
										<div class="form-group">
											<label for="recipient-name" class="form-control-label">
												Tag:
											</label><br>
											<div class="btn-group btn-group-toggle" data-toggle="buttons">
												<label class="btn btn-success active">
													<input type="radio" name="options" id="option1">
													Learning Agreement
													<span></span>
												</label>
                                                <label class="btn btn-success">
													<input type="radio" name="options" id="option2">
													Esami
													<span></span>
												</label>
												<label class="btn btn-success ">
													<input type="radio" name="options" id="option3">
													Richiesta informazioni
													<span></span>
												</label>
											</div>
										</div>
										<div class="form-group">
											<label for="message-text" class="form-control-label">
												Messaggio:
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
	</footer>	<!-- end::Footer -->
</div>
<!-- end:: Page -->
<!-- begin::Quick Sidebar -->
<div id="m_quick_sidebar" class="m-quick-sidebar m-quick-sidebar--tabbed m-quick-sidebar--skin-light">
	<div class="m-quick-sidebar__content m--hide">
				<span id="m_quick_sidebar_close" class="m-quick-sidebar__close">
					<i class="la la-close"></i>
				</span>
		<ul id="m_quick_sidebar_tabs" class="nav nav-tabs m-tabs m-tabs-line m-tabs-line--brand" role="tablist">
			<li class="nav-item m-tabs__item">
				<a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_quick_sidebar_tabs_messenger" role="tab">
					Messages
				</a>
			</li>
			<li class="nav-item m-tabs__item">
				<a class="nav-link m-tabs__link" 		data-toggle="tab" href="#m_quick_sidebar_tabs_settings" role="tab">
					Settings
				</a>
			</li>
			<li class="nav-item m-tabs__item">
				<a class="nav-link m-tabs__link" data-toggle="tab" href="#m_quick_sidebar_tabs_logs" role="tab">
					Logs
				</a>
			</li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active m-scrollable" id="m_quick_sidebar_tabs_messenger" role="tabpanel">
				<div class="m-messenger m-messenger--message-arrow m-messenger--skin-light">
					<div class="m-messenger__messages">
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--in">
								<div class="m-messenger__message-pic">
									<img src="assets/app/media/img//users/user3.jpg" alt=""/>
								</div>
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-username">
											Megan wrote
										</div>
										<div class="m-messenger__message-text">
											Hi Bob. What time will be the meeting ?
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--out">
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-text">
											Hi Megan. It's at 2.30PM
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--in">
								<div class="m-messenger__message-pic">
									<img src="assets/app/media/img//users/user3.jpg" alt=""/>
								</div>
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-username">
											Megan wrote
										</div>
										<div class="m-messenger__message-text">
											Will the development team be joining ?
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--out">
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-text">
											Yes sure. I invited them as well
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="m-messenger__datetime">
							2:30PM
						</div>
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--in">
								<div class="m-messenger__message-pic">
									<img src="assets/app/media/img//users/user3.jpg"  alt=""/>
								</div>
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-username">
											Megan wrote
										</div>
										<div class="m-messenger__message-text">
											Noted. For the Coca-Cola Mobile App project as well ?
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--out">
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-text">
											Yes, sure.
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--out">
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-text">
											Please also prepare the quotation for the Loop CRM project as well.
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="m-messenger__datetime">
							3:15PM
						</div>
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--in">
								<div class="m-messenger__message-no-pic m--bg-fill-danger">
											<span>
												M
											</span>
								</div>
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-username">
											Megan wrote
										</div>
										<div class="m-messenger__message-text">
											Noted. I will prepare it.
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--out">
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-text">
											Thanks Megan. I will see you later.
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="m-messenger__wrapper">
							<div class="m-messenger__message m-messenger__message--in">
								<div class="m-messenger__message-pic">
									<img src="assets/app/media/img//users/user3.jpg"  alt=""/>
								</div>
								<div class="m-messenger__message-body">
									<div class="m-messenger__message-arrow"></div>
									<div class="m-messenger__message-content">
										<div class="m-messenger__message-username">
											Megan wrote
										</div>
										<div class="m-messenger__message-text">
											Sure. See you in the meeting soon.
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="m-messenger__seperator"></div>
					<div class="m-messenger__form">
						<div class="m-messenger__form-controls">
							<input type="text" name="" placeholder="Type here..." class="m-messenger__form-input">
						</div>
						<div class="m-messenger__form-tools">
							<a href="" class="m-messenger__form-attachment">
								<i class="la la-paperclip"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane  m-scrollable" id="m_quick_sidebar_tabs_settings" role="tabpanel">
				<div class="m-list-settings">
					<div class="m-list-settings__group">
						<div class="m-list-settings__heading">
							General Settings
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Email Notifications
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" checked="checked" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Site Tracking
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										SMS Alerts
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Backup Storage
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Audit Logs
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" checked="checked" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
					</div>
					<div class="m-list-settings__group">
						<div class="m-list-settings__heading">
							System Settings
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										System Logs
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Error Reporting
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Applications Logs
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Backup Servers
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" checked="checked" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
						<div class="m-list-settings__item">
									<span class="m-list-settings__item-label">
										Audit Logs
									</span>
							<span class="m-list-settings__item-control">
										<span class="m-switch m-switch--outline m-switch--icon-check m-switch--brand">
											<label>
												<input type="checkbox" name="">
												<span></span>
											</label>
										</span>
									</span>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane  m-scrollable" id="m_quick_sidebar_tabs_logs" role="tabpanel">
				<div class="m-list-timeline">
					<div class="m-list-timeline__group">
						<div class="m-list-timeline__heading">
							System Logs
						</div>
						<div class="m-list-timeline__items">
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
								<a href="" class="m-list-timeline__text">
									12 new users registered
									<span class="m-badge m-badge--warning m-badge--wide">
												important
											</span>
								</a>
								<span class="m-list-timeline__time">
											Just now
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
								<a href="" class="m-list-timeline__text">
									System shutdown
								</a>
								<span class="m-list-timeline__time">
											11 mins
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-danger"></span>
								<a href="" class="m-list-timeline__text">
									New invoice received
								</a>
								<span class="m-list-timeline__time">
											20 mins
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-warning"></span>
								<a href="" class="m-list-timeline__text">
									Database overloaded 89%
									<span class="m-badge m-badge--success m-badge--wide">
												resolved
											</span>
								</a>
								<span class="m-list-timeline__time">
											1 hr
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
								<a href="" class="m-list-timeline__text">
									System error
								</a>
								<span class="m-list-timeline__time">
											2 hrs
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
								<a href="" class="m-list-timeline__text">
									Production server down
									<span class="m-badge m-badge--danger m-badge--wide">
												pending
											</span>
								</a>
								<span class="m-list-timeline__time">
											3 hrs
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
								<a href="" class="m-list-timeline__text">
									Production server up
								</a>
								<span class="m-list-timeline__time">
											5 hrs
										</span>
							</div>
						</div>
					</div>
					<div class="m-list-timeline__group">
						<div class="m-list-timeline__heading">
							Applications Logs
						</div>
						<div class="m-list-timeline__items">
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
								<a href="" class="m-list-timeline__text">
									New order received
									<span class="m-badge m-badge--info m-badge--wide">
												urgent
											</span>
								</a>
								<span class="m-list-timeline__time">
											7 hrs
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
								<a href="" class="m-list-timeline__text">
									12 new users registered
								</a>
								<span class="m-list-timeline__time">
											Just now
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
								<a href="" class="m-list-timeline__text">
									System shutdown
								</a>
								<span class="m-list-timeline__time">
											11 mins
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-danger"></span>
								<a href="" class="m-list-timeline__text">
									New invoices received
								</a>
								<span class="m-list-timeline__time">
											20 mins
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-warning"></span>
								<a href="" class="m-list-timeline__text">
									Database overloaded 89%
								</a>
								<span class="m-list-timeline__time">
											1 hr
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
								<a href="" class="m-list-timeline__text">
									System error
									<span class="m-badge m-badge--info m-badge--wide">
												pending
											</span>
								</a>
								<span class="m-list-timeline__time">
											2 hrs
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
								<a href="" class="m-list-timeline__text">
									Production server down
								</a>
								<span class="m-list-timeline__time">
											3 hrs
										</span>
							</div>
						</div>
					</div>
					<div class="m-list-timeline__group">
						<div class="m-list-timeline__heading">
							Server Logs
						</div>
						<div class="m-list-timeline__items">
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
								<a href="" class="m-list-timeline__text">
									Production server up
								</a>
								<span class="m-list-timeline__time">
											5 hrs
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
								<a href="" class="m-list-timeline__text">
									New order received
								</a>
								<span class="m-list-timeline__time">
											7 hrs
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
								<a href="" class="m-list-timeline__text">
									12 new users registered
								</a>
								<span class="m-list-timeline__time">
											Just now
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
								<a href="" class="m-list-timeline__text">
									System shutdown
								</a>
								<span class="m-list-timeline__time">
											11 mins
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-danger"></span>
								<a href="" class="m-list-timeline__text">
									New invoice received
								</a>
								<span class="m-list-timeline__time">
											20 mins
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-warning"></span>
								<a href="" class="m-list-timeline__text">
									Database overloaded 89%
								</a>
								<span class="m-list-timeline__time">
											1 hr
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
								<a href="" class="m-list-timeline__text">
									System error
								</a>
								<span class="m-list-timeline__time">
											2 hrs
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
								<a href="" class="m-list-timeline__text">
									Production server down
								</a>
								<span class="m-list-timeline__time">
											3 hrs
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-success"></span>
								<a href="" class="m-list-timeline__text">
									Production server up
								</a>
								<span class="m-list-timeline__time">
											5 hrs
										</span>
							</div>
							<div class="m-list-timeline__item">
								<span class="m-list-timeline__badge m-list-timeline__badge--state-info"></span>
								<a href="" class="m-list-timeline__text">
									New order received
								</a>
								<span class="m-list-timeline__time">
											1117 hrs
										</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- end::Quick Sidebar -->
<!-- begin::Scroll Top -->
<div id="m_scroll_top" class="m-scroll-top">
	<i class="la la-arrow-up"></i>
</div>
<!-- end::Scroll Top -->		    <!-- begin::Quick Nav -->
<!-- begin::Quick Nav -->
<!--begin::Base Scripts -->
<script src="assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>
<script src="assets/demo/default/base/scripts.bundle.js" type="text/javascript"></script>
<!--end::Base Scripts -->
</body>
<!-- end::Body -->
</html>
