import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {NetworkScan} from '../model/network-scan';

@Injectable({
  providedIn: 'root'
})
export class DataProxyService {

  private networkScansSource: BehaviorSubject<NetworkScan[]>  = new BehaviorSubject<NetworkScan[]>([]);
  public currentNetworkScans: Observable<NetworkScan[]> = this.networkScansSource.asObservable();

  constructor() { }

  onNetworkScanAdded(networkScan: NetworkScan): void {
    const scansAlreadyThere = this.networkScansSource.getValue();
    scansAlreadyThere.push(networkScan);
    this.networkScansSource.next(scansAlreadyThere);
  }
}
