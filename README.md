# About Sheep_heep
Sheep flock AI simulation writen in JAVA using OpenGL, LWJGL, Slick and own engine called "Kryten".

![sheep_heap](http://s3.postimg.org/on7n67y3n/sheep_heap.png)

For running a simulation just run class **game.Run** in project **sheep_heap-core**.
You also need to set path to native libraries to VM Arguments. 
(In eclipse you can set it Project > Propertities > Run/Debug Settings > Create or edit launch configuration). 
Some more info about running an app you can find in [Kryten](https://github.com/jakub-chatrny/Kryten) repository.

    On Linux: -Djava.library.path=lib/native/linux
    On Mac: -Djava.library.path=lib/native/macosx
    On Windows: -Djava.library.path=lib/native/windows
    

    
Add the token to $HOME/.m2/settings.xml as the username

<settings>
  <servers>
        <server>
          <id>jitpack.io</id>
          <username>fedf89b330198b7107b2178fb20a6b78bcfa6903</username>
          <password>.</password>
        </server>
  </servers>
</settings>
							

