/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author ADMIN
 */


//@CustomFormAuthenticationMechanismDefinition(
//loginToContinue = @LoginToContinue(
//        loginPage = "/faces/login.xhtml"
//))
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/doctz_pool",
        callerQuery = "select password from user_tb where email=?",
        groupsQuery = "select g.groupName from group_tb g,user_tb u,usergroup_tb ug where ug.userId=u.userId and ug.groupId=g.groupId and u.email=?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30
)
@Named
@ApplicationScoped
public class appconfig {
    
}
