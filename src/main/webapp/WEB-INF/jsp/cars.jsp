<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <s:head />
        <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <title>Cars</title>
    </head>
    <body>
        <div class="titleDiv"><s:text name="application.title"/></div>
        <h1>Cars</h1>
        <s:url var="url" action="inputCar" />
        <a href="<s:property value="#url"/>">Add New Car</a>
        &nbsp;&nbsp;&nbsp;
        <s:url var="personUrl" action="index" />
        <a href="<s:property value="#personUrl"/>">Back to Persons</a>
        &nbsp;&nbsp;&nbsp;
        <s:url var="petUrl" action="listPet" />
        <a href="<s:property value="#petUrl"/>">View Pets</a>
        <br/><br/>
        <table class="borderAll">
            <tr>
                <th>Model</th>
                <th>Manufacturer</th>
                <th>Year</th>
                <th>Color</th>
                <th>&nbsp;</th>
            </tr>
            <s:iterator value="cars" status="status">
                <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
                    <td class="nowrap"><s:property value="model"/></td>
                    <td class="nowrap"><s:property value="manufacturer"/></td>
                    <td class="nowrap"><s:property value="year"/></td>
                    <td class="nowrap" style="background-color: <s:property value='color'/>;"></td>
                    <td class="nowrap">
                        <s:url action="inputCar" var="url">
                            <s:param name="car.carId" value="carId"/>
                        </s:url>
                        <a href="<s:property value="#url"/>">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <s:url action="deleteCar" var="url">
                            <s:param name="car.carId" value="carId"/>
                        </s:url>
                        <a href="<s:property value="#url"/>">Delete</a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
