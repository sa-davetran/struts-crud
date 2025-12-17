<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <s:head />
        <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <title>Pets</title>
    </head>
    <body>
        <div class="titleDiv"><s:text name="application.title"/></div>
        <h1>Pets</h1>
        <s:url var="url" action="inputPet" />
        <a href="<s:property value="#url"/>">Add New Pet</a>
        &nbsp;&nbsp;&nbsp;
        <s:url var="personUrl" action="index" />
        <a href="<s:property value="#personUrl"/>">Back to Persons</a>
        &nbsp;&nbsp;&nbsp;
        <s:url var="carUrl" action="listCar" />
        <a href="<s:property value="#carUrl"/>">View Cars</a>
        <br/><br/>
        <table class="borderAll">
            <tr>
                <th>Name</th>
                <th>Species</th>
                <th>Age</th>
                <th>&nbsp;</th>
            </tr>
            <s:iterator value="pets" status="status">
                <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
                    <td class="nowrap"><s:property value="name"/></td>
                    <td class="nowrap"><s:property value="species"/></td>
                    <td class="nowrap"><s:property value="age"/></td>
                    <td class="nowrap">
                        <s:url action="inputPet" var="url">
                            <s:param name="pet.petId" value="petId"/>
                        </s:url>
                        <a href="<s:property value="#url"/>">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <s:url action="deletePet" var="url">
                            <s:param name="pet.petId" value="petId"/>
                        </s:url>
                        <a href="<s:property value="#url"/>">Delete</a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
