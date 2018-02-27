<#-- @ftlvariable name="competitionResult" type="com.github.igorf.shoot.logic.domain.CompetitionResult" -->

<#include '../layout/app.ftl'>
<script language="JavaScript" src="/js/konva.min.js"></script>
<script language="JavaScript" src="/js/components/Target.js"></script>

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
                <div id="target_${cTarget.id}"></div>
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

<script>
    let rings = [];
    let shots = [];
    let target = null;
<#if (competitionResult.targets) ??>
    <#list competitionResult.targets as cTarget>
        target = new Target('target_${cTarget.id}', 250, 250);
        target.minRing = ${cTarget.getMinimumVisibleRing()}
        rings = [];
        shots = [];

        <#if (cTarget.competitionResult.competition.exercise.target.rings) ??>
            <#list cTarget.competitionResult.competition.exercise.target.rings as ring>
            rings.push(new TargetCircle(${ring.diameter?c}, "${ring.color}", ${ring.denomination}));
            </#list>
        </#if>

        <#if (cTarget.shots) ??>
            <#list cTarget.shots as shot>
            shots.push(new Shot(${cTarget.competitionResult.competition.exercise.caliber?c}, ${shot.result?c}, ${shot.x?c}, ${shot.y?c}));
            </#list>
        </#if>

        for (let targetRing of rings) {
            target.addCircle(targetRing);
        }
        for (let targetShot of shots) {
            target.addShot(targetShot);
        }
        target.draw();
    </#list>
</#if>
</script>

</@layout>
