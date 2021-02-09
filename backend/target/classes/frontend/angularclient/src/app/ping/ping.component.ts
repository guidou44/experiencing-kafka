import { Component, OnInit } from '@angular/core';
import { PingService } from '../service/ping.service';

@Component({
  selector: 'app-ping',
  templateUrl: './ping.component.html',
  styleUrls: ['./ping.component.css']
})
export class PingComponent implements OnInit {

  ping_message: string;

  constructor(private pingService: PingService) {
    this.ping_message = 'no server message';
  }

  ngOnInit(): void {
    this.pingService.ping().subscribe(data => {
      this.ping_message = data;
    });
  }
}
