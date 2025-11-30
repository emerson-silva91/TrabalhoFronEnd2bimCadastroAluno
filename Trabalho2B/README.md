
# Trabalho2B - API Spring Boot

Estrutura gerada com endpoints para:
- Alunos
- Professores
- Disciplinas
- Matrículas (AlunoDisciplina)
- Lançamento de notas e faltas por bimestre
- Aulas dadas e presenças

## Como rodar
- Ajuste `src/main/resources/application.properties` com suas credenciais do PostgreSQL
- `mvn clean package`
- `java -jar target/Trabalho2B-0.0.1-SNAPSHOT.war` (ou rode pelo IDE)

Swagger UI disponível em: http://localhost:8080/swagger-ui/index.html
