package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class License
{
    public String key;
    public String name;
    public String spdx_id;
    public String url;
    public String node_id;
}
