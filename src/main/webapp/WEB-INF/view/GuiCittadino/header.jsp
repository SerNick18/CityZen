<%@ page import="model.gestioneDati.modelObjects.Cittadino" %>
<%@ page import="model.gestioneDati.facadeDataAccess.FacadeDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.gestioneDati.modelObjects.SegnalazioneInterface" %>
<%@ page import="model.gestioneDati.modelObjects.Segnalazione" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/styleL.css">
<%
    Cittadino cittadino = (Cittadino) request.getSession().getAttribute("Cittadino");
%>

<label for="toggle" id="iconToggle" style="position: absolute;">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots-vertical" viewBox="0 0 16 16">
        <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
    </svg>
</label>
<input type="checkbox" id="toggle" style="display: none;">

<div class="col-2 align-self-start height100 boxShadow" id="headerContainer">
    <!-- collapse icon -->


    <div class="row mb-5 justify-content-center mt-1 mt-md-1 mt-lg-5 mt-xl-5 collapsable">
        <button class="roundShape boxShadow" id="profileCircle" class="boldFont" disabled>
            <!-- iniziali nome e cognome -->
            <%if(cittadino!=null){%>
            <%=cittadino.getNome().charAt(0)%>
            <%=cittadino.getCognome().charAt(0)%>
            <%}%>
        </button>
    </div>
    <div class="row ml-3 marginTop150" id="linkHome">
        <a href="guiCittadino" class="noDecoration greyText collapsable">Home</a>
    </div>
    <div class="row ml-3 mt-2" id="linkProfilo">
        <a href="profilo" class="noDecoration greyText collapsable">Profilo</a>
    </div>
    <div class="row ml-3 mt-2">
        <a href="visualizza-segnalazioni" class="noDecoration greyText collapsable">Inoltrate</a>
    </div>
    <div class="row ml-3 mt-2">
        <a href="#" class="noDecoration greyText collapsable">Calendario Raccolta Rifiuti</a>
    </div>
    <div class="row ml-3 mt-2">
        <a href="visualizzaClassifica" class="noDecoration greyText collapsable">Visualizza Classifica</a>
    </div>
    <div class="row ml-3 mb-1 mb-md-1 mb-lg-5 mb-xl-5 mb-xxl-5 mt-2">
        <a href="#" class="greyText collapsable">Sito del comune</a>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-7 col-lg-5 col-xl-5 col-xxl-5 col-7 collapsable">Totali</div>
        <div class="col-md-7 col-lg-5 col-xl-5 col-xxl-5 col-7 myBtnPink mr-3 boxShadow collapsable" style="margin-left: 10px;">
            <!-- segnalazioni totali -->
            <%if(cittadino!=null){%>
            <%=cittadino.getNumSegnalazioni()%>
            <%}%>
        </div>
    </div>
    <hr>
    <div class="row mt-2 justify-content-center collapsable">
        <a id="logoutLink" class="boxShadow" href="logout" style="padding: 6px;">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-left" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0v2z"/>
                <path fill-rule="evenodd" d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"/>
            </svg>
        </a>
    </div>
</div>
