package com.movierec.userservice.Utils;

import com.movierec.userservice.Entity.User;
import org.keycloak .OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KeycloakUtil {
    private static CredentialRepresentation createPassword(String password){
        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setTemporary(false);
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(password);
        return passwordCredential;
    }
    private Keycloak keycloakGetInstance(){
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:9090/auth")
                .realm("microservice-realm")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("movie-recommendation")
                .clientSecret("e1Mae4KkANnAu0MIyEwAo0KL0hW9mBzl")
                .build();
    }

    public int addUser(User user){
        CredentialRepresentation credential = createPassword(user.getPassword());

        org.keycloak.representations.idm.UserRepresentation representation = new UserRepresentation();
        representation.setEnabled(true);
        representation.setUsername(user.getUsername());
        representation.setFirstName(user.getFullname());
        representation.setCredentials(Collections.singletonList(credential));
        representation.setEmailVerified(false);

        UsersResource usersResource = keycloakGetInstance().realm("microservice-realm").users();
//
//        Response response = usersResource.create(representation);
//        return (response.getStatus());
        return 1;
    }
}
