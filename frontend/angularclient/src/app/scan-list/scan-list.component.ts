import {Component, OnDestroy, OnInit} from '@angular/core';
import {NetworkScan} from '../model/network-scan';
import {NetworkScanService} from '../service/network-scan.service';
import {retry, startWith, switchMap} from 'rxjs/operators';
import {interval} from 'rxjs/internal/observable/interval';
import {DataProxyService} from '../service/data-proxy.service';

@Component({
  selector: 'app-scan-list',
  templateUrl: './scan-list.component.html',
  styleUrls: ['./scan-list.component.css']
})
export class ScanListComponent implements OnInit {

  private maxDisplay = 7;
  public networkScans: NetworkScan[];

  constructor(private networkScanService: NetworkScanService, private successfulScanProxy: DataProxyService) {
    this.networkScans = [];
  }

  ngOnInit(): void {
    interval(5000)
      .pipe(
        startWith(0),
        switchMap(() => this.networkScanService.getScans())
      )
      .subscribe(res => {

        console.log('received response');
        if (res != null) {
          console.log('received ' + res.ipAddress);
          if (this.networkScans.length >= this.maxDisplay) {
            this.networkScans.shift();
          }
          if (res.reachable) {
            this.publishSuccessfulScan(res);
          }
          this.networkScans.push(res);
        }
      });
  }

  publishSuccessfulScan(successfulScan: NetworkScan): void {
    this.successfulScanProxy.onNetworkScanAdded(successfulScan);
  }
}
