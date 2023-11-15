## **Uruchamianie testów z poziomu terminala:**

mvn clean test '-Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml'

mvn clean test -Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml

## **Generowanie Allure raportu z poziomu terminala**
- Pierw trzeba uruchomić testy z poziomu terminala

mvn allure:report
