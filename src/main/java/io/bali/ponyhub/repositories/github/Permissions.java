package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Permissions
{
    public boolean admin;
    public boolean push;
    public boolean pull;
    public boolean maintain;
    public boolean triage;
}
