<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="person==null || person.personId == null">
    <s:set var="title" value="%{'Add new person'}"/>
</s:if>
<s:else>
    <s:set var="title" value="%{'Update person'}"/>
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
        <div class="titleDiv">
            <a href="<s:url value='/'/>" class="home-button" style="margin-left:20px;">Home</a>
            <s:text name="application.title"/>
        </div>
        <h1><s:property value="#title"/></h1>
        <s:actionerror />
        <s:actionmessage />
        <s:form action="savePerson" method="post">
            <s:textfield key="person.firstName" /> 
            <s:textfield key="person.lastName" /> 
            <s:textfield key="person.email" />
            <s:textfield key="person.phoneNumber" />
            <s:select key="person.sport" list="sports" />
            <s:radio key="person.gender" list="genders" />
            <s:select name="person.country.countryId" list="countries" listKey="countryId" listValue="countryName" label="%{getText('person.country')}"/>
            <s:textfield key="person.yearOfBirth" label="Year of Birth">
                <s:param name="type">number</s:param>
                <s:param name="pattern">\\d+</s:param>
                <s:param name="step">1</s:param>
            </s:textfield>
            
            <tr>
                <td class="tdLabel" style="vertical-align: top;"><label for="person_carModels" class="label"><s:text name="person.carModels"/>:</label></td>
                <td>
                    <div style="display: inline-block; vertical-align: top;">
                        <div id="person_carModels">
                            <s:iterator value="carModelsAvailable" var="carModel" status="stat">
                                <div style="margin-bottom: 5px;">
                                    <s:set var="isSelected" value="false" />
                                    <s:if test="person != null && person.carModels != null">
                                        <s:iterator value="person.carModels" var="selectedCar">
                                            <s:if test="#selectedCar.carId == #carModel.carId">
                                                <s:set var="isSelected" value="true" />
                                            </s:if>
                                        </s:iterator>
                                    </s:if>
                                    <input type="checkbox" name="selectedCarIds" value="<s:property value='#carModel.carId'/>" 
                                           id="person_carModels-<s:property value="#stat.index"/>"
                                           <s:if test="#isSelected == true">checked="checked"</s:if> />
                                    <label for="person_carModels-<s:property value="#stat.index"/>"><s:property value="#carModel.manufacturer"/> <s:property value="#carModel.model"/></label>
                                </div>
                            </s:iterator>
                        </div>
                    </div>
                    <s:url var="carsUrl" action="listCar" />
                    <a href="<s:property value="#carsUrl"/>" style="margin-left: 20px; padding: 5px 15px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 4px; display: inline-block; font-size: 0.9em; vertical-align: top;">Update Cars</a>
                </td>
            </tr>
            
            <tr>
                <td class="tdLabel" style="vertical-align: top;"><label for="person_petModels" class="label"><s:text name="person.petModels"/>:</label></td>
                <td>
                    <div style="display: inline-block; vertical-align: top;">
                        <div id="person_petModels">
                            <s:iterator value="petModelsAvailable" var="petModel" status="stat">
                                <div style="margin-bottom: 5px;">
                                    <s:set var="isSelected" value="false" />
                                    <s:if test="person != null && person.petModels != null">
                                        <s:iterator value="person.petModels" var="selectedPet">
                                            <s:if test="#selectedPet.petId == #petModel.petId">
                                                <s:set var="isSelected" value="true" />
                                            </s:if>
                                        </s:iterator>
                                    </s:if>
                                    <input type="checkbox" name="selectedPetIds" value="<s:property value='#petModel.petId'/>" 
                                           id="person_petModels-<s:property value="#stat.index"/>"
                                           <s:if test="#isSelected == true">checked="checked"</s:if> />
                                    <label for="person_petModels-<s:property value="#stat.index"/>"><s:property value="#petModel.name"/> (<s:property value="#petModel.species"/>)</label>
                                </div>
                            </s:iterator>
                        </div>
                    </div>
                    <s:url var="petsUrl" action="listPet" />
                    <a href="<s:property value="#petsUrl"/>" style="margin-left: 20px; padding: 5px 15px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 4px; display: inline-block; font-size: 0.9em; vertical-align: top;">Update Pets</a>
                </td>
            </tr>
            
            <s:hidden name="person.personId" value="%{person.personId}"/>

            <s:submit value="%{getText('button.label.submit')}"/>
            <s:submit value="%{getText('button.label.cancel')}" action="index"/>
        </s:form>
    </body>
</html>

