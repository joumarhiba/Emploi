package com.emploi.helpers;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor

public class AuthenticationRequest {

    private final String email;
    private final String password;
    private final String userRole;

}
