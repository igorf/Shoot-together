<#-- @ftlvariable name="competitionResult" type="com.github.igorf.shoot.logic.domain.CompetitionResult" -->

<#include '../layout/app.ftl'>
<script language="JavaScript" src="/js/konva.min.js"></script>
<script language="JavaScript" src="/js/components/Target.js"></script>

<@layout title="${competitionResult.competition.title}">
<h2>My results ${competitionResult.competition.title}</h2>

<ul class="breadcrumb">
    <li><a href="/competition/list">Competitions</a></li>
    <li><a href="/competition/view/${competitionResult.competition.id}">${competitionResult.competition.title}</a></li>
    <li class="active">${competitionResult.competitor.firstname} ${competitionResult.competitor.lastname}</li>
</ul>

<h2>Total results</h2>
<div class="alert alert-info">
    <div class="row">
        <div class="col-lg-3" id="total_result"></div>
        <div class="col-lg-offset-4">
            <h1>Name: ${competitionResult.competitor.firstname} ${competitionResult.competitor.lastname}</h1>
            <h2>
                Series:
                <#list competitionResult.shotSeries as serie>
                 ${serie}
                </#list>
            </h2>
        </div>
    </div>
</div>

<hr />
<h2>Targets</h2>

 <#if (competitionResult.targets) ??>
  <#list competitionResult.targets as cTarget>
   <div class="alert alert-success">
       <div class="row">
           <div class="col-lg-3" id="target_${cTarget.id}"></div>
           <div class="col-lg-offset-4">
               <h2>
                   Shots:&nbsp;
        <#list cTarget.shots as shot>
         ${shot.result}&nbsp;
        </#list>
               </h2>
           </div>
       </div>
   </div>
  </#list>
 </#if>

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

<#if (competitionResult.shots) ??>
   target = new Target('total_result', 250, 250);
   target.minRing = ${competitionResult.getMinimumVisibleRing()}
           rings = [];
   shots = [];

 <#if (competitionResult.competition.exercise.target.rings) ??>
  <#list competitionResult.competition.exercise.target.rings as ring>
            rings.push(new TargetCircle(${ring.diameter?c}, "${ring.color}", ${ring.denomination}));
  </#list>
 </#if>

 <#if (competitionResult.getShots()) ??>
  <#list competitionResult.getShots() as shot>
            shots.push(new Shot(${competitionResult.competition.exercise.caliber?c}, ${shot.result?c}, ${shot.x?c}, ${shot.y?c}));
  </#list>
 </#if>

    for (let targetRing of rings) {
        target.addCircle(targetRing);
    }
    for (let targetShot of shots) {
        target.addShot(targetShot);
    }
    target.draw();
</#if>
</script>

</@layout>