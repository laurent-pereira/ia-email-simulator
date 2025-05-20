package org.acme;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(modelName = "generator")
public interface EmailGeneratorService {

    @SystemMessage("""
            Rédige un court email interne en français. Formate-le avec :
            - une ligne d'objet
            - un corps qui inclut soit une demande d'action, soit un partage d'information à l'équipe.
            Garde un ton professionnel et décontracté.
            N'utilise pas plus de 100 mots.
            N'utilise pas de markdown. N'explique pas la consigne.
            Retourne uniquement le contenu de l'email en commençant par "Objet :"
            """)
    String generateEmail(@UserMessage String projectname);
}