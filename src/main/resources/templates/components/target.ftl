<#-- @ftlvariable name="target" type="com.github.igorf.shoot.logic.domain.Target" -->
<script language="JavaScript" src="/js/konva.min.js"></script>
<script language="JavaScript" src="/js/components/Target.js"></script>
<div id="target_${target.id}"></div>
<script>
    let target = new Target('target_${target.id}', 150, 150);
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
