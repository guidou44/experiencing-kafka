import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PingService {

  private readonly url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/api/ping/heartbeat';
  }

  public ping(): Observable<string> {
    return this.http.get(this.url, {responseType:'text'});
  }
}
