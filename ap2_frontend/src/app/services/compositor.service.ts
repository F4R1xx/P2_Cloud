import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompositorModule } from '../model/compositor/compositor.module';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompositorService {

  constructor(private httpClient: HttpClient) {  }

  public getCompositor(): Observable<CompositorModule[]> {
    return this.httpClient.get<CompositorModule[]> ('https://ibmec-ap1-william-cloud.azurewebsites.net/compositor')
  }

  public getCompositorById(id:any) : Observable<CompositorModule[]> {
    return this.httpClient.get<CompositorModule[]> ('https://ibmec-ap1-william-cloud.azurewebsites.net/compositor/'+ id)
  }
}
