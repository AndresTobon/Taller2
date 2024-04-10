document.addEventListener("DOMContentLoaded", () => {
  class Cancion {
    constructor(file) {
      this.file = file;
      this.title = file.name;
    }
  }

  class Reproductor {
    constructor() {
      this.canciones = [];
      this.actualIndex = 0;
      this.audio = new Audio();
      this.inicializarUI();
    }

    inicializarUI() {
      document
        .getElementById("fileInput")
        .addEventListener("change", (e) =>
          this.cargarCanciones(e.target.files)
        );
      document
        .getElementById("play")
        .addEventListener("click", () => this.togglePlayPause());
      document
        .getElementById("next")
        .addEventListener("click", () => this.siguienteCancion());
      document
        .getElementById("prev")
        .addEventListener("click", () => this.anteriorCancion());
    }

    cargarCanciones(files) {
      this.canciones = []; // Reinicia la lista de canciones para evitar duplicados
      for (const file of files) {
        this.canciones.push(new Cancion(file));
      }
      this.actualIndex = 0; // Resetea el índice al cargar nuevas canciones
      this.cargarCancion(true); // Intenta reproducir automáticamente la primera canción
    }

    cargarCancion(autoPlay = false) {
      if (this.canciones.length > 0) {
        const cancion = this.canciones[this.actualIndex];
        this.audio.src = URL.createObjectURL(cancion.file);
        this.audio.load(); // Asegura que el navegador recargue el recurso de audio
        if (autoPlay) {
          this.audio
            .play()
            .catch((error) =>
              console.error("La reproducción automática falló", error)
            );
        }
      }
    }

    togglePlayPause() {
      if (this.audio.paused) {
        this.audio.play();
      } else {
        this.audio.pause();
      }
    }

    siguienteCancion() {
      if (this.actualIndex < this.canciones.length - 1) {
        this.actualIndex++;
      } else {
        this.actualIndex = 0;
      }
      this.cargarCancion(true);
    }

    anteriorCancion() {
      if (this.actualIndex > 0) {
        this.actualIndex--;
      } else {
        this.actualIndex = this.canciones.length - 1;
      }
      this.cargarCancion(true);
    }
  }

  new Reproductor();
});
