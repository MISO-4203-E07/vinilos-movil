Repositorio para el curso de Desarrollo Móvil 2021-15

# Construir e instalar la aplicación Vinilos de forma local

### Clone el repositorio
Puede clonarlo a través de HTTP
```shell
git clone https://github.com/MISW-4204-ComputacionEnNube/Proyecto-Grupo3-202120.git
```
o por SSH
```shell
git clone git@github.com:MISO-4203-E07/vinilos-movil.git
```
### Inicie Android Studio
Si tiene un proyecto abierto, puede dar click en 
```shell
File / New / Import project
```
o en la ventana de bienvenida en
```shell
Import project (Gradle, Eclipse ADT, etc)
```
Seleccione la carpeta del proyecto, acepte y espere a que termine de importar por completo. Este proceso puede tomar algunos minutos (dependiendo de la máquina) mientras descarga/instala las librerías y compila el proyecto.
Sí le solicita actualizar Gradle a una nueva versión, actualice y reconstruya el proyecto en la pestaña **Build / Rebuild Project**.
### Prepare un dispositivo virtual a través del emulador o conecte un dispositivo físico
En el siguiente enlace obtiene más información para emular un dispositivo android
```shell
https://developer.android.com/studio/run/emulator
```
Si cuenta con un dispositivo físico, conectelo y siga las instrucciones que aparecen en el siguiente enlace
```shell
https://developer.android.com/studio/debug/dev-options
```
**Nota**: recuerde que debe habilitar las **Opciones de desarrollador** del dispositivo físico y habilitar la **Depuración USB**

Una vez haya establecido la conexión con el dispositivo objetivo, podrá ver el nombre del mismo en la parte superior, junto a los botones de acciones rápidas. Ahora, ejecute la aplicación presionando el botón Run, con un ícono de flecha verde para compilar y ejecutar la aplicación.

Sí requiere más información sobre como instalar y utilizar Android Studio dirígase al codelab a través del enlace
```shell
https://misovirtual.virtual.uniandes.edu.co/codelabs/android-setup-tutorial/index.html#0
```
Terminados los anteriores pasos tendrá la aplicación abierta e instalada en su dispositivo.
### Ejecutar pruebas automatizadas con Espresso
Dentro de Android Studio, en la pestaña **Project**, dirígase a la siguiente carpeta
```shell
app/java/co.edu.uniandes.vinilos (androidTest)/view.album
```
Y abra cada uno de los archivos de los tests, es decir:
```shell
AlbumActivityTest
DetailAlbumActivityTest
```
Cada HU tiene asociado un test, para ejecutarlo sitúese al lado del nombre de la clase y de click sobre el botón **Rojo/verde** que aparece al lado del # de la línea.

Otra manera de ejecutar las pruebas es seleccionar el nombre del test en **Select Run/Debug Configuration** situado al lado de los botones de acciones rápidas y de nuevo darle click al  botón Run, con un ícono de flecha verde.

Se deberá abrir el emulador y ejecutar el test, una vez termine, le mostrará los resultados del mismo.