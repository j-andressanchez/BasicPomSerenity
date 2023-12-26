# Proyecto de Automatización con Serenity y Selenium

Este proyecto utiliza Serenity y Selenium para la automatización de pruebas de software. [Serenity BDD](http://www.thucydides.info/docs/serenity/) proporciona un marco de trabajo BDD (Behavior-Driven Development) que simplifica la creación de pruebas automatizadas y genera informes detallados.

## Diagrama de interacción

![Diagrama de Interacción](Docs/AutomationBaseDiagram.drawio.png)

## Requisitos

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (Versión usada: Java 11)
- [Maven](https://maven.apache.org/download.cgi) (Versión usada: Java 2.4.3)
- [Eclipse](https://www.eclipse.org/downloads/) u otro entorno de desarrollo Java (opcional)

## Configuración del Proyecto

1. Clona este repositorio: `git clone https://dev.azure.com/ecopetrolad/AgileDevOps/_git/WebAutomationBase`
2. Abre el proyecto en tu entorno de desarrollo (Eclipse, IntelliJ, etc.).
3. Configura las dependencias de Maven.

## Estructura del Proyecto

- **src/test/java:** Contiene los archivos fuente de las pruebas, Clases de Definition, Steps, Utilities y Runner.
- **src/test/resources:** Almacena los archivos de recursos como archivos de características de Cucumber, DataDriven, archivos de ambiente y demás.
- **target:** Directorio donde se generan los informes y otros archivos de construcción.

## Ejecución de las Pruebas

Para ejecutar las pruebas, puedes utilizar Maven. Abre una terminal en la raíz del proyecto y ejecuta:

```bash
mvn verify -{PerfilRunner} -Dcucumber.filter.tags="@TagAEjecutar" -Denvironment="Ambiente"

 *Ejemplo*
mvn verify -{BaseWebRunner} -Dcucumber.filter.tags="@Exitoso" -Denvironment="QA"
