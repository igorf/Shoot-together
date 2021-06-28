<#-- @ftlvariable name="competitions" type="org.springframework.data.domain.Page<com.github.igorf.shoot.logic.domain.Competition>" -->

<#include '../layout/app.ftl'>

<@layout title="Competitions">
<h2>Competitions</h2>

<table class="table table-hover table-bordered table-striped">
    <tr>
        <th>Title</th>
        <th>Exercise</th>
        <th>Stared at</th>
        <th>Finished at</th>
        <th>Status</th>
    </tr>
    <#list competitions.content as competition>
        <tr>
            <td><a href="/competition/view/${(competition.id)!}">${competition.title}</a></td>
            <td>${competition.exercise.title}</td>
            <td>${competition.start?string["dd.MM.yyyy"]}</td>
            <td>${competition.end?string["dd.MM.yyyy"]}</td>
            <td>
             <#if competition.status == "PLANNED">Not started yet</#if>
             <#if competition.status == "FINISHED">Finished</#if>
             <#if competition.status == "ACTIVE">In progress</#if>
            </td>
        </tr>
    </#list>
</table>

<#if competitions.hasPrevious() || competitions.hasNext()>
<ul class="pager">
    <#if competitions.hasPrevious()>
        <a href="/competitions/list?page=${competitions.number - 1}">&larr; Previous</a>
    </#if>
    <#if competitions.hasNext()>
        <a href="/competitions/list?page=${competitions.number + 1}">Next &rarr; </a>
    </#if>
</ul>
</#if>

</@layout>
