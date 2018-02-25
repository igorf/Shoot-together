<#-- @ftlvariable name="competition" type="com.github.igorf.shoot.logic.domain.Competition" -->
<#include '../layout/app.ftl'>

<script language="JavaScript" src="/js/konva.min.js"></script>
<script language="JavaScript" src="/js/components/Target.js"></script>

<@layout title="${competition.title}: add target">
<h2>${competition.title}: add target</h2>

<ul class="breadcrumb">
    <li><a href="/competition/list">Competitions</a></li>
    <li><a href="/competition/view/${competition.id}">${competition.title}</a></li>
    <li><a href="/competition/result/my/${competition.id}">My results</a></li>
    <li class="active">Add target</li>
</ul>

<div id="target"></div>
<script>
    let target = new Target('target', 650, 650);
    let rings = [];
    let shots = [];
    <#if (competition.exercise.target.rings) ??>
     <#list competition.exercise.target.rings as ring>
            rings.push(new TargetCircle(${ring.diameter?c}, "${ring.color}", ${ring.denomination}));
     </#list>
    </#if>
    for (let targetRing of rings) {
        target.addCircle(targetRing);
    }

    <#list 0..<competition.exercise.shotsPerTarget as i>
      <#assign y =
       competition.exercise.caliber * 2 -
       (competition.exercise.target.getDiameter() / 2) +
       (competition.exercise.caliber * 1.5) * i
      >
      shots.push(new Shot(${competition.exercise.caliber?c}, 0, ${(0 - competition.exercise.caliber * 2 - (competition.exercise.target.getDiameter() / 2))?c}, ${y?c}));
    </#list>

    for (let targetShot of shots) {
        target.addShot(targetShot);
    }

    target.editable = true;
    target.draw();
</script>

</@layout>
