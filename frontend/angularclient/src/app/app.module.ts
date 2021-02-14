import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import {PingService} from './service/ping.service';
import { PingComponent } from './ping/ping.component';
import { ScanListComponent } from './scan-list/scan-list.component';
import { ErrorScanListComponent } from './error-scan-list/error-scan-list.component';
import { SuccessfulScanListComponent } from './successful-scan-list/successful-scan-list.component';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    PingComponent,
    ScanListComponent,
    ErrorScanListComponent,
    SuccessfulScanListComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [PingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
