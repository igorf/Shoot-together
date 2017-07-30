<#-- @ftlvariable name="exercises" type="java.lang.Iterable<com.github.igorf.shoot.logic.domain.Competition>" -->
<#-- @ftlvariable name="competition" type="com.github.igorf.shoot.logic.domain.Competition" -->
<#-- @ftlvariable name="isMutable" type="boolean" -->

<#assign selectedExerciseId>${(competition.exercise.id)!-1}</#assign>
<#include '../layout/admin.ftl'>
<@admin_layout title="Edit competition ${(competition.title)!}">

    <script type="text/javascript" src="/bower_components/moment/min/moment.min.js"></script>
    <script type="text/javascript" src="/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />

    <ul class="breadcrumb">
        <li><a href="/admin/competition/list">Competition list</a></li>
        <li class="active">Edit competition</li>
    </ul>

    <div class="well">
        <form class="form-horizontal" method="post" action="/admin/competition/save">
            <input type="hidden" name="id" value="${(competition.id)!}">
            <fieldset>
                <legend>Edit competition ${(competition.title)!}</legend>

                <div class="form-group">
                    <label for="inputTitle" class="col-lg-2 control-label">Title</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputTitle" name="title" value="${(competition.title)!}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputStart" class="col-lg-2 control-label">Starts at</label>
                    <div class="col-lg-10">
                        <input type="text" placeholder="DD.MM.YYYY" class="form-control" id="inputStart" name="start" value="${(competition.start?string["dd.MM.yyyy"])!}">
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            $('#inputStart').datetimepicker({format: 'DD.MM.YYYY'});
                        });
                    </script>
                </div>

                <div class="form-group">
                    <label for="inputFinish" class="col-lg-2 control-label">Finished at</label>
                    <div class="col-lg-10">
                        <input type="text" placeholder="DD.MM.YYYY" class="form-control" id="inputFinish" name="end" value="${(competition.end?string["dd.MM.yyyy"])!}">
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            $('#inputFinish').datetimepicker({format: 'DD.MM.YYYY'});
                        });
                    </script>
                </div>

                <div class="form-group">
                    <label for="selectTarget" class="col-lg-2 control-label">Exercise</label>
                    <div class="col-lg-10">
                        <select class="form-control" id="selectTarget" name="exerciseId">
                        <#list exercises as exercise>
                            <option value="${exercise.id}" <#if selectedExerciseId?number == exercise.id>selected</#if>>${exercise.title}</option>
                        </#list>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <#if isMutable >
                            <button type="submit" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-save"></span>
                                Save
                            </button>
                        </#if>
                        <#if competition ??>
                            <a class="btn btn-sm btn-danger" href="/admin/competition/delete/#{(competition.id)!}">
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
