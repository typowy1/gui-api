## **Uruchamianie testów z poziomu terminala:**

**Dla Firefox:**

mvn clean test -Dbrowser=FIREFOX '-Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml'

mvn clean test -Dbrowser=FIREFOX -Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml

**Dla Chrome:**

mvn clean test -Dbrowser=CHROME '-Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml'

mvn clean test -Dbrowser=CHROME -Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml

## **Generowanie Allure raportu z poziomu terminala**
1. Uruchamiamy testy z poziomu terminala 
2. Generujemy raport za pomocą komendy: _**mvn allure:report**_
3. Raport dostępny w lokalizacji: _target/site/allure-maven-plugin/index.html_
4. Klikamy w _index.html_, wybieramy przeglądarkę 
