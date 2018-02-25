<#-- @ftlvariable name="competition" type="com.github.igorf.shoot.logic.domain.Competition" -->

<#include '../layout/app.ftl'>

<@layout title="${competition.title}">
<h2>${competition.title}</h2>

<ul class="breadcrumb">
    <li><a href="/competition/list">Competitions</a></li>
    <li class="active">${competition.title}</li>
</ul>

<div class="alert alert-info">
    <a href="/competition/result/my/${competition.id}">My result</a>
</div>

<table class="table table-hover">
  <tbody>
 <#if (competition.results) ??>
  <#list competition.results as cResult>
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
