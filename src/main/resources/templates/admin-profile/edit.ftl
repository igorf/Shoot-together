<#-- @ftlvariable name="profile" type="com.github.igorf.shoot.logic.domain.Profile" -->
<#-- @ftlvariable name="roles" type="java.util.List<com.github.igorf.shoot.logic.domain.Role>" -->
<#import "/spring.ftl" as spring/>

<#include '../layout/admin.ftl'>
<@admin_layout title="Edit profile ${(profile.login)!}">

    <ul class="breadcrumb">
        <li><a href="/admin/profile/list">Profiles</a></li>
        <li class="active">Edit profile</li>
    </ul>

    <form class="form-horizontal" method="post" action="/admin/profile/save" id="profileForm">
        <input type="hidden" name="id" value="${(profile.id)!}">

        <div class="well">
            <fieldset>
                <legend>Edit profile ${(profile.login)!}</legend>

                <div class="form-group">
                    <label for="inputLogin" class="col-lg-2 control-label">Login</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputLogin" name="login" value="${(profile.login)!}">
                    </div>
                    <@spring.bind "profile.login" />
                    <#list spring.status.errorMessages as error>
                        <div class="col-lg-10 col-lg-offset-2 error">
                            <b>${error}</b>
                        </div>
                    </#list>
                </div>

                <div class="form-group">
                    <label for="inputRoles" class="col-lg-2 control-label">Roles</label>
                    <div class="col-lg-10 col-lg-offset-2">
                        <#list roles as role>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="roles" value="${role.id}" <#if profile.hasRole(role.id)>checked</#if>> ${role.name}
                                </label>
                            </div>
                        </#list>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-save"></span>
                            Save
                        </button>
                        <#if profile.id ??>
                            <a class="btn btn-sm btn-danger" href="/admin/profile/delete/#{(profile.id)!}">
                                <span class="glyphicon glyphicon-erase"></span>
                                Delete
                            </a>
                        </#if>
                    </div>
                </div>
            </fieldset>
        </div>

        <div class="well">
            <fieldset>
            <legend>Update password</legend>

            <div class="form-group">
                <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                <div class="col-lg-10">
                    <input type="password" class="form-control" id="inputPassword" name="password" />
                </div>
                <@spring.bind "profile.password" />
                <#list spring.status.errorMessages as error>
                    <div class="col-lg-10 col-lg-offset-2 error">
                        <b>${error}</b>
                    </div>
                </#list>
            </div>

            <div class="form-group">
                <label for="inputPasswordConfirm" class="col-lg-2 control-label">Confirm password</label>
                <div class="col-lg-10">
                    <input type="password" class="form-control" id="inputPasswordConfirm" name="passwordConfirm" />
                </div>
                <@spring.bind "profile.passwordConfirm" />
                <#list spring.status.errorMessages as error>
                    <div class="col-lg-10 col-lg-offset-2 error">
                        <b>${error}</b>
                    </div>
                </#list>
            </div>

            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button class="btn btn-primary btn-sm" onclick="updatePassword()">
                        <span class="glyphicon glyphicon-save"></span>
                        Update
                    </button>
                </div>
            </div>
        </fieldset>
        </div>
    </form>

</@admin_layout>

<script language="JavaScript">
    function updatePassword() {
        $('#profileForm').attr('action', "/admin/profile/update_password").submit();
    }

    function updateRoles() {
        $('#profileForm').attr('action', "/admin/profile/update_roles").submit();
    }
</script>