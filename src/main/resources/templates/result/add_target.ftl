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

<div class="alert alert-info">
    <a class="btn btn-sm btn-danger" onclick="saveTarget();">
        <span class="glyphicon glyphicon-plus"></span>
        Save target result
    </a>
</div>

<script>
    var target = new Target('target', 650, 650);
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

    function saveTarget() {
        $.ajax({
            type: "POST",
            url: "/api/result/my/target/save/${competition.id}",
            data: JSON.stringify(target.getResults()),
            dataType: "json",
            contentType : "application/json",
            success: function() {
                window.location.replace('/competition/result/my/${competition.id}');
            },
            error: function (jqXHR, exception) {
                var msg = '';
                if (jqXHR.status === 0) {
                    msg = 'Not connect.\n Verify Network.';
                } else if (jqXHR.status == 404) {
                    msg = 'Requested page not found. [404]';
                } else if (jqXHR.status == 500) {
                    msg = 'Internal Server Error [500].';
                } else if (exception === 'parsererror') {
                    msg = 'Requested JSON parse failed.';
                } else if (exception === 'timeout') {
                    msg = 'Time out error.';
                } else if (exception === 'abort') {
                    msg = 'Ajax request aborted.';
                } else {
                    msg = 'Uncaught Error.\n' + jqXHR.responseText;
                }
                console.log(msg);
            }
        });
    }
</script>

</@layout>
