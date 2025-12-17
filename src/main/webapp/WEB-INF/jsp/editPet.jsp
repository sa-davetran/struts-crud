<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="pet==null || pet.petId == null">
    <s:set var="title" value="%{'Add new pet'}"/>
</s:if>
<s:else>
    <s:set var="title" value="%{'Update pet'}"/>
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
        <s:form action="savePet" method="post">
            <s:textfield name="pet.name" label="Name" /> 
            <s:select name="pet.species" list="species" label="Species" />
            <s:textfield name="pet.age" label="Age">
                <s:param name="type">number</s:param>
                <s:param name="pattern">\\d+</s:param>
                <s:param name="step">1</s:param>
                <s:param name="min">1</s:param>
                <s:param name="max">20</s:param>
            </s:textfield>
            
            <s:hidden name="pet.petId" value="%{pet.petId}"/>

            <s:submit value="Submit"/>
            <s:submit value="Cancel" action="listPet"/>
        </s:form>
    </body>
</html>
