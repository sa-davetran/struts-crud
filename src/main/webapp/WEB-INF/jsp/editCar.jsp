<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="car==null || car.carId == null">
    <s:set var="title" value="%{'Add new car'}"/>
</s:if>
<s:else>
    <s:set var="title" value="%{'Update car'}"/>
</s:else>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <s:head />
        <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <style>td { white-space:nowrap; }</style>
        <title><s:property value="#title"/></title>
    </head>
    <body>
        <div class="titleDiv"><s:text name="application.title"/></div>
        <h1><s:property value="#title"/></h1>
        <s:actionerror />
        <s:actionmessage />
        <s:form action="saveCar" method="post">
            <s:textfield name="car.model" label="Model" /> 
            <s:select name="car.manufacturer" list="manufacturers" label="Manufacturer" />
            <s:textfield name="car.year" label="Year" />
            <s:select name="car.color" list="colors" label="Color" />
            
            <s:hidden name="car.carId" value="%{car.carId}"/>

            <s:submit value="Submit"/>
            <s:submit value="Cancel" action="listCar"/>
        </s:form>
    </body>
</html>
