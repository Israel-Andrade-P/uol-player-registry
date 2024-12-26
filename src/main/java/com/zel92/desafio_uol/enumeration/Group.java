package com.zel92.desafio_uol.enumeration;

public enum Group {
    AVENGERS("Avengers", "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json"),
    LEAGUE_OF_JUSTICE("League of Justice", "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml");

    private final String value;
    private final String uri;

    Group(String value, String uri) {
        this.value = value;
        this.uri = uri;
    }

    public String getValue() {
        return value;
    }

    public String getUri() {
        return uri;
    }
}
