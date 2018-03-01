<#-- @ftlvariable name="competition" type="com.github.igorf.shoot.logic.domain.Competition" -->
<#-- @ftlvariable name="myResult" type="com.github.igorf.shoot.logic.domain.CompetitionResult" -->

<#include '../layout/app.ftl'>

<@layout title="${competition.title}">
<h2>${competition.title}</h2>

<ul class="breadcrumb">
    <li class="breadcrumb-item"><a href="/competition/list">Competitions</a></li>
    <li class="breadcrumb-item active">${competition.title}</li>
</ul>

<@security.authorize access="hasRole('ROLE_COMPETITOR')">
<#if myResult.isEditable()>
<div class="alert alert-info">
    <a href="/competition/result/my/${competition.id}">Edit my result</a>
</div>
</#if>
</@security.authorize>

<table class="table table-hover">
  <tbody>
 <#if (competition.visibleResults) ??>
  <#list competition.visibleResults as cResult>
    <tr>
        <td>
            <#assign result = cResult>
            <#include "../components/result_target.ftl">
        </td>
        <td>
            ${result.competitor.firstname} ${result.competitor.lastname}
            ${result.result}
        </td>
    </tr>
  </#list>
 </#if>
  </tbody>
</table>
</@layout>
