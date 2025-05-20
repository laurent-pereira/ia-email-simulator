package org.acme;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(modelName = "processor", tools = TodoService.class)
public interface EmailProcessor {

  @SystemMessage("""
      Tu es un assistant IA qui traite des emails entrants en français.
      Si l'email contient une tâche à effectuer, utilise l'outil 'addTask'
      avec une description concise de la tâche.
      Réponds UNIQUEMENT par ACKNOWLEDGED ou THANK_YOU.
      """)
  String processEmail(@UserMessage String email);
}