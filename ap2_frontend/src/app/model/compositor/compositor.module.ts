import { MusicaModule } from "../musica/musica.module";

export interface CompositorModule {
  id?:number;
  nome?:string;
  musicas: Array<MusicaModule>;
}