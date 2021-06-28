<#-- @ftlvariable name="competitor" type="com.github.igorf.shoot.logic.domain.Competitor" -->
<#import "/spring.ftl" as spring/>

<#include '../layout/app.ftl'>
<@layout title="Edit my profile (${(competitor.profile.login)!})">

    <form method="post" action="/profile/save" id="profileForm">
        <input type="hidden" name="id" value="${(competitor.id)!}">
        <fieldset>
            <legend>Edit my profile (${(competitor.profile.login)!})</legend>

            <div class="form-group">
                <label for="inputFirstname" class="mt-4 control-label">Firstname</label>
                <input type="text" class="form-control" id="inputFirstname" name="firstname" value="${(competitor.firstname)!}">
                <@spring.bind "competitor.firstname" />
                <#list spring.status.errorMessages as error>
                    <div class="col-lg-10 col-lg-offset-2 error">
                        <b>${error}</b>
                    </div>
                </#list>
            </div>

            <div class="form-group">
                <label for="inputLastname" class="mt-4 control-label">Lastname</label>
                <input type="text" class="form-control" id="inputLastname" name="lastname" value="${(competitor.lastname)!}">
                <@spring.bind "competitor.lastname" />
                <#list spring.status.errorMessages as error>
                    <div class="col-lg-10 col-lg-offset-2 error">
                        <b>${error}</b>
                    </div>
                </#list>
            </div>

            <button type="submit" class="glyphicon glyphicon-save btn btn-primary form-submit">Save</button>
        </fieldset>
    </form>

</@layout>