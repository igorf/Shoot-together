<#-- @ftlvariable name="exercise" type="com.github.igorf.shoot.logic.domain.Exercise" -->
<#-- @ftlvariable name="targets" type="java.lang.Iterable<com.github.igorf.shoot.logic.domain.Target>" -->
<#assign selectedTargetId>${(exercise.target.id)!-1}</#assign>
<#include '../layout/admin.ftl'>
<@admin_layout title="Edit exercise ${(exercise.title)!}">

    <ul class="breadcrumb">
        <li><a href="/admin/exercise/list">Exercise list</a></li>
        <li class="active">Edit exercise</li>
    </ul>

    <div class="well">
        <form class="form-horizontal" method="post" action="/admin/exercise/save">
            <input type="hidden" name="id" value="${(exercise.id)!}">
            <fieldset>
                <legend>Edit exercise ${(exercise.title)!}</legend>
                <div class="form-group">
                    <label for="inputTitle" class="col-lg-2 control-label">Title</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputTitle" name="title" value="${(exercise.title)!}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputShots" class="col-lg-2 control-label">Shots number</label>
                    <div class="col-lg-10">
                        <input type="number" step="any" class="form-control" id="inputShots" name="shots" value="${(exercise.shots)!}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputTimeLimit" class="col-lg-2 control-label">Time limit</label>
                    <div class="col-lg-10">
                        <input type="number" step="any" class="form-control" id="inputTimeLimit" name="timeLimit" value="${(exercise.timeLimit)!}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputCaliber" class="col-lg-2 control-label">Caliber, mm</label>
                    <div class="col-lg-10">
                        <input type="number" step="any" class="form-control" id="inputCaliber" name="caliber" value="${((exercise.caliber)!0)?string.computer}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDistance" class="col-lg-2 control-label">Distance, m</label>
                    <div class="col-lg-10">
                        <input type="number" step="any" class="form-control" id="inputDistance" name="distance" value="${((exercise.distance)!0)?string.computer}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputShotsPerTarget" class="col-lg-2 control-label">Shots per target</label>
                    <div class="col-lg-10">
                        <input type="number" step="any" class="form-control" id="inputShotsPerTarget" name="shotsPerTarget" value="${((exercise.shotsPerTarget)!0)?string.computer}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputShotsPerSeries" class="col-lg-2 control-label">Shots per series</label>
                    <div class="col-lg-10">
                        <input type="number" step="any" class="form-control" id="inputShotsPerSeries" name="shotsPerSeries" value="${((exercise.shotsPerSeries)!0)?string.computer}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="selectTarget" class="col-lg-2 control-label">Target</label>
                    <div class="col-lg-10">
                        <select class="form-control" id="selectTarget" name="targetId">
                        <#list targets as target>
                            <option value="${target.id}" <#if selectedTargetId?number == target.id>selected</#if>>${target.title}</option>
                        </#list>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="descriptionArea" class="col-lg-2 control-label">Description</label>
                    <div class="col-lg-10">
                        <textarea class="form-control" rows="5" id="descriptionArea" name="description">${(exercise.description)!}</textarea>
                        <span class="help-block">Description of the exercise.</span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-save"></span>
                            Save
                        </button>
                        <#if exercise ??>
                            <a class="btn btn-sm btn-danger" href="/admin/exercise/delete/#{(exercise.id)!}">
                                <span class="glyphicon glyphicon-erase"></span>
                                Delete
                            </a>
                        </#if>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</@admin_layout>
