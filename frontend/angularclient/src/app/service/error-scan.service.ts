import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {ErrorScan} from '../model/error-scan';

@Injectable({
  providedIn: 'root'
})
export class ErrorScanService {

  private readonly url: string;

  constructor(private http: HttpClient) {
    this.url = environment.apiUrl + 'networkscan/errorscan';
  }

  public getErrorScans(): Observable<ErrorScan> {
    return this.http.get<ErrorScan>(this.url);
  }
}
