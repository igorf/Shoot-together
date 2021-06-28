<#-- @ftlvariable name="competitorTarget" type="com.github.igorf.shoot.logic.domain.CompetitorTarget" -->
<script language="JavaScript" src="/js/konva.min.js"></script>
<script language="JavaScript" src="/js/components/Target.js"></script>
<div id="target_${competitorTarget.id}"></div>
<script>
    let target = new Target('target_${competitorTarget.id}', 150, 150);
    let rings = [];
    let shots = [];

    <#if (competitorTarget.competitionResult.competition.exercise.target.rings) ??>
        <#list competitorTarget.competitionResult.competition.exercise.target.rings as ring>
            rings.push(new TargetCircle(${ring.diameter?c}, "${ring.color}", ${ring.denomination}));
        </#list>
    </#if>

    <#if (competitorTarget.shots) ??>
       <#list competitorTarget.shots as shot>
            shots.push(new Shot(${competitorTarget.competitionResult.competition.exercise.caliber}, ${shot.result}, ${shot.x}, ${shot.y}));
       </#list>
    </#if>

    for (let targetRing of rings) {
        target.addCircle(targetRing);
    }
    for (let targetShot of shots) {
        target.addShot(targetShot);
    }
    target.draw();
</script>
