import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {NetworkScan} from '../model/network-scan';

@Injectable({
  providedIn: 'root'
})
export class NetworkScanService {

  private readonly url: string;

  constructor(private http: HttpClient) {
    this.url = environment.apiUrl + 'networkscan/networkscan';
    console.log(this.url);
  }

  public getScans(): Observable<NetworkScan> {
    return this.http.get<NetworkScan>(this.url);
  }
}
