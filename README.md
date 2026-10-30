## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Pruebas

Las pruebas manuales de Java se guardan en la carpeta `test`. Para comprobar el estilo y la acción del botón desde PowerShell, ejecuta desde la carpeta del proyecto:

```powershell
javac -d bin src\estilos\EstiloBoton.java src\App.java test\PruebaApp.java
java "-Djava.awt.headless=true" -cp bin PruebaApp
```

Si todo funciona, veras `Pruebas correctas`.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
