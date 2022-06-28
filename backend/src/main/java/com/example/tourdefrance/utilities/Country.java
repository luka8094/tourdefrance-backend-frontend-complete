package com.example.tourdefrance.utilities;

// Inspireret af disse kilder:
// https://stackoverflow.com/questions/17741721/getting-string-value-from-enum-in-java
// https://howtodoinjava.com/java/enum/java-enum-string-example/
// Enum klasse til at klassificere de enkelte rytter's land - ved forkortelse
public enum Country {
    DENMARK("dk"),
    FRANCE("fr"),
    GERMANY("de"),
    POLAND("pl"),
    SWEDEN("se"),
    BELGIUM("bl"),
    CZECH_REPUBLIC("cz"),
    NORWAY("nr"),
    SPAIN("es"),
    SIBERIA("si"),
    NEW_ZEALAND("nz"),
    SCHWEIZ("ch"),
    USA("us"),
    NETHERLANDS("nl"),
    CANADA("ca"),
    GREAT_BRITAIN("gb"),
    AUSTRALIA("au"),
    ISRAEL("il");

    private String name;

    Country(String countryName) {
        this.name = countryName;
    }

    public String getCountryAbbrivation(){
        return name;
    }
}
