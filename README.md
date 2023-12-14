## **Uruchamianie testów:**

### z poziomu terminala:

GUI

**Dla Firefox:**

mvn clean test -Dbrowser=FIREFOX '-Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml'

mvn clean test -Dbrowser=FIREFOX -Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml

**Dla Chrome:**

mvn clean test -Dbrowser=CHROME '-Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml'

mvn clean test -Dbrowser=CHROME -Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml

API

mvn clean test '-Dsurefire.suiteXmlFiles=src\test\resources\testSuite\api_tests_suite.xml'

mvn clean test -Dsurefire.suiteXmlFiles=src\test\resources\testSuite\api_tests_suite.xml

### Na Jenkins-ie:

Gui

mvn clean test -Dbrowser=${params.browser} -Dsurefire.suiteXmlFiles=src/test/resources/testSuite/${params.TestSuite}.xml

Api

mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testSuite/${params.TestSuite}.xml

#### _**Oczywiście można też uruchamiać standardowo z poziomu klasy czy z poziomu testu w klasie testowej.**_

## **Generowanie Allure raportu z poziomu terminala**
1. Uruchamiamy testy z poziomu terminala 
2. Generujemy raport za pomocą komendy: _**mvn allure:report**_
3. Raport dostępny w lokalizacji: _target/site/allure-maven-plugin/index.html_
4. Klikamy w _index.html_, wybieramy przeglądarkę 
