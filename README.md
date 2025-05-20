# IA Email Simulator avec Quarkus, LangChain4j et Ollama

Ce projet simule la génération et le traitement d'emails internes en français, en utilisant Quarkus, LangChain4j et Ollama pour exécuter des modèles d'IA localement.

## Prérequis

- [Java 17+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [Ollama](https://ollama.com/) installé sur votre machine
- Modèles Ollama `phi3:mini` et `llama3` téléchargés

## Installation et configuration

### 1. Installer Ollama et les modèles

Téléchargez et installez Ollama :  
https://ollama.com/download

Lancez Ollama, puis téléchargez les modèles nécessaires :

```sh
ollama pull phi3:mini
ollama pull llama3
```

### 2. Démarrer les modèles Ollama

Pour de meilleures performances, préchargez les deux modèles :

```sh
ollama run phi3:mini &
ollama run llama3 &
```

### 3. Cloner et configurer le projet

```sh
git clone <url-du-repo>
cd ia-email-simulator
```

Vérifiez la configuration dans `src/main/resources/application.properties` :

```properties
quarkus.langchain4j.generator.chat-model.model-name=phi3:mini
quarkus.langchain4j.processor.chat-model.model-name=llama3
quarkus.langchain4j.ollama.base-url=http://localhost:11434
quarkus.scheduler.enabled=true
```

### 4. Lancer l’application

```sh
./mvnw quarkus:dev
```

L’application va automatiquement générer et traiter des emails toutes les 5 minutes (modifiable dans `SimulationService.java`).

## Fonctionnement

- **EmailGeneratorService** : génère un email interne en français via le modèle `phi3:mini`.
- **EmailProcessor** : analyse l’email généré avec le modèle `llama3` et ajoute une tâche si besoin.
- **TodoService** : stocke les tâches extraites.

Les logs affichent chaque étape du processus.

## Ressources

- [Article de référence](https://myfear.substack.com/p/quarkus-langchain4j-local-ai-task-extractor)
- [Documentation Quarkus LangChain4j](https://quarkiverse.github.io/quarkiverse-docs/langchain4j/dev/)
- [Ollama](https://ollama.com/)

---

**Astuce** :  
Pour ajuster la fréquence de simulation, modifiez l’annotation `@Scheduled` dans `SimulationService.java`.