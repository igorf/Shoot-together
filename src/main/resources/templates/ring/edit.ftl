<#-- @ftlvariable name="target" type="com.github.igorf.shoot.logic.domain.Target" -->
<#-- @ftlvariable name="ring" type="com.github.igorf.shoot.logic.domain.TargetRing" -->

<#include '../layout/admin.ftl'>
<@admin_layout title="Edit target ${(target.title)!}">

    <ul class="breadcrumb">
        <li><a href="/target/list">Target list</a></li>
        <li><a href="/target/view/${(target.id)!}">Edit target ${(target.title)!}</a></li>
        <#if ring ??>
            <li class="active">Edit ring ${ring.denomination}</li>
        <#else>
            <li class="active">Add new ring</li>
        </#if>
    </ul>

    <div class="well">
        <form class="form-horizontal" action="/ring/save" method="post">
            <input type="hidden" name="targetId" value="${(target.id)!"-1"}">
            <input type="hidden" name="ringId" value="${(ring.id)!"-1"}">
            <fieldset>
                <#if ring ??>
                    <legend>Edit ring ${ring.denomination}</legend>
                <#else>
                    <legend>Add new ring</legend>
                </#if>

                <div class="form-group">
                    <label for="inputDenomination" class="col-lg-2 control-label">Denomination</label>
                    <div class="col-lg-10">
                        <input type="text" name="denomination" class="form-control" id="inputDenomination" value="${(ring.denomination)!}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDiameter" class="col-lg-2 control-label">Diameter</label>
                    <div class="col-lg-10">
                        <input type="text" name="diameter" class="form-control" id="inputDiameter" value="${(ring.diameter)!}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="select" class="col-lg-2 control-label">Color</label>
                    <div class="col-lg-10">
                        <select class="form-control" id="selectColor" name="color">
                            <#if ring ??>
                                <option value="0" <#if ring.color == "WHITE">selected</#if>>White</option>
                                <option value="1" <#if ring.color == "BLACK">selected</#if>>Black</option>
                            <#else>
                                <option value="0">White</option>
                                <option value="1">Black</option>
                            </#if>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-save"></span>
                            Save
                        </button>
                        <#if ring ??>
                            <a class="btn btn-sm btn-danger" href="/ring/delete/#{(ring.id)!}">
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
