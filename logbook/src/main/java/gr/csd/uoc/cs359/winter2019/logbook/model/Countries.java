package gr.csd.uoc.cs359.winter2019.logbook.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A Map of country code (2 letters) -> full country name.
 */
public final class Countries {
    private static final Map<String, String> COUNTRIES = createMap();

    private Countries() {}

    private static Map<String, String> createMap() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("AF","Afghanistan");
        result.put("AX","Åland Islands");
        result.put("AL","Albania");
        result.put("DZ","Algeria");
        result.put("AS","American Samoa");
        result.put("AD","Andorra");
        result.put("AO","Angola");
        result.put("AI","Anguilla");
        result.put("AQ","Antarctica");
        result.put("AG","Antigua and Barbuda");
        result.put("AR","Argentina");
        result.put("AM","Armenia");
        result.put("AW","Aruba");
        result.put("AU","Australia");
        result.put("AT","Austria");
        result.put("AZ","Azerbaijan");
        result.put("BS","Bahamas");
        result.put("BH","Bahrain");
        result.put("BD","Bangladesh");
        result.put("BB","Barbados");
        result.put("BY","Belarus");
        result.put("BE","Belgium");
        result.put("BZ","Belize");
        result.put("BJ","Benin");
        result.put("BM","Bermuda");
        result.put("BT","Bhutan");
        result.put("BO","Bolivia, Plurinational State of");
        result.put("BQ","Bonaire, Sint Eustatius and Saba");
        result.put("BA","Bosnia and Herzegovina");
        result.put("BW","Botswana");
        result.put("BV","Bouvet Island");
        result.put("BR","Brazil");
        result.put("IO","British Indian Ocean Territory");
        result.put("BN","Brunei Darussalam");
        result.put("BG","Bulgaria");
        result.put("BF","Burkina Faso");
        result.put("BI","Burundi");
        result.put("KH","Cambodia");
        result.put("CM","Cameroon");
        result.put("CA","Canada");
        result.put("CV","Cape Verde");
        result.put("KY","Cayman Islands");
        result.put("CF","Central African Republic");
        result.put("TD","Chad");
        result.put("CL","Chile");
        result.put("CN","China");
        result.put("CX","Christmas Island");
        result.put("CC","Cocos (Keeling) Islands");
        result.put("CO","Colombia");
        result.put("KM","Comoros");
        result.put("CG","Congo");
        result.put("CD","Congo, the Democratic Republic of the");
        result.put("CK","Cook Islands");
        result.put("CR","Costa Rica");
        result.put("CI","Côte d'Ivoire");
        result.put("HR","Croatia");
        result.put("CU","Cuba");
        result.put("CW","Curaçao");
        result.put("CY","Cyprus");
        result.put("CZ","Czech Republic");
        result.put("DK","Denmark");
        result.put("DJ","Djibouti");
        result.put("DM","Dominica");
        result.put("DO","Dominican Republic");
        result.put("EC","Ecuador");
        result.put("EG","Egypt");
        result.put("SV","El Salvador");
        result.put("GQ","Equatorial Guinea");
        result.put("ER","Eritrea");
        result.put("EE","Estonia");
        result.put("ET","Ethiopia");
        result.put("FK","Falkland Islands (Malvinas)");
        result.put("FO","Faroe Islands");
        result.put("FJ","Fiji");
        result.put("FI","Finland");
        result.put("FR","France");
        result.put("GF","French Guiana");
        result.put("PF","French Polynesia");
        result.put("TF","French Southern Territories");
        result.put("GA","Gabon");
        result.put("GM","Gambia");
        result.put("GE","Georgia");
        result.put("DE","Germany");
        result.put("GH","Ghana");
        result.put("GI","Gibraltar");
        result.put("GR","Greece");
        result.put("GL","Greenland");
        result.put("GD","Grenada");
        result.put("GP","Guadeloupe");
        result.put("GU","Guam");
        result.put("GT","Guatemala");
        result.put("GG","Guernsey");
        result.put("GN","Guinea");
        result.put("GW","Guinea-Bissau");
        result.put("GY","Guyana");
        result.put("HT","Haiti");
        result.put("HM","Heard Island and McDonald Islands");
        result.put("VA","Holy See (Vatican City State)");
        result.put("HN","Honduras");
        result.put("HK","Hong Kong");
        result.put("HU","Hungary");
        result.put("IS","Iceland");
        result.put("IN","India");
        result.put("ID","Indonesia");
        result.put("IR","Iran, Islamic Republic of");
        result.put("IQ","Iraq");
        result.put("IE","Ireland");
        result.put("IM","Isle of Man");
        result.put("IL","Israel");
        result.put("IT","Italy");
        result.put("JM","Jamaica");
        result.put("JP","Japan");
        result.put("JE","Jersey");
        result.put("JO","Jordan");
        result.put("KZ","Kazakhstan");
        result.put("KE","Kenya");
        result.put("KI","Kiribati");
        result.put("KP","Korea, Democratic People's Republic of");
        result.put("KR","Korea, Republic of");
        result.put("KW","Kuwait");
        result.put("KG","Kyrgyzstan");
        result.put("LA","Lao People's Democratic Republic");
        result.put("LV","Latvia");
        result.put("LB","Lebanon");
        result.put("LS","Lesotho");
        result.put("LR","Liberia");
        result.put("LY","Libya");
        result.put("LI","Liechtenstein");
        result.put("LT","Lithuania");
        result.put("LU","Luxembourg");
        result.put("MO","Macao");
        result.put("MK","Macedonia, the former Yugoslav Republic of");
        result.put("MG","Madagascar");
        result.put("MW","Malawi");
        result.put("MY","Malaysia");
        result.put("MV","Maldives");
        result.put("ML","Mali");
        result.put("MT","Malta");
        result.put("MH","Marshall Islands");
        result.put("MQ","Martinique");
        result.put("MR","Mauritania");
        result.put("MU","Mauritius");
        result.put("YT","Mayotte");
        result.put("MX","Mexico");
        result.put("FM","Micronesia, Federated States of");
        result.put("MD","Moldova, Republic of");
        result.put("MC","Monaco");
        result.put("MN","Mongolia");
        result.put("ME","Montenegro");
        result.put("MS","Montserrat");
        result.put("MA","Morocco");
        result.put("MZ","Mozambique");
        result.put("MM","Myanmar");
        result.put("NA","Namibia");
        result.put("NR","Nauru");
        result.put("NP","Nepal");
        result.put("NL","Netherlands");
        result.put("NC","New Caledonia");
        result.put("NZ","New Zealand");
        result.put("NI","Nicaragua");
        result.put("NE","Niger");
        result.put("NG","Nigeria");
        result.put("NU","Niue");
        result.put("NF","Norfolk Island");
        result.put("MP","Northern Mariana Islands");
        result.put("NO","Norway");
        result.put("OM","Oman");
        result.put("PK","Pakistan");
        result.put("PW","Palau");
        result.put("PS","Palestinian Territory, Occupied");
        result.put("PA","Panama");
        result.put("PG","Papua New Guinea");
        result.put("PY","Paraguay");
        result.put("PE","Peru");
        result.put("PH","Philippines");
        result.put("PN","Pitcairn");
        result.put("PL","Poland");
        result.put("PT","Portugal");
        result.put("PR","Puerto Rico");
        result.put("QA","Qatar");
        result.put("RE","Réunion");
        result.put("RO","Romania");
        result.put("RU","Russian Federation");
        result.put("RW","Rwanda");
        result.put("BL","Saint Barthélemy");
        result.put("SH","Saint Helena, Ascension and Tristan da Cunha");
        result.put("KN","Saint Kitts and Nevis");
        result.put("LC","Saint Lucia");
        result.put("MF","Saint Martin (French part)");
        result.put("PM","Saint Pierre and Miquelon");
        result.put("VC","Saint Vincent and the Grenadines");
        result.put("WS","Samoa");
        result.put("SM","San Marino");
        result.put("ST","Sao Tome and Principe");
        result.put("SA","Saudi Arabia");
        result.put("SN","Senegal");
        result.put("RS","Serbia");
        result.put("SC","Seychelles");
        result.put("SL","Sierra Leone");
        result.put("SG","Singapore");
        result.put("SX","Sint Maarten (Dutch part)");
        result.put("SK","Slovakia");
        result.put("SI","Slovenia");
        result.put("SB","Solomon Islands");
        result.put("SO","Somalia");
        result.put("ZA","South Africa");
        result.put("GS","South Georgia and the South Sandwich Islands");
        result.put("SS","South Sudan");
        result.put("ES","Spain");
        result.put("LK","Sri Lanka");
        result.put("SD","Sudan");
        result.put("SR","Suriname");
        result.put("SJ","Svalbard and Jan Mayen");
        result.put("SZ","Swaziland");
        result.put("SE","Sweden");
        result.put("CH","Switzerland");
        result.put("SY","Syrian Arab Republic");
        result.put("TW","Taiwan, Province of China");
        result.put("TJ","Tajikistan");
        result.put("TZ","Tanzania, United Republic of");
        result.put("TH","Thailand");
        result.put("TL","Timor-Leste");
        result.put("TG","Togo");
        result.put("TK","Tokelau");
        result.put("TO","Tonga");
        result.put("TT","Trinidad and Tobago");
        result.put("TN","Tunisia");
        result.put("TR","Turkey");
        result.put("TM","Turkmenistan");
        result.put("TC","Turks and Caicos Islands");
        result.put("TV","Tuvalu");
        result.put("UG","Uganda");
        result.put("UA","Ukraine");
        result.put("AE","United Arab Emirates");
        result.put("GB","United Kingdom");
        result.put("US","United States");
        result.put("UM","United States Minor Outlying Islands");
        result.put("UY","Uruguay");
        result.put("UZ","Uzbekistan");
        result.put("VU","Vanuatu");
        result.put("VE","Venezuela, Bolivarian Republic of");
        result.put("VN","Viet Nam");
        result.put("VG","Virgin Islands, British");
        result.put("VI","Virgin Islands, U.S.");
        result.put("WF","Wallis and Futuna");
        result.put("EH","Western Sahara");
        result.put("YE","Yemen");
        result.put("ZM","Zambia");
        result.put("ZW","Zimbabwe");
        return Collections.unmodifiableMap(result);
    }

    public static String getNameOf(String code) {
        return COUNTRIES.get(code);
    }

}