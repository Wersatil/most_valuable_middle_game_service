INSERT INTO question (question_text, answer, author) VALUES
('Возможно ли использование Prometeus без Actuator или наоборот?', 'Возможно, но Actuator ничего не хранит, он может только собирать и сразу куда-нибудь отдавать. А для Prometeus нужен экспортер метрик. То есть, нужно самому написать аналог Actuator.', 'Wersatil'),
('В чем разница между Gradle и Maven?', 'Maven — более простой и стандартизированный. Gradle — более гибкий и производительный, использует скрипты.', 'Wersatil'),
('Для чего нужен Gradle Wrapper?', '“Gradle Wrapper позволяет запускать сборку проекта с заранее определённой версией Gradle, даже если Gradle не установлен на машине. Это гарантирует воспроизводимость сборки и упрощает работу команды и CI.”', 'Wersatil'),
('Для чего нужен файл pom.xml?', 'Это - главный конфигурационный файл Maven (аналог build.gradle). Нужен для указания конфигураций проекта.', 'Wersatil'),
('Чем отличается implementation от compileOnly / testImplementation?', 'implementation — для зависимостей основного кода, они нужны и при компиляции, и при выполнении. compileOnly — только для компиляции, не попадут в jar (например, Lombok). testImplementation — только для тестов, доступны при компиляции и запуске тестов, но не в production.', 'Wersatil'),
('Как запустить сборку / тесты из консоли?', 'Команда "./gradlew test" выполняет все тесты в src/test/java и генерирует отчёт в build/reports/tests/test/index.html.', 'Wersatil');




