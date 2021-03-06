import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PingService {

  private readonly url: string;

  constructor(private http: HttpClient) {
    this.url = environment.apiUrl + 'ping/heartbeat';
  }

  public ping(): Observable<string> {
    return this.http.get(this.url, {responseType: 'text'});
  }
}
