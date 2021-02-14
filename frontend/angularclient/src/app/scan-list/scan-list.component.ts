import { Component, OnInit } from '@angular/core';
import {NetworkScan} from '../model/network-scan';
import {NetworkScanService} from '../service/network-scan.service';
import {retry, startWith, switchMap} from 'rxjs/operators';
import {interval} from 'rxjs/internal/observable/interval';

@Component({
  selector: 'app-scan-list',
  templateUrl: './scan-list.component.html',
  styleUrls: ['./scan-list.component.css']
})
export class ScanListComponent implements OnInit {

  maxDisplay = 5;
  networkScans: NetworkScan[];

  constructor(private networkScanService: NetworkScanService) {
    this.networkScans = [];
  }

  ngOnInit(): void {
    console.log('starting loop');
    interval(5000)
      .pipe(
        startWith(0),
        switchMap(() => this.networkScanService.getScans())
      )
      .subscribe(res => {

        if (res != null) {
          console.log('received ' + res.ipAddress);
          if (this.networkScans.length >= this.maxDisplay) {
            this.networkScans.shift();
          }
          this.networkScans.push(res);
        }
      });
  }

}
