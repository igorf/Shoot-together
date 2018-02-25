<#-- @ftlvariable name="competitionResult" type="com.github.igorf.shoot.logic.domain.CompetitionResult" -->

<#include '../layout/app.ftl'>

<@layout title="${competitionResult.competition.title}">
<h2>My results ${competitionResult.competition.title}</h2>

<ul class="breadcrumb">
    <li><a href="/competition/list">Competitions</a></li>
    <li><a href="/competition/view/${competitionResult.competition.id}">${competitionResult.competition.title}</a></li>
    <li class="active">My results</li>
</ul>

<div class="row">
 <#if (competitionResult.targets) ??>
  <#list competitionResult.targets as cTarget>
    <div class="col-lg-4">
        <div class="bs-component">
            <div class="alert alert-success">
               <#assign competitorTarget = cTarget>
               <#include "../components/result_simple_target.ftl">
            </div>
        </div>
    </div>
  </#list>
 </#if>
 <#if (competitionResult.needMoreTargets()) >
    <div class="col-lg-4">
        <div class="bs-component">
            <div class="alert alert-info">
                <a href="/competition/result/my/target/add/${competitionResult.competition.id}">Add target</a>
            </div>
        </div>
    </div>
 </#if>
</div>

</@layout>
