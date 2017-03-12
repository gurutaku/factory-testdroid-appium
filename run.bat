@ECHO OFF

SET FILTER=-skip
SET BROWSER=firefox
REM SET BROWSER=provided

mvn clean verify -Dwebdriver.driver=%BROWSER% -Dmetafilter=%FILTER%