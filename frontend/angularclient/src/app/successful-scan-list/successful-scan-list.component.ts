import {Component, OnDestroy, OnInit} from '@angular/core';
import {NetworkScan} from '../model/network-scan';
import {DataProxyService} from '../service/data-proxy.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-successful-scan-list',
  templateUrl: './successful-scan-list.component.html',
  styleUrls: ['./successful-scan-list.component.css']
})
export class SuccessfulScanListComponent implements OnInit, OnDestroy {

  private maxDisplay = 3;
  private subscription: Subscription | undefined;
  public networkScans: NetworkScan[];

  constructor(private successfulScanProxy: DataProxyService) {
    this.networkScans = [];
  }

  ngOnInit(): void {
    this.subscription = this.successfulScanProxy.currentNetworkScans.subscribe(scans => {
      if (scans.length > this.maxDisplay) {
        this.networkScans = scans.slice(-1 * this.maxDisplay);
      } else {
        this.networkScans = scans;
      }
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
