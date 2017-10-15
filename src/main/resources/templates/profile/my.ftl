<#-- @ftlvariable name="competitor" type="com.github.igorf.shoot.logic.domain.Competitor" -->
<#import "/spring.ftl" as spring/>

<#include '../layout/app.ftl'>
<@layout title="Edit my profile (${(competitor.profile.login)!})">

    <form class="form-horizontal" method="post" action="/profile/save" id="profileForm">
        <input type="hidden" name="id" value="${(competitor.id)!}">

        <div class="well">
            <fieldset>
                <legend>Edit my profile (${(competitor.profile.login)!})</legend>

                <div class="form-group">
                    <label for="inputFirstname" class="col-lg-2 control-label">Firstname</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputFirstname" name="firstname" value="${(competitor.firstname)!}">
                    </div>
                    <@spring.bind "competitor.firstname" />
                    <#list spring.status.errorMessages as error>
                        <div class="col-lg-10 col-lg-offset-2 error">
                            <b>${error}</b>
                        </div>
                    </#list>
                </div>

                <div class="form-group">
                    <label for="inputLastname" class="col-lg-2 control-label">Lastname</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputLastname" name="lastname" value="${(competitor.lastname)!}">
                    </div>
                    <@spring.bind "competitor.lastname" />
                    <#list spring.status.errorMessages as error>
                        <div class="col-lg-10 col-lg-offset-2 error">
                            <b>${error}</b>
                        </div>
                    </#list>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-save"></span>
                            Save
                        </button>
                    </div>
                </div>
            </fieldset>
        </div>
    </form>

</@layout>