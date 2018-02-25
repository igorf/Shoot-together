<#-- @ftlvariable name="result" type="com.github.igorf.shoot.logic.domain.CompetitionResult" -->
<script language="JavaScript" src="/js/konva.min.js"></script>
<script language="JavaScript" src="/js/components/Target.js"></script>
<div id="target_${result.id}"></div>
<script>
    let target = new Target('target_${result.id}', 150, 150);
    let rings = [];
    let shots = [];

    <#if (result.competition.exercise.target.rings) ??>
        <#list result.competition.exercise.target.rings as ring>
            rings.push(new TargetCircle(${ring.diameter?c}, "${ring.color}", ${ring.denomination}));
        </#list>
    </#if>

    <#if (result.getShots()) ??>
       <#list result.getShots() as shot>
            shots.push(new Shot(${result.competition.exercise.caliber}, ${shot.result}, ${shot.x}, ${shot.y}));
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
