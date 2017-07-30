<#-- @ftlvariable name="target" type="com.github.igorf.shoot.logic.domain.Target" -->
<script language="JavaScript" src="/js/components/Target.js"></script>
<canvas id="target_${target.id}" width="150" height="150">
    Your browser doesn't support canvas!
</canvas>
<script>
    let target = new Target(document.getElementById('target_${target.id}'));
    let rings = [];
    <#if (target.rings) ??>
        <#list target.rings as ring>
            rings.push(new TargetCircle(${ring.diameter?c}, "${ring.color}", ${ring.denomination}));
        </#list>
    </#if>
    for (let targetRing of rings) {
        target.addCircle(targetRing);
    }
    target.draw();
</script>
