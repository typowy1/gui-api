package pl.example.commons;

import java.util.ArrayList;
import java.util.List;

public class CookiesConstants {

    public static final List<String> CONSENT_BAR_BUTTON_LABELS = new ArrayList<>(List.of(
            "Dostosuj", "Odrzuć wszystkie", "Zaakceptuj wszystkie"
    ));

    public static final List<String> PREFERENCE_BUTTON_LABELS = new ArrayList<>(List.of(
            "Odrzuć wszystkie", "Zaakceptuj wybrane", "Zaakceptuj wszystkie"
    ));

    public static final String CONSENT_BAR_HEADER_TEXT = "Strona korzysta z plików cookie";

    public static final String PREFERENCE_HEADER_TEXT = "Dostosuj preferencje dotyczące zgody";

    public static final List<String> PREFERENCE_ACCORDION_BTN_LABELS = new ArrayList<>(List.of(
            "Niezbędne", "Preferencyjne", "Statystyczne", "Marketingowe"
    ));

    public static final String PREFERENCE_TOGGLE_DISABLED = "Włączyć Preferencyjne";

    public static final String PREFERENCE_TOGGLE_ENABLED = "Wyłączyć Preferencyjne";
}