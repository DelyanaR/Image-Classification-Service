import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Image} from "../../image";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private apiServiceUrl= "http://localhost:8080";

  constructor(private http:HttpClient) { }

  public getImages(): Observable<Image[]>{
    return this.http.get<Image[]>(`${this.apiServiceUrl}/images`);
  }

  public getImageByUrl(url: string): Observable<Image>{
    return this.http.get<Image>(`${this.apiServiceUrl}/images/${url}`);
  }

 public getImagesByTagName(name: String): Observable<Image[]>{
    return this.http.get<Image[]>(`${this.apiServiceUrl}/tag/${name}`);
 }

  public getImage(id: number): Observable<Image>{
    return this.http.get<Image>(`${this.apiServiceUrl}/images/${id}`);
  }

  public createImage(image: Image): Observable<Image>{
    return this.http.post<Image>(`${this.apiServiceUrl}/images`,image);
  }
}
