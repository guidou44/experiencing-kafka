import { Component, OnInit } from '@angular/core';
import {ErrorScan} from '../model/error-scan';
import {ErrorScanService} from '../service/error-scan.service';
import {interval} from 'rxjs/internal/observable/interval';
import {startWith, switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-error-scan-list',
  templateUrl: './error-scan-list.component.html',
  styleUrls: ['./error-scan-list.component.css']
})
export class ErrorScanListComponent implements OnInit {

  private maxDisplay = 1;
  public currentErrorScans: ErrorScan[];

  constructor(private errorScanService: ErrorScanService) {
    this.currentErrorScans = [];
  }


  ngOnInit(): void {
    interval(5000)
      .pipe(
        startWith(0),
        switchMap(() => this.errorScanService.getErrorScans())
      )
      .subscribe(res => {
        if (res != null) {
          if (this.currentErrorScans.length >= this.maxDisplay) {
            this.currentErrorScans.shift();
          }
          this.currentErrorScans.push(res);
        }
      });
  }
}
