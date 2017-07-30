<#-- @ftlvariable name="exercises" type="java.lang.Iterable<com.github.igorf.shoot.logic.domain.Competition>" -->
<#-- @ftlvariable name="competition" type="com.github.igorf.shoot.logic.domain.Competition" -->
<#-- @ftlvariable name="isMutable" type="boolean" -->

<#include '../layout/admin.ftl'>
<@admin_layout title="Add competitions">
    <script type="text/javascript" src="/bower_components/moment/min/moment.min.js"></script>
    <script type="text/javascript" src="/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />

    <ul class="breadcrumb">
        <li><a href="/admin/competition/list">Competition list</a></li>
        <li class="active">Add competitions</li>
    </ul>

    <div class="well">
        <form class="form-horizontal" method="post" action="/admin/competition/multisave">
            <fieldset>
                <legend>Add competitions</legend>

                <div class="form-group">
                    <label for="inputTitle" class="col-lg-2 control-label">Title</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputTitle" name="title">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputStart" class="col-lg-2 control-label">Starts at</label>
                    <div class="col-lg-10">
                        <input type="text" placeholder="DD.MM.YYYY" class="form-control" id="inputStart" name="start">
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
                        <input type="text" placeholder="DD.MM.YYYY" class="form-control" id="inputFinish" name="end">
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            $('#inputFinish').datetimepicker({format: 'DD.MM.YYYY'});
                        });
                    </script>
                </div>

                <div class="form-group">
                    <label for="selectTarget" class="col-lg-2 control-label">Exercises</label>
                    <div class="col-lg-10">
                        <select class="form-control" id="selectExercise" name="exerciseId[]" multiple="" class="form-control">
                        <#list exercises as exercise>
                            <option value="${exercise.id}">${exercise.title}</option>
                        </#list>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-save"></span>
                            Save competitions
                        </button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</@admin_layout>
