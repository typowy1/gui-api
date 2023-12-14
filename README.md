## **Uruchamianie testów:**

### z poziomu terminala:

_**GUI**_

**Dla Firefox:**

mvn clean test -Dbrowser=FIREFOX '-Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml'

mvn clean test -Dbrowser=FIREFOX -Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml

**Dla Chrome:**

mvn clean test -Dbrowser=CHROME '-Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml'

mvn clean test -Dbrowser=CHROME -Dsurefire.suiteXmlFiles=src\test\resources\testSuite\gui_tests_suite.xml

**_API_**

mvn clean test '-Dsurefire.suiteXmlFiles=src\test\resources\testSuite\api_tests_suite.xml'

mvn clean test -Dsurefire.suiteXmlFiles=src\test\resources\testSuite\api_tests_suite.xml

### Jenkins:

_**GUI**_

mvn clean test -Dbrowser=${params.browser} -Dsurefire.suiteXmlFiles=src/test/resources/testSuite/${params.TestSuite}.xml

_**API**_

mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testSuite/${params.TestSuite}.xml

#### _**Oczywiście można też uruchamiać standardowo z poziomu klasy czy z poziomu testu w klasie testowej.**_

## **Generowanie Allure raportu z poziomu terminala**
1. Uruchamiamy testy z poziomu terminala 
2. Generujemy raport za pomocą komendy: _**mvn allure:report**_
3. Raport dostępny w lokalizacji: _target/site/allure-maven-plugin/index.html_
4. Klikamy w _index.html_, wybieramy przeglądarkę 
